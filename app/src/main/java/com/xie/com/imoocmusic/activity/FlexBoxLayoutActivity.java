package com.xie.com.imoocmusic.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.xie.com.imoocmusic.R;

import java.util.Arrays;
import java.util.List;

public class FlexBoxLayoutActivity extends BaseActivity {

    private FlexboxLayout flexboxLayout;
    private static final List<String> mDataList = Arrays.asList(
            "Android","Java","C","C++","C#","HTML","CSS","Jquery","Javascript","C#","Android","Java","C","C++","C#","HTML","CSS","Jquery","Javascript");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flex_box_layout);

        initView();
    }

    private void initView() {
        initNavBar(true,"FlexBoxLayout布局",false);
        flexboxLayout = findViewById(R.id.flexBox);
    }

    public void AddFlexBoxTag(View view) {
        TextView textView = (TextView) LayoutInflater.from(this).inflate(R.layout.item_tag,flexboxLayout,false);
        textView.setText(mDataList.get((int) (Math.random() *(mDataList.size()-1))));
        flexboxLayout.addView(textView);
    }
}
