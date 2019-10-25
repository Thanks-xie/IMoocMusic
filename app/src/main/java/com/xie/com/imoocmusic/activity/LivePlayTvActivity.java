package com.xie.com.imoocmusic.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xie.com.imoocmusic.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;


public class LivePlayTvActivity extends BaseActivity {

    private String playPath;
    private String tvName;
    private ImageView back,playState;
    private TextView tv_title,sysTime;
    private VideoView videoView;
    private static final int RETRY_TIME = 5;
    private int count = 0;
    private RelativeLayout loading,rootView;
    private LinearLayout topView,bottomView;
    private Handler handler = new Handler(Looper.getMainLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_live_play_tv);

        Intent intent = getIntent();
        playPath = intent.getStringExtra("path");
        tvName = intent.getStringExtra("tvName");
        initView();
        initPlayer();

    }

    /**
     * 初始化Vitamio播放
     */
    private void initPlayer() {

        Vitamio.isInitialized(getApplicationContext());
        videoView = findViewById(R.id.surface_view);
        videoView.setVideoURI(Uri.parse(playPath));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {   //开始播放
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {   //播放出错
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                if (count>RETRY_TIME){
                    new AlertDialog.Builder(LivePlayTvActivity.this)
                            .setMessage("播放出错啦")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    LivePlayTvActivity.this.finish();
                                }
                            }).setCancelable(false).show();
                }else {
                    videoView.stopPlayback();
                    videoView.setVideoURI(Uri.parse(playPath));
                }
                count++;
                return false;
            }
        });

        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {   //缓冲
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                switch (what){
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        //正在缓冲
                        loading.setVisibility(View.VISIBLE);
                        break;
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                    case MediaPlayer.MEDIA_INFO_VIDEO_TRACK_LAGGING:
                        //停止缓冲
                        loading.setVisibility(View.GONE);
                        break;

                }
                return false;
            }
        });

    }

    private void initView(){
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
        tv_title = findViewById(R.id.tv_name);
        tv_title.setText(tvName);
        sysTime = findViewById(R.id.sys_time);
        sysTime.setText(getCurrentTime());
        loading = findViewById(R.id.loading);
        rootView = findViewById(R.id.root_view);
        topView = findViewById(R.id.top_view);
        bottomView = findViewById(R.id.bottom_view);
        playState = findViewById(R.id.play_statue);
        playState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()){
                    videoView.stopPlayback();
                    playState.setImageResource(R.mipmap.playtv);
                }else {
                    videoView.setVideoURI(Uri.parse(playPath));
                    videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            videoView.start();
                        }
                    });
                    playState.setImageResource(R.mipmap.pause);
                }
            }
        });
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (topView.getVisibility()==View.VISIBLE||bottomView.getVisibility()==View.VISIBLE){
                    topView.setVisibility(View.GONE);
                    bottomView.setVisibility(View.GONE);
                }else {
                    topView.setVisibility(View.VISIBLE);
                    bottomView.setVisibility(View.VISIBLE);
                    if (videoView.isPlaying()){
                        playState.setImageResource(R.mipmap.pause);
                    }else {
                        playState.setImageResource(R.mipmap.playtv);
                    }
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        topView.setVisibility(View.GONE);
                        bottomView.setVisibility(View.GONE);
                    }
                },5000);

            }
        });


    }

    private String getCurrentTime(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sf.format(calendar.getTime());
        return time;
    }

    @Override
    protected void onStop() {
        super.onStop();
        count=0;
        if (videoView!=null){
            videoView.stopPlayback();
        }
    }
}
