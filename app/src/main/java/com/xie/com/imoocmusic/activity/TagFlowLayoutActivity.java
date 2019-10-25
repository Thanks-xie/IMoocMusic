package com.xie.com.imoocmusic.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.xie.com.imoocmusic.R;
import com.xie.com.imoocmusic.views.TagAdapter;
import com.xie.com.imoocmusic.views.TagFlowLayoutViewGroup;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TagFlowLayoutActivity extends BaseActivity {

    private TagFlowLayoutViewGroup tagFlowLayout;
    private List<String> mDatas = new ArrayList<>(Arrays.asList("The first one is FlexboxLayout that extends the ViewGroup like LinearLayout and RelativeLayout. You can specify the attributes from a layout XML like".split(" ")));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_flow_layout);
        initView();
    }

    private void initView() {
        initNavBar(true,"TagFlowLayout布局",false);
        tagFlowLayout = findViewById(R.id.tagFlowLayout);
        tagFlowLayout.setmMaxSelectedCount(3);
        tagFlowLayout.setTagAddpter(new TagAdapter() {
            @Override
            public int getItemCount() {
                return mDatas.size();
            }

            @Override
            public View CreateView(LayoutInflater inflater, ViewGroup parent, int position) {
                return inflater.inflate(R.layout.item_select_tag,parent,false);
            }

            @Override
            public void bindView(View view, int position) {
                TextView textView = view.findViewById(R.id.tv);
                textView.setText(mDatas.get(position));
            }

            @Override
            public void onItemViewClick(int position, View view) {
                super.onItemViewClick(position, view);
            }

            @Override
            public void tipForSelectMax(View v, int maxSelectedCount) {

                Toast.makeText(v.getContext(),"最多选择"+maxSelectedCount+"个",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemSelected(View view, int position) {
                TextView textView = view.findViewById(R.id.tv);
                textView.setTextColor(Color.BLUE);
            }

            @Override
            public void onItemUnSelected(View view, int position) {
                TextView textView = view.findViewById(R.id.tv);
                textView.setTextColor(Color.BLACK);
            }
        });

    }
}
