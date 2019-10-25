package com.xie.com.imoocmusic.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;

import com.xie.com.imoocmusic.R;
import com.xie.com.imoocmusic.utils.HttpUrlUtil;

public class HTTPURLConnectionActivity extends BaseActivity {

    private WebView webView;
    private SendURLTask sendURLTask;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_httpurlconnection);
        initView();
        sendURLTask = new SendURLTask("https://www.baidu.com");
        sendURLTask.execute();
    }

    private void initView() {
        initNavBar(true,"HTTP URL 网络请求",false);
        webView = findViewById(R.id.webview);
        imageView = findViewById(R.id.img);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //避免内存泄漏
        if (sendURLTask!=null){
            sendURLTask.cancel(true);
        }
    }

    /**
     * 由于网络请求耗时，采用异步task请求
     */
    private class SendURLTask extends AsyncTask<Void,Void,String>{
        private String httpUrl;

        public SendURLTask (String url){
            httpUrl = url;
        }

        @Override
        protected String doInBackground(Void... voids) {
            //进行网络请求
            return HttpUrlUtil.sendUrl(httpUrl);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //加载返回的数据
            webView.loadData(s,"text/html;charset=utf-8",null);
        }
    }

}
