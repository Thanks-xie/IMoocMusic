package com.xie.com.imoocmusic.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xie.com.imoocmusic.R;

public class BaseActivity extends Activity {

    private ImageView mNavBack,mNavMe;
    private TextView mNavTitle;

    /**
     * 加载navBar,控制其显示与隐藏
     * @param isShowBack
     * @param title
     * @param isShowMe
     */
    protected void initNavBar(boolean isShowBack,String title,boolean isShowMe){
        mNavBack = findViewById(R.id.nav_back);
        mNavMe = findViewById(R.id.nav_me);
        mNavTitle = findViewById(R.id.nav_title);

        mNavBack.setVisibility(isShowBack ? View.VISIBLE : View.GONE);
        mNavMe.setVisibility(isShowMe ? View.VISIBLE : View.GONE);
        mNavTitle.setText(title);

        //后退操作
        mNavBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //进入个人中心

        mNavMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(BaseActivity.this,MeActivity.class);
                    startActivity(intent);
            }
        });
    }
}
