package com.xie.com.imoocmusic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.xie.com.imoocmusic.R;
import com.xie.com.imoocmusic.config.MyXmppConfig;
import com.xie.com.imoocmusic.utils.UserUtil;
import com.xie.com.imoocmusic.views.InputView;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;

import java.io.IOException;

public class LoginActivity extends BaseActivity {

    private InputView mInputPhone,mInputPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    /**
     * 初始化加载
     */
    private void initView(){
        initNavBar(false,"登录",false);
        mInputPhone = findViewById(R.id.input_phone);
        mInputPassword = findViewById(R.id.input_password);
    }

    /**
     * 跳转注册页面
     */
    public void onRegisterClick(View v){
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }

    /**
     * 登录
     */
    public void onCommitClick(View v){
        final String phone = mInputPhone.getInputString();
        final String password = mInputPassword.getInputString();
        if ( !UserUtil.isValidityLogin(this,phone,password)){
            return;
        }else {

            try {
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        //服务器登录
                        MyXmppConfig.loginOpenfire(phone,password);
                    }
                }).start();

            } catch (Exception e) {
                Log.e("onCommitClick","Exception: "+e.toString());
            }
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

}
