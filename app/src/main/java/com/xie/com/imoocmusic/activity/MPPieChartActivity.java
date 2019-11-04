package com.xie.com.imoocmusic.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;
import com.xie.com.imoocmusic.R;
import com.xie.com.imoocmusic.utils.LargeValueFormatter;
import com.xie.com.imoocmusic.utils.PieChartUtil;

import java.util.ArrayList;
import java.util.List;

public class MPPieChartActivity extends BaseActivity {
    private PieChart pieChart;
    private PieChart pieChart2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mppie_chart);
        initNavBar(true,"PieChart图表",false);
        initView();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        pieChart = findViewById(R.id.pieChart);
        pieChart2 = findViewById(R.id.pieChart2);
        settingPieChart();
        initData();
    }

    /**
     * PieChart设置
     */
    private void settingPieChart() {
        String descriptionStr = "幻灵新能源公司季度营业额";
        PieChartUtil.setPieChartSetting(this,pieChart,descriptionStr);
        String descriptionStr2 = "幻灵智能通信公司季度营业额";
        PieChartUtil.setPieChartSetting(this,pieChart2,descriptionStr2);
    }



    /**
     * 加载数据
     */
    private void initData() {
        List<PieEntry> valsComp1 = new ArrayList<PieEntry>();
        //幻灵新能源公司四个季度收入
        valsComp1.add(new PieEntry(1009000f, "第一季度"));
        valsComp1.add(new PieEntry(1408000f, "第二季度"));
        valsComp1.add(new PieEntry(1205000f, "第三季度"));
        valsComp1.add(new PieEntry(1855000f, "第四季度"));

        List<Integer> colors = new ArrayList<>();
        for (int c : ColorTemplate.VORDIPLOM_COLORS) {colors.add(c);}
        for (int c : ColorTemplate.LIBERTY_COLORS) {colors.add(c);}
        for (int c : ColorTemplate.COLORFUL_COLORS) {colors.add(c);}
        for (int c : ColorTemplate.PASTEL_COLORS) {colors.add(c);}
        colors.add(ColorTemplate.getHoloBlue());

        List<PieEntry> valsComp2 = new ArrayList<PieEntry>();
        //幻灵新能源公司四个季度收入
        valsComp2.add(new PieEntry(1109000f, "第一季度"));
        valsComp2.add(new PieEntry(1508000f, "第二季度"));
        valsComp2.add(new PieEntry(1205000f, "第三季度"));
        valsComp2.add(new PieEntry(855000f, "第四季度"));

        //设置第一个饼状图数据
        PieChartUtil.setPieChartData(valsComp1,colors,pieChart);
        //设置第二个饼状图数据
        PieChartUtil.setPieChartData(valsComp2,colors,pieChart2);

    }
}
