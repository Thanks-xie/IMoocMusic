package com.xie.com.imoocmusic.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xie.com.imoocmusic.R;

import java.util.ArrayList;

import im.dacer.androidcharts.PieHelper;
import im.dacer.androidcharts.PieView;

public class PieChartActivity extends BaseActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        initNavBar(true,"饼状图",false);
        textView = (TextView)findViewById(R.id.textView);
        final PieView pieView = (PieView)findViewById(R.id.pie_view);
        Button button = (Button) findViewById(R.id.pie_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                randomSet(pieView);
            }
        });
        set(pieView);
    }

    private void randomSet(PieView pieView) {
        ArrayList<PieHelper> pieHelperArrayList = new ArrayList<PieHelper>();
        ArrayList<Integer> intList = new ArrayList<Integer>();
        int totalNum = (int) (5 * Math.random()) + 5;

        int totalInt = 0;
        for (int i = 0; i < totalNum; i++) {
            int ranInt = (int) (Math.random() * 10) + 1;
            intList.add(ranInt);
            totalInt += ranInt;
        }
        for (int i = 0; i < totalNum; i++) {
            pieHelperArrayList.add(new PieHelper(100f * intList.get(i) / totalInt));
        }

        pieView.selectedPie(PieView.NO_SELECTED_INDEX);
        pieView.showPercentLabel(true);
        pieView.setDate(pieHelperArrayList);
    }

    private void set(PieView pieView) {
        ArrayList<PieHelper> pieHelperArrayList = new ArrayList<PieHelper>();
        pieHelperArrayList.add(new PieHelper(20, Color.BLACK));
        pieHelperArrayList.add(new PieHelper(6));
        pieHelperArrayList.add(new PieHelper(30));
        pieHelperArrayList.add(new PieHelper(12));
        pieHelperArrayList.add(new PieHelper(32));

        pieView.setDate(pieHelperArrayList);
        pieView.setOnPieClickListener(new PieView.OnPieClickListener() {
            @Override public void onPieClick(int index) {
                if (index != PieView.NO_SELECTED_INDEX) {
                    textView.setText(index + " selected");
                } else {
                    textView.setText("No selected pie");
                }
            }
        });
        pieView.selectedPie(2);
    }

}
