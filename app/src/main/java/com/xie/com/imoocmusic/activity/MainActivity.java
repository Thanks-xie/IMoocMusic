package com.xie.com.imoocmusic.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.xie.com.imoocmusic.R;
import com.xie.com.imoocmusic.adapters.MusicGridAdapter;
import com.xie.com.imoocmusic.adapters.NewMusicListAdapter;
import com.xie.com.imoocmusic.domain.MusicResourceModel;
import com.xie.com.imoocmusic.help.RealmHelp;

public class MainActivity extends BaseActivity {

    private RecyclerView recycler,listRectcler;
    private MusicGridAdapter musicGridAdapter;
    private NewMusicListAdapter newMusicListAdapter;
    private RealmHelp mRealmHelper;
    private MusicResourceModel mMusicSourceModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ininData();
        initView();
    }

    /**
     * 初始化加载
     */
    private void initView(){
        initNavBar(false,"波浪谷",true);

        //推荐歌单
        recycler = findViewById(R.id.recyclerView);
        recycler.setLayoutManager(new GridLayoutManager(this,3));
        //禁止滑动，解决滑动冲突
        recycler.setNestedScrollingEnabled(false);
        musicGridAdapter = new MusicGridAdapter(this,mMusicSourceModel.getAlbum());
        recycler.setAdapter(musicGridAdapter);

        //最热音乐
        listRectcler = findViewById(R.id.rv_list);
        listRectcler.setLayoutManager(new LinearLayoutManager(this));
        listRectcler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //禁止滑动，解决滑动冲突
        listRectcler.setNestedScrollingEnabled(false);
        newMusicListAdapter = new NewMusicListAdapter(this,listRectcler,mMusicSourceModel.getHot());
        listRectcler.setAdapter(newMusicListAdapter);
    }

    /**
     * 数据加载
     */
    private void ininData(){
        mRealmHelper = new RealmHelp();
        mMusicSourceModel = mRealmHelper.getMusicSource();
        if (mMusicSourceModel==null){
            mRealmHelper.setMusicSource(this);
            mMusicSourceModel = mRealmHelper.getMusicSource();
        }
    }

}
