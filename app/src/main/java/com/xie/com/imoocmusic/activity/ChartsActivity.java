package com.xie.com.imoocmusic.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xie.com.imoocmusic.R;

/**
 * 图表展示
 */
public class ChartsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);
        initView();
    }

    private void initView(){
        initNavBar(true,"图表展示",false);
    }

    /**
     * 进入直方图显示
     * @param view
     */
    public void toHistogram(View view){
        Intent intent = new Intent(this,HistogramActivity.class);
        startActivity(intent);
    }

    /**
     * 进入折线图
     * @param view
     */
    public void toLineChart(View view){
        Intent intent = new Intent(this,LineChartActivity.class);
        startActivity(intent);
    }
    /**
     * 进入柱形图
     * @param view
     */
    public void toBarChart(View view){
        Intent intent = new Intent(this,BarChartActivity.class);
        startActivity(intent);
    }
    /**
     * 进入饼状图
     * @param view
     */
    public void toPieChart(View view){
        Intent intent = new Intent(this,PieChartActivity.class);
        startActivity(intent);
    }
}
