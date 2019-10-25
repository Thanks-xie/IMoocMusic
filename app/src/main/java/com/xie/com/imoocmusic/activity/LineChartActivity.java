package com.xie.com.imoocmusic.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xie.com.imoocmusic.R;

import java.util.ArrayList;

import im.dacer.androidcharts.LineView;

public class LineChartActivity extends BaseActivity {

    private int randomint = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);
        initNavBar(true,"折线图",false);
        final LineView lineView = findViewById(R.id.line_view);
        final LineView lineViewFloat = findViewById(R.id.line_view_float);

        initLineView(lineView);
        initLineView(lineViewFloat);
        Button lineButton = (Button) findViewById(R.id.line_button);
        lineButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                randomSet(lineView, lineViewFloat);
            }
        });

        randomSet(lineView, lineViewFloat);

    }

    private void initLineView(LineView lineView) {
        ArrayList<String> test = new ArrayList<String>();
        for (int i = 0; i < randomint; i++) {
            test.add(String.valueOf(i + 1));
        }
        lineView.setBottomTextList(test);
        lineView.setColorArray(new int[] {
                Color.parseColor("#F44336"), Color.parseColor("#9C27B0"),
                Color.parseColor("#2196F3"), Color.parseColor("#009688")
        });
        lineView.setDrawDotLine(true);
        lineView.setShowPopup(LineView.SHOW_POPUPS_NONE);
    }

    private void randomSet(LineView lineView, LineView lineViewFloat) {
        ArrayList<Integer> dataList = new ArrayList<>();
        float random = (float) (Math.random() * 9 + 1);
        for (int i = 0; i < randomint; i++) {
            dataList.add((int) (Math.random() * random));
        }

        ArrayList<Integer> dataList2 = new ArrayList<>();
        random = (int) (Math.random() * 9 + 1);
        for (int i = 0; i < randomint; i++) {
            dataList2.add((int) (Math.random() * random));
        }

        ArrayList<Integer> dataList3 = new ArrayList<>();
        random = (int) (Math.random() * 9 + 1);
        for (int i = 0; i < randomint; i++) {
            dataList3.add((int) (Math.random() * random));
        }

        ArrayList<ArrayList<Integer>> dataLists = new ArrayList<>();
        dataLists.add(dataList);
        dataLists.add(dataList2);
        dataLists.add(dataList3);

        lineView.setDataList(dataLists);

        ArrayList<Float> dataListF = new ArrayList<>();
        float randomF = (float) (Math.random() * 9 + 1);
        for (int i = 0; i < randomint; i++) {
            dataListF.add((float) (Math.random() * randomF));
        }

        ArrayList<Float> dataListF2 = new ArrayList<>();
        randomF = (int) (Math.random() * 9 + 1);
        for (int i = 0; i < randomint; i++) {
            dataListF2.add((float) (Math.random() * randomF));
        }

        ArrayList<Float> dataListF3 = new ArrayList<>();
        randomF = (int) (Math.random() * 9 + 1);
        for (int i = 0; i < randomint; i++) {
            dataListF3.add((float) (Math.random() * randomF));
        }

        ArrayList<ArrayList<Float>> dataListFs = new ArrayList<>();
        dataListFs.add(dataListF);
        dataListFs.add(dataListF2);
        dataListFs.add(dataListF3);

        lineViewFloat.setFloatDataList(dataListFs);
    }

}
