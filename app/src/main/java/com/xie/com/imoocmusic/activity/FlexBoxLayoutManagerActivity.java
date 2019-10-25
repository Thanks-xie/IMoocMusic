package com.xie.com.imoocmusic.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.google.android.flexbox.FlexboxLayoutManager;
import com.xie.com.imoocmusic.R;
import com.xie.com.imoocmusic.adapters.FlexBoxLayoutManagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FlexBoxLayoutManagerActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private List<String> mDatas = new ArrayList<>();
    private FlexBoxLayoutManagerAdapter managerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flex_box_layout_manager);

        initView();
    }

    private void initView() {
        initNavBar(true,"FlexBoxLayoutManager布局",false);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new FlexboxLayoutManager(this));
        mDatas.clear();
        for (int i=0;i<400;i++){
            mDatas.add("Android" + i);
        }
        managerAdapter = new FlexBoxLayoutManagerAdapter(this,mDatas);
        recyclerView.setAdapter(managerAdapter);
    }
}
