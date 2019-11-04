package com.xie.com.imoocmusic.activity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xie.com.imoocmusic.R;
import com.xie.com.imoocmusic.http.PostRequest_Interface;
import com.xie.com.imoocmusic.http.PostTranslation;
import com.xie.com.imoocmusic.utils.RetrofitLogUtil;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import cn.salesuite.saf.utils.StringUtils;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit框架的Post请求
 */
public class RetrofitPostActivity extends BaseActivity {

    private NiceSpinner niceSpinner;
    private Button button;
    private EditText inputEdit;
    private TextView result;
    private String type = "ZH_CN2EN";
    private String inputValue;
    private Map<String,String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_post);
        initNavBar(true,"有道翻译中心",false);
        initView();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        niceSpinner = findViewById(R.id.nice_spinner);
        initSpinnerView();
        inputEdit = findViewById(R.id.input_text);
        result = findViewById(R.id.show_result);
        button = findViewById(R.id.translate_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputValue = inputEdit.getText().toString();
                if (StringUtils.isNotBlank(inputValue)){
                    request();
                }
            }
        });

    }

    /**
     * 初始化下拉框
     */
    private void initSpinnerView() {
        final List<String> dataset = new LinkedList<>(Arrays.asList("中文 >> 英文", "英文 >> 中文", "中文 >> 日语", "日语 >> 中文"));
        map = new HashMap<>();
        map.put("中文 >> 英文","ZH_CN2EN");
        map.put("英文 >> 中文","EN2ZH_CN");
        map.put("中文 >> 日语","ZH_CN2JA");
        map.put("日语 >> 中文","JA2ZH_CN");
        niceSpinner.attachDataSource(dataset);
        niceSpinner.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                // This example uses String, but your type can be any
                parent.getItemAtPosition(position);
                type = map.get(dataset.get(position));
            }
        });

    }

    /**
     * post请求
     */
    private void request() {
        //添加打印
        OkHttpClient client = RetrofitLogUtil.showRetrofitLog();

        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .client(client)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        PostRequest_Interface request = retrofit.create(PostRequest_Interface.class);

        //对 发送请求 进行封装(设置需要翻译的内容)
        Call<PostTranslation> call = request.getCall(type,inputValue);

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<PostTranslation>() {

            //请求成功时回调
            @Override
            public void onResponse(Call<PostTranslation> call, Response<PostTranslation> response) {
                Log.e("RetrofitPostActivity","请求成功");
                // 步骤7：处理返回的数据结果：输出翻译的内容
                result.setText(response.body().getTranslateResult().get(0).get(0).getTgt());
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<PostTranslation> call, Throwable throwable) {
                Log.e("RetrofitPostActivity","请求失败");
                Log.e("RetrofitPostActivity","onFailure: "+throwable.getMessage());
            }
        });
    }
}
