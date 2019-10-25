package com.xie.com.imoocmusic.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.request.RequestOptions;
import com.xie.com.imoocmusic.R;
import com.xie.com.imoocmusic.domain.MusicModel;
import com.xie.com.imoocmusic.help.RealmHelp;
import com.xie.com.imoocmusic.views.PlayMusicView;

import java.security.MessageDigest;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class PlayMusicActivity extends BaseActivity {

    private ImageView play_bg;
    private PlayMusicView playMusicView;
    private String mMusicId;
    private RealmHelp mRealmHelper;
    private MusicModel mMusicModel;
    private TextView musicName,musicAuthor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        //隐藏navbar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initData();
        initView();
    }

    private void initView(){
        play_bg = findViewById(R.id.play_bg);
        musicAuthor = findViewById(R.id.music_author);
        musicName = findViewById(R.id.music_name);

        //利用glide-transformations加载模糊背景图
        Glide.with(this).load(mMusicModel.getPoster())
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25,10)))
                .into(play_bg);
        musicName.setText(mMusicModel.getName());
        musicAuthor.setText(mMusicModel.getAuthor());

        playMusicView = findViewById(R.id.play_music_view);
        playMusicView.setMusic(mMusicModel);
        playMusicView.playMusic();
    }

    /**
     * 返回
     * @param view
     */
    public void onBackClick(View view){
        onBackPressed();
    }

    /**
     * 数据加载
     */
    private void initData(){
        mMusicId = getIntent().getStringExtra("musicId");
        mRealmHelper = new RealmHelp();
        mMusicModel = mRealmHelper.getMusic(mMusicId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        playMusicView.destroy();
        mRealmHelper.close();
    }
}
