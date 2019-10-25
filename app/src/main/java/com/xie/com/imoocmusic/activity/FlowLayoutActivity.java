package com.xie.com.imoocmusic.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xie.com.imoocmusic.R;

public class FlowLayoutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout);

        initView();
    }

    private void initView() {
        initNavBar(true,"流式布局",false);
    }

    /**
     * 进入流式布局
     * @param view
     */
    public void toFlowLayout(View view){
        Intent intent = new Intent(this,FlowLayoutViewGroupActivity.class);
        startActivity(intent);
    }

    /**
     * 进入FlexBoxLayout布局
     * @param view
     */
    public void toFlexBoxLayout(View view) {
        Intent intent = new Intent(this,FlexBoxLayoutActivity.class);
        startActivity(intent);
    }

    /**
     * 进入FlexBoxLayoutManager布局
     * @param view
     */
    public void toFlexBoxLayoutManager(View view) {
        Intent intent = new Intent(this,FlexBoxLayoutManagerActivity.class);
        startActivity(intent);
    }

    /**
     * 进入TagFlowLayout布局
     * @param view
     */
    public void toTagFlowLayout(View view) {
        Intent intent = new Intent(this,TagFlowLayoutActivity.class);
        startActivity(intent);
    }
}
