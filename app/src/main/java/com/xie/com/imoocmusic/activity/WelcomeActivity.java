package com.xie.com.imoocmusic.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xie.com.imoocmusic.R;
import com.xie.com.imoocmusic.utils.UserUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 1.延迟3秒
 * 2.跳转
 */
public class WelcomeActivity extends BaseActivity {

    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        init();
    }

    /**
     * 延迟3秒加载
     */
    private void init(){
        final boolean isLogin = UserUtil.validateUserLogin(this);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                if (isLogin){
                    toMain();
                }else {
                    toLogin();
                }

            }
        },3*1000);
    }
    private void  toMain(){
        Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void  toLogin(){
        Intent intent = new Intent(WelcomeActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
