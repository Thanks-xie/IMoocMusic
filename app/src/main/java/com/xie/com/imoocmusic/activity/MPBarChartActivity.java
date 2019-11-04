package com.xie.com.imoocmusic.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.xie.com.imoocmusic.R;
import com.xie.com.imoocmusic.utils.LargeValueFormatter;

import java.util.ArrayList;
import java.util.List;



public class MPBarChartActivity extends BaseActivity {
    private BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpbar_chart);
        initNavBar(true,"BarChart图表",false);
        initView();
    }

    /**
     *初始化布局
     */
    private void initView() {
        barChart = findViewById(R.id.barChart);
        settingBarChart();
        setChartsDescription();
        setXAxis();
        setYAxis();
        initData();
    }

    /**
     * 基础设置
     */
    private void settingBarChart() {
        barChart.setAutoScaleMinMaxEnabled(true);
        barChart.animateXY(1000,1000);
        //设置表注解label
        Legend legend = barChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        //legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setDrawInside(true);

    }

    /**
     * 初始化数据
     */
    private void initData() {
        List<BarEntry> valsComp1 = new ArrayList<BarEntry>();
        List<BarEntry> valsComp2 = new ArrayList<BarEntry>();
        //幻灵新能源公司四个季度收入
        valsComp1.add(new BarEntry(0, 1009000f));
        valsComp1.add(new BarEntry(1, 1408000f));
        valsComp1.add(new BarEntry(2, 1205000f));
        valsComp1.add(new BarEntry(3, 1855000f));
        BarDataSet barDataSet1 = new BarDataSet(valsComp1,"幻灵新能源公司");
        barDataSet1.setColor(Color.BLUE);

        //幻灵智能通信公司四个季度收入
        valsComp2.add(new BarEntry(0, 1308000f));
        valsComp2.add(new BarEntry(1, 1150000f));
        valsComp2.add(new BarEntry(2, 1006000f));
        valsComp2.add(new BarEntry(3, 1255000f));
        BarDataSet barDataSet2 = new BarDataSet(valsComp2,"幻灵智能通信公司");
        barDataSet2.setColor(Color.GREEN);


        BarData barData = new BarData(barDataSet1,barDataSet2);
        barData.setValueFormatter(new LargeValueFormatter());
        barData.setValueTextColor(getResources().getColor(R.color.colorAccent));
        //设置柱子宽度,(barWidth+barSpace)*2+groupSpace=1
        float groupSpace = 0.1f;
        float barSpace = 0.05f;
        float barWidth = 0.4f;
        barData.setBarWidth(barWidth);
        barData.groupBars(0, groupSpace, barSpace);
        barChart.setData(barData);
        barChart.getBarData().setBarWidth(barWidth);
        //下面三行为设置X轴标签居中显示，且让所有图在当前页显示完
        barChart.getXAxis().setAxisMinimum(0);
        barChart.getXAxis().setAxisMaximum(0 +barChart.getBarData().getGroupWidth(groupSpace, barSpace) * 4);
        barChart.getXAxis().setCenterAxisLabels(true);//设置标签居中

        barChart.setFitBars(true); //X轴自适应所有柱形图
        barChart.invalidate();
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
        description.setTextColor(getResources().getColor(R.color.colorAccent));
        description.setTypeface(Typeface.DEFAULT_BOLD);
        barChart.setDescription(description);

    }

    /**
     * 绘制X轴
     */
    private void setXAxis(){
        //获取X轴对象，设置X轴
        XAxis xAxis = barChart.getXAxis();
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

        // 设置 x 轴 从0开始 默认不是从 0
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(4);

    }

    /**
     * 绘制Y轴
     */
    private void setYAxis(){
        //获取左边Y轴对象，设置左边Y轴
        YAxis yLeftAxis = barChart.getAxisLeft();
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
        //左侧Y轴网格线设置为虚线
        yLeftAxis.enableGridDashedLine(10f, 10f, 0f);

        YAxis yRightAxis = barChart.getAxisRight();
        yRightAxis.setEnabled(false);


    }

    /**
     * 设置X轴坐标值，横坐标为季度
     */
    private void setXAxisLabelValue(final XAxis xAxis){
        final String[] quarters = new String[] { "第一季度", "第二季度", "第三季度", "第四季度" };
        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                //这里是兼容数组越界的错误
                int index = (int) value;
                if (index < 0 || index >= xAxis.getLabelCount()) {
                    return "";
                } else {
                    return quarters[(int) Math.abs(value)];
                }
            }
        };
        xAxis.setValueFormatter(formatter);
    }
}
