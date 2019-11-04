package com.xie.com.imoocmusic.utils;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.Utils;
import com.xie.com.imoocmusic.R;

import java.util.List;

/**
 * PieChart 工具类
 * @author xiejinbo
 * @date 2019/11/1 0001 13:20
 */
public class PieChartUtil {

    /**
     * 设置PieChart的数据
     * @param valsComp 展示数据
     * @param colors    每个区域的颜色
     * @param pieChart
     */
    public static void setPieChartData(List<PieEntry> valsComp, List<Integer> colors, PieChart pieChart){
        PieDataSet pieDataSet = new PieDataSet(valsComp,"");
        pieDataSet.setColors(colors);
        pieDataSet.setSliceSpace(3f); //两个区域之间的空格

        PieData pieData = new PieData(pieDataSet);
        pieData.setValueFormatter(new LargeValueFormatter());
        pieData.setDrawValues(true);
        pieData.setValueTextSize(12f);
        pieChart.setDrawSliceText(false);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    /**
     * PieChart的设置
     * @param context
     * @param pieChart
     * @param descriptionStr
     */
    public static void setPieChartSetting(Context context,PieChart pieChart,String descriptionStr){

        Description description = new Description();
        description.setText(descriptionStr);
        description.setTextSize(14f);
        //设置文字颜色
        description.setTextColor(context.getResources().getColor(R.color.colorAccent));
        description.setTypeface(Typeface.DEFAULT_BOLD);
        setDescriptionPosition(context,pieChart,descriptionStr,description);

        //设置表注解label
        Legend legend = pieChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setDrawInside(true);
    }

    /**
     * 设置描述文本位置：居中
     */
    public static void setDescriptionPosition(Context context,PieChart pieChart,String descriptionStr, Description description) {
        // 获取屏幕中间x 轴的像素坐标
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);


        // y轴像素坐标，获取文本高度（dp）+上方间隔12dp 转换为像素
        Paint paint = new Paint();
        paint.setTextSize(14f);
        float x = (dm.widthPixels- Utils.calcTextWidth(paint,descriptionStr))/2;
        float height = Utils.calcTextHeight(paint, descriptionStr);
        float y = Utils.convertDpToPixel(height + 12);
        // 设置饼状图的位置
        description.setPosition(x, y);
        description.setTextAlign(Paint.Align.CENTER);
        pieChart.setDescription(description);
        //设置上边距
        pieChart.setExtraTopOffset(25f);
        pieChart.setCenterText("营业额");
        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);
        pieChart.animateY(1000, Easing.EaseInOutQuad);
    }

}
