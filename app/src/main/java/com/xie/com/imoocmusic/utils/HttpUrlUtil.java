package com.xie.com.imoocmusic.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpUrlUtil {

    /**
     * 返回Url网址内容
     * @param sendUrl
     * @return
     */
    public static String sendUrl(String sendUrl){
        try {
            URL url = new URL(sendUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");

            //下面开始读取返回的网页数据
            InputStream inputStream = conn.getInputStream();
            //将字节流转换为字符流
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            //创建缓冲区存内容
            StringBuffer buffer = new StringBuffer();
            String str = null;
            while ((str = reader.readLine())!=null){
                buffer.append(str);
            }
            return buffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 返回Url对应的图片内容
     * @param sendUrl
     * @return
     */
    public static Bitmap loadImage(String sendUrl){
        try {
            URL url = new URL(sendUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");

            //下面开始读取返回的网页数据
            InputStream inputStream = conn.getInputStream();
            //命名
            String fileName = String.valueOf(System.currentTimeMillis());
            FileOutputStream outputStream =null;
            File fileDownload =null;
           //判断SDK是否被挂载
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                //获取sdk路径
                File parent = Environment.getExternalStorageDirectory();
                fileDownload = new File(parent,fileName);
                outputStream = new FileOutputStream(fileDownload);
            }
            //图片缓存
            byte[] bytes = new byte[2*1024];
            int lens;
            if (outputStream!=null){
                while ((lens = inputStream.read(bytes)) != -1){
                    outputStream.write(bytes,0,lens);
                }
                return BitmapFactory.decodeFile(fileDownload.getAbsolutePath());
            }else {
                return null;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * post请求
     * @param strUrl
     * @return
     */
    public static String post(String strUrl,String params ){
        try {
            URL url = new URL(strUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
           conn.setReadTimeout(5000);
           conn.setRequestMethod("POST");

           //设置请求体类型，此处为文本类型
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            //设置请求体的长度
            conn.setRequestProperty("Content-Length",String.valueOf(params.getBytes()));
            //获得输出流，向服务器写入
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(params.getBytes());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
