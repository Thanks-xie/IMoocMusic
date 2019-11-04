package com.xie.com.imoocmusic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.xie.com.imoocmusic.R;

public class RetrofitActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        initNavBar(true,"Retrofit框架实例",false);
    }

    /**
     * Retrofit框架实例:Get运用
     * @param view
     */
    public void onRetrofitGetClick(View view) {
        Intent intent = new Intent(this,RetrofitGetActivity.class);
        startActivity(intent);
    }

    /**
     * Retrofit框架实例:Post运用
     * @param view
     */
    public void onRetrofitPostClick(View view) {
        Intent intent = new Intent(this,RetrofitPostActivity.class);
        startActivity(intent);

    }
}
