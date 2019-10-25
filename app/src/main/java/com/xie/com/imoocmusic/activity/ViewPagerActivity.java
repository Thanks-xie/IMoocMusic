package com.xie.com.imoocmusic.activity;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.xie.com.imoocmusic.R;
import com.xie.com.imoocmusic.adapters.CardFragmentPagerAdapter;
import com.xie.com.imoocmusic.domain.QuestionInfo;
import com.xie.com.imoocmusic.presenter.TestPresenter;
import com.xie.com.imoocmusic.views.ITextView;

import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

import io.vov.vitamio.utils.Log;

public class ViewPagerActivity extends FragmentActivity implements ITextView{

    private ViewPager viewPager;
    private TextView textView;
    private ImageView back;
    private CardFragmentPagerAdapter adapter;
    private List<QuestionInfo> infoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        initView();
        initData();
    }
    /**
     * 获取数据
     */
    private void initData() {
        TestPresenter presenter = new TestPresenter(this);
        presenter.getData();
    }

    /**
     * 初始化
     */
    private void initView() {
        viewPager = findViewById(R.id.viewPager);
        textView = findViewById(R.id.bottom_text);
        back = findViewById(R.id.nav_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void updateUI(List<QuestionInfo> list) {
        Log.e("xiejinbo","list: "+ JSON.toJSONString(list));
        Collections.reverse(list);
        adapter = new CardFragmentPagerAdapter(getSupportFragmentManager(),list);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setCurrentItem(list.size()-1);
        //viewPager.setPageTransformer(true,new CardTransformer());
    }
}
