package com.xie.com.imoocmusic.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xie.com.imoocmusic.R;

import java.util.ArrayList;

import im.dacer.androidcharts.BarView;

public class BarChartActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        initNavBar(true,"柱形图",false);
        final BarView barView = (BarView)findViewById(R.id.bar_view);
        Button button = (Button)findViewById(R.id.bar_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                randomSet(barView);
            }
        });
        randomSet(barView);
    }

    private void randomSet(BarView barView) {
        int random = (int) (Math.random() * 20) + 6;
        ArrayList<String> test = new ArrayList<String>();
        for (int i = 0; i < random; i++) {
            test.add("test");
            test.add("pqg");
            //            test.add(String.valueOf(i+1));
        }
        barView.setBottomTextList(test);

        ArrayList<Integer> barDataList = new ArrayList<Integer>();
        for (int i = 0; i < random * 2; i++) {
            barDataList.add((int) (Math.random() * 100));
        }
        barView.setDataList(barDataList, 100);
    }
}
