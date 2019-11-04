package com.xie.com.imoocmusic.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.xie.com.imoocmusic.R;
import com.xie.com.imoocmusic.http.GetRequest_Interface;
import com.xie.com.imoocmusic.http.GetTranslation;
import com.xie.com.imoocmusic.utils.RetrofitLogUtil;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit框架的Get请求
 */
public class RetrofitGetActivity extends BaseActivity {

    private NiceSpinner niceSpinner;
    private Button btn;
    private EditText input;
    private String inputString;
    private Map<String,String> map;
    private String f_language;
    private String t_language;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_get);
        initNavBar(true,"金山词霸翻译中心",false);
        initView();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        niceSpinner =  findViewById(R.id.nice_spinner);
        result = findViewById(R.id.show_result);
        initSpinnerView();
        input = findViewById(R.id.input_text);
        f_language="zh";
        t_language="en";
        btn = findViewById(R.id.translate_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputString = input.getText().toString();
                request();
            }
        });
    }

    /**
     * 初始化下拉框
     */
    private void initSpinnerView() {
        final List<String> dataset = new LinkedList<>(Arrays.asList("中文 >> 英文", "英文 >> 中文", "中文 >> 日语", "日语 >> 中文"));
        map = new HashMap<>();
        map.put("中文 >> 英文","zh>>en");
        map.put("英文 >> 中文","en>>zh");
        map.put("中文 >> 日语","zh>>ja");
        map.put("日语 >> 中文","ja>>zh");
        niceSpinner.attachDataSource(dataset);
        niceSpinner.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                // This example uses String, but your type can be any
                parent.getItemAtPosition(position);
                f_language = map.get(dataset.get(position)).split(">>")[0];
                t_language = map.get(dataset.get(position)).split(">>")[1];
            }
        });

    }

    /**
     * 使用Retrofit封装的方法
     */
    private void request() {

        OkHttpClient client = RetrofitLogUtil.showRetrofitLog();

        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .client(client) //打印log
                .build();

        // 步骤5:创建 网络请求接口 的实例
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        //对 发送请求 进行封装
        Call<GetTranslation> call = request.getCall(f_language,t_language,inputString);
        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<GetTranslation>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<GetTranslation> call, Response<GetTranslation> response) {
                // 步骤7：处理返回的数据结果
                GetTranslation getTranslation = response.body();
                if (getTranslation!=null){
                    result.setText(getTranslation.content.out);
                }
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<GetTranslation> call, Throwable throwable) {
                Log.e("RetrofitGetActivity","连接失败: "+throwable.toString());
            }
        });


    }
}
