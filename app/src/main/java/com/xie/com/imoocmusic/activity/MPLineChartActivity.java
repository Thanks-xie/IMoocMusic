package com.xie.com.imoocmusic.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.xie.com.imoocmusic.R;
import com.xie.com.imoocmusic.utils.LargeValueFormatter;
import com.xie.com.imoocmusic.views.MyCustomMarkerView;

import java.util.ArrayList;
import java.util.List;

public class MPLineChartActivity extends BaseActivity{

    private LineChart lineChart;
    private MyCustomMarkerView myCustomMarkerView;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpline_chart);
        initNavBar(true,"LineChart图表",false);
        context = this;
        initView();
    }

    private void initView() {
        lineChart = findViewById(R.id.lineChart);
        settingLineChart();
        setChartsDescription();
        setXAxis();
        setYAxis();
        initData();
        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                myCustomMarkerView = new MyCustomMarkerView(context,R.layout.custom_marker_view_layout);
                myCustomMarkerView.setChartView(lineChart);
                lineChart.setMarker(myCustomMarkerView);
            }

            @Override
            public void onNothingSelected() {
            }
        });
    }

    /**
     * 折线图设置
     */
    private void settingLineChart() {
        lineChart.setAutoScaleMinMaxEnabled(true);
        //设置动画
        lineChart.animateXY(1000,1000);

        //设置表注解label
        Legend legend = lineChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        //legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setDrawInside(true);



    }

    /**
     * 设置数据
     */
    private void initData() {

        List<Entry> valsComp1 = new ArrayList<Entry>();
        List<Entry> valsComp2 = new ArrayList<Entry>();
        //幻灵新能源公司四个季度收入
        valsComp1.add(new Entry(0f, 1009000f));
        valsComp1.add(new Entry(1f, 1408000f));
        valsComp1.add(new Entry(2f, 1205000f));
        valsComp1.add(new Entry(3f, 1855000f));

        LineDataSet setComp1 = new LineDataSet(valsComp1, "幻灵新能源公司");
        setComp1.setColor(Color.BLUE);
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);

        //幻灵智能通信公司四个季度收入
        valsComp2.add(new Entry(0f, 1308000f));
        valsComp2.add(new Entry(1f, 1150000f));
        valsComp2.add(new Entry(2f, 1006000f));
        valsComp2.add(new Entry(3f, 1255000f));

        LineDataSet setComp2 = new LineDataSet(valsComp2, "幻灵智能通信公司");
        setComp2.setColor(Color.GREEN);
        setComp2.setAxisDependency(YAxis.AxisDependency.LEFT);

        List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(setComp1);
        dataSets.add(setComp2);

        LineData lineData = new LineData(dataSets);
        //设置折线图每个节点显示值单位(K)
        lineData.setValueFormatter(new LargeValueFormatter());
        //设置线上不显示值
        lineData.setDrawValues(false);
        lineChart.setData(lineData);
        lineChart.invalidate();



    }

    /**
     * 设置图表的描述
     */
    private void setChartsDescription(){
        //设置图表的描述，右下角文字
        Description description = new Description();
        //设置文字，会显示在图表的右下角。
        description.setText("幻灵科技集团");
        description.setTextSize(14f);
        description.setYOffset(10f);
        //设置文字颜色
        description.setTextColor(getResources().getColor(R.color.blue));
        description.setTypeface(Typeface.DEFAULT_BOLD);
        lineChart.setDescription(description);

        /*//设置图表的描述，图表上方文字
        Description description1 = new Description();
        description1.setPosition(100,50);
        description1.setXOffset(30f);
        description1.setYOffset(30f);
        description1.setTextAlign(Paint.Align.CENTER);
        description1.setTypeface(Typeface.DEFAULT_BOLD);
        description1.setTextColor(getResources().getColor(R.color.colorAccent));
        description1.setText("单位:万元");
        lineChart.setDescription(description1);*/
    }

    /**
     * 绘制X轴
     */
    private void setXAxis(){
        //获取X轴对象，设置X轴
        XAxis xAxis = lineChart.getXAxis();
        //设置X轴位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置轴启用或禁用。如果false，该轴的任何部分都不会被绘制（不绘制坐标轴/便签等）
        //xAxis.setEnabled(true);
        //设置为true，则绘制该行旁边的轴线（axis-line）。
        xAxis.setDrawAxisLine(true);
        //设置为true，则绘制网格线。
        xAxis.setDrawGridLines(false);
        //设置为true，则绘制轴的标签,X轴坐标值
        xAxis.setDrawLabels(true);
        //xAxis.setSpaceMax(4);
        //设置X轴坐标值
        setXAxisLabelValue(xAxis);
        //设置最后和第一个标签不超出x轴
        xAxis.setAvoidFirstLastClipping(true);
    }

    /**
     * 绘制Y轴
     */
    private void setYAxis(){
        //获取左边Y轴对象，设置左边Y轴
        YAxis yLeftAxis = lineChart.getAxisLeft();
        yLeftAxis.setEnabled(true);
        //设置，其中轴标签绘制的位置。
        yLeftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        //是否启用绘制零线:设置为true后才有后面的操作
        yLeftAxis.setDrawZeroLine(true);
        //绘制零线颜色
        yLeftAxis.setZeroLineColor(Color.BLUE);
        //设置Y轴显示值单位(K)
        yLeftAxis.setValueFormatter(new LargeValueFormatter());
        //保证Y轴从0开始，不然会上移一点
        yLeftAxis.setAxisMinimum(0f);

        YAxis yRightAxis = lineChart.getAxisRight();
        yRightAxis.setEnabled(false);

    }

    /**
     * 设置X轴坐标值，横坐标为季度
     */
    private void setXAxisLabelValue(XAxis xAxis){
        final String[] quarters = new String[] { "第一季度", "第二季度", "第三季度", "第四季度" };
        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return quarters[(int) value];
            }
        };
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);
    }


}
