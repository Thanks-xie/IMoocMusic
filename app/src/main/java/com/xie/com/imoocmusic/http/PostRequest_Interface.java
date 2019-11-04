package com.xie.com.imoocmusic.http;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 *  URL
 * http://fanyi.youdao.com/translate
 *
 *  URL实例
 * http://fanyi.youdao.com/translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=
 *
 *
 *  参数说明
 *  doctype：json 或 xml
 *  jsonversion：如果 doctype 值是 xml，则去除该值，若 doctype 值是 json，该值为空即可
 *  xmlVersion：如果 doctype 值是 json，则去除该值，若 doctype 值是 xml，该值为空即可
 *  type：语言自动检测时为 null，为 null 时可为空。英译中为 EN2ZH_CN，中译英为 ZH_CN2EN，日译中为 JA2ZH_CN，中译日为 ZH_CN2JA，韩译中为 KR2ZH_CN，中译韩为 ZH_CN2KR，中译法为 ZH_CN2FR，法译中为 FR2ZH_CN
 *  keyform：mdict. + 版本号 + .手机平台。可为空
 *  model：手机型号。可为空
 *  mid：平台版本。可为空
 *  imei：???。可为空
 *  vendor：应用下载平台。可为空
 *  screen：屏幕宽高。可为空
 *  ssid：用户名。可为空
 *  abtest：???。可为空
 *
 *  请求方式说明
 *  请求方式：POST
 *  请求体：i
 *  请求格式：x-www-form-urlencoded
 * @author xiejinbo
 * @date 2019/10/29 0029 13:23
 */
public interface PostRequest_Interface {


        @POST("translate?doctype=json&jsonversion=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
        @FormUrlEncoded
        Call<PostTranslation> getCall(@Query ("type") String type,@Field("i") String inputValue);
        //采用@Post表示Post方法进行请求（传入部分url地址）
        // 采用@FormUrlEncoded注解的原因:API规定采用请求格式x-www-form-urlencoded,即表单形式
        // 需要配合@Field 向服务器提交需要的字段

}
