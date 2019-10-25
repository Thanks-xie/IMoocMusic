package com.xie.com.imoocmusic.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xie.com.imoocmusic.R;
import com.xie.com.imoocmusic.utils.UserUtil;
import com.xie.com.imoocmusic.views.InputView;

public class RegisterActivity extends BaseActivity {

    private InputView inputPhone,inputPassword,inputRePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }


    /**
     * 初始化加载
     */
    private void initView(){
        initNavBar(true,"注册",false);
        inputPhone = findViewById(R.id.input_phone);
        inputPassword = findViewById(R.id.input_password);
        inputRePassword = findViewById(R.id.input_repassword);
    }


    /**
     * 注册功能
     */
    public void onRegisterClick(View view){
        String phone = inputPhone.getInputString();
        String password = inputPassword.getInputString();
        String repassword = inputRePassword.getInputString();

        if (UserUtil.registerUtil(this,phone,password,repassword)){
           onBackPressed();
        }
    }
}
