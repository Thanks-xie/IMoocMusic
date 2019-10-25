package com.xie.com.imoocmusic.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.xie.com.imoocmusic.R;
import com.xie.com.imoocmusic.utils.UserUtil;
import com.xie.com.imoocmusic.views.InputView;

public class ChangePasswordActivity extends BaseActivity {

    private InputView oldPd,newPd,newPdConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initView();
    }

    private void initView(){
        initNavBar(true,"修改密码",false);
        oldPd = findViewById(R.id.input_old_password);
        newPd = findViewById(R.id.input_new_password);
        newPdConfirm = findViewById(R.id.input_password_confirm);
    }

    public void onCommitChangeClick(View view){

        String oldPassWord = oldPd.getInputString();
        String newPassWord = newPd.getInputString();
        String newPassWordConfirm = newPdConfirm.getInputString();

        boolean isChange = UserUtil.changePassWord(this,oldPassWord,newPassWord,newPassWordConfirm);
        if (!isChange){
            return;
        }
        UserUtil.logout(this);
    }
}
