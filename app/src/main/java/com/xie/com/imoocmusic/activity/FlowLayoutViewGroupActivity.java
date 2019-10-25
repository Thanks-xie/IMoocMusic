package com.xie.com.imoocmusic.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.xie.com.imoocmusic.R;
import com.xie.com.imoocmusic.views.FlowLayoutViewGroup;

import java.util.Arrays;
import java.util.List;

public class FlowLayoutViewGroupActivity extends BaseActivity {

    private FlowLayoutViewGroup layoutViewGroup;
    private static final List<String> mDataList = Arrays.asList(
            "Android","Java","C","C++","C#","HTML","CSS","Jquery","Javascript","C#","Android","Java","C","C++","C#","HTML","CSS","Jquery","Javascript");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout_view_group);

        initView();
    }

    private void initView() {
        initNavBar(true,"流式布局",false);
        layoutViewGroup = findViewById(R.id.flowLayout);
    }

    public void AddTextviewTag(View view) {
        TextView textView = (TextView) LayoutInflater.from(this).inflate(R.layout.item_tag,layoutViewGroup,false);
        textView.setText(mDataList.get((int) (Math.random() *(mDataList.size()-1))));
        layoutViewGroup.addView(textView);

    }
}
