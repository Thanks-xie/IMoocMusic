package com.xie.com.imoocmusic.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xie.com.imoocmusic.R;
import com.xie.com.imoocmusic.adapters.ChatFriendListAdapter;
import com.xie.com.imoocmusic.adapters.LiveTVAdapter;
import com.xie.com.imoocmusic.domain.LiveTvModel;

import java.util.ArrayList;
import java.util.List;

public class LiveTVActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private LiveTVAdapter liveTVAdapter;
    private List<LiveTvModel> listTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_tv);
        initView();
        initData();
    }

    private void initView(){
        initNavBar(true,"直播电视",false);
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void initData(){
        setLiveTVData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        liveTVAdapter = new LiveTVAdapter(this,listTv);
        recyclerView.setAdapter(liveTVAdapter);
    }

    private void setLiveTVData(){
        listTv = new ArrayList<>();

        String[] tvName = {"CCTV1综合","CCTV2财经","CCTV3综艺","CCTV4中文国际","CCTV5体育","CCTV6电影","CCTV7军事农业","CCTV8电视剧","CCTV9记录","CCTV10科教","湖南卫视","浙江卫视"};
        String[] tvPath = {
                "http://39.134.65.162/PLTV/88888888/224/3221225493/index.m3u8",
                "http://117.148.187.37/PLTV/88888888/224/3221226138/index.m3u8",
                "http://ivi.bupt.edu.cn/hls/cctv3hd.m3u8",
                "http://124.90.32.171:6610/zjhs/2/10074/index.m3u8?virtualDomain=zjhs.live_hls.zte.com",
                "http://223.110.245.136/PLTV/3/224/3221227166/index.m3u8",
                "http://ivi.bupt.edu.cn/hls/cctv6hd.m3u8",
                "http://183.252.176.59//PLTV/88888888/224/3221225927/index.m3u8",
                "http://ivi.bupt.edu.cn/hls/cctv8hd.m3u8",
                "http://183.252.176.36//PLTV/88888888/224/3221225929/index.m3u8",
                "http://183.252.176.13//PLTV/88888888/224/3221225931/index.m3u8",
                "http://39.134.52.182/wh7f454c46tw1696467131_1868720566/hwottcdn.ln.chinamobile.com/PLTV/88888890/224/3221225981/index.m3u8",
                "http://183.252.176.35//PLTV/88888888/224/3221225934/index.m3u8"};
        for (int i=0;i<tvName.length;i++){
            LiveTvModel liveTvModel = new LiveTvModel();
            liveTvModel.setTvName(tvName[i]);
            liveTvModel.setTvPath(tvPath[i]);
            listTv.add(liveTvModel);
        }

    }
}
