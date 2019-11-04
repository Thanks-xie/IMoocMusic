package com.xie.com.imoocmusic.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.xie.com.imoocmusic.R;

import java.text.DecimalFormat;


/**
 * 椭圆形弹出框
 * @author xiejinbo
 * @date 2019/10/31 0031 9:50
 */
public class MyCustomMarkerView extends MarkerView {
    private TextView tvContent;
    private DecimalFormat mFormat;
    private MPPointF mOffset;
    private static String[] SUFFIX = new String[]{
            "元", "万元"
    };
    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    public MyCustomMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
        tvContent = findViewById(R.id.tvContent);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        mFormat = new DecimalFormat("####E0");
        String r = mFormat.format(e.getY());
        r = r.replaceAll("E[0-9]",
                SUFFIX[Character.getNumericValue(r.charAt(r.length() - 1)) / 4]);
        tvContent.setText(""+r);
        tvContent.setTextColor(getResources().getColor(R.color.colorAccent));
        tvContent.setTextSize(12f);

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        if(mOffset == null) {
            // center the marker horizontally and vertically
            mOffset = new MPPointF(-(getWidth() / 2), -getHeight());

        }
        return mOffset;
    }
}
