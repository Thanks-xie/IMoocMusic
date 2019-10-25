package com.xie.com.imoocmusic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xie.com.imoocmusic.R;
import com.xie.com.imoocmusic.help.UserHelp;
import com.xie.com.imoocmusic.utils.UserUtil;

public class MeActivity extends BaseActivity {

    private TextView userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        initView();
    }

    /**
     * 初始化加载
     */
    private void initView(){
        initNavBar(true,"个人中心",false);
        userName = findViewById(R.id.user_name);
        userName.setText(UserHelp.getInstance().getPhone());
    }

    /**
     * 修改密码
     * @param view
     */
    public void onChangeClick(View view){
        Intent intent = new Intent(this,ChangePasswordActivity.class);
        startActivity(intent);
    }

    /**
     * 退出登录
     * @param view
     */
    public void onLogoutClick(View view){
        UserUtil.logout(this);
    }

    /**
     * 进入IM聊天
     * @param view
     */
    public void onChatClick(View view){
        Intent intent = new Intent(this,IMActivity.class);
        startActivity(intent);
    }

    /**
     * 图表展示
     * @param view
     */
    public void onChartsClick(View view){
        Intent intent = new Intent(this,ChartsActivity.class);
        startActivity(intent);
    }
    /**
     * 流式布局
     * @param view
     */
    public void  onFlowLayoutClick(View view){
        Intent intent = new Intent(this,FlowLayoutActivity.class);
        startActivity(intent);
    }


    /**
     * ViewPager卡片式布局
     * @param view
     */
    public void onViewPagerClick(View view) {
        Intent intent = new Intent(this,ViewPagerActivity.class);
        startActivity(intent);
    }

    /**
     * HTTP测试
     * @param view
     */
    public void onHTTPClick(View view) {
        Intent intent = new Intent(this,HTTPURLConnectionActivity.class);
        startActivity(intent);
    }
}
