package com.xie.com.imoocmusic.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xie.com.imoocmusic.R;

public class MPAndroidChartActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpandroid_chart);
        initNavBar(true,"MPAndroidChart图表",false);
    }

    /**
     * MPAndroidChart框架实例:LineChart图表
     * @param view
     */
    public void onLineChartClick(View view) {
        Intent intent = new Intent(this,MPLineChartActivity.class);
        startActivity(intent);
    }

    /**
     * MPAndroidChart框架实例:BarChart图表
     * @param view
     */
    public void onBarChartClick(View view) {
        Intent intent = new Intent(this,MPBarChartActivity.class);
        startActivity(intent);
    }

    /**
     * MPAndroidChart框架实例:PieChart图表
     * @param view
     */
    public void onPieChartClick(View view) {
        Intent intent = new Intent(this,MPPieChartActivity.class);
        startActivity(intent);
    }
}
