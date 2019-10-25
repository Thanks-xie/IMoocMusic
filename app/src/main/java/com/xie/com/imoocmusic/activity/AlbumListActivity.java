package com.xie.com.imoocmusic.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xie.com.imoocmusic.R;
import com.xie.com.imoocmusic.adapters.AlbumListAdapter;
import com.xie.com.imoocmusic.adapters.NewMusicListAdapter;
import com.xie.com.imoocmusic.domain.AlbumModel;
import com.xie.com.imoocmusic.help.RealmHelp;

public class AlbumListActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private NewMusicListAdapter newMusicListAdapter;
    private String mAlbumId;
    private RealmHelp mRealmHelper;
    private AlbumModel mAlbumModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);
        initData();
        initView();
    }

    private void initView(){
        initNavBar(true,"专辑列表",false);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        newMusicListAdapter = new NewMusicListAdapter(this,null,mAlbumModel.getList());
        recyclerView.setAdapter(newMusicListAdapter);
    }

    /**
     * 数据加载
     */
    private void initData(){
        Intent intent = getIntent();
        mAlbumId = intent.getStringExtra("albumId");
        mRealmHelper = new RealmHelp();
        mAlbumModel = mRealmHelper.getAlbum(mAlbumId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealmHelper.close();
    }
}
