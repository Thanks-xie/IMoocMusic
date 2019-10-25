package com.xie.com.imoocmusic.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xie.com.imoocmusic.R;
import com.xie.com.imoocmusic.views.HistogramView;

public class HistogramActivity extends BaseActivity {

    private HistogramView histogramView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histogram);
        initView();
        initData();
    }

    private void initData() {
        int[][] columnInfo = new int[][]{
                {6, Color.BLUE},
                {5, Color.GRAY},
                {3, Color.GREEN},
                {7, Color.YELLOW},
                {2, Color.LTGRAY},
                {8, Color.DKGRAY},
                {1, Color.RED},
        };
        histogramView.setColumnInfo(columnInfo);
        histogramView.setXAxisScaleValue(10,9);
        histogramView.setYAxisScaleValue(9,9);
    }

    private void initView(){
        initNavBar(true,"直方图",false);
        histogramView = findViewById(R.id.my_histogram);
    }
}
