package com.xie.com.imoocmusic.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xie.com.imoocmusic.R;
import com.xie.com.imoocmusic.activity.LiveTVActivity;
import com.xie.com.imoocmusic.adapters.LiveTVAdapter;

/**
 * 直播间
 */
public class LiveRoomFragment extends Fragment {
    private LinearLayout liveTv,vodTv,liveRoom;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        View chatView = inflater.inflate(R.layout.activity_tab_live, container,false);
        initView(chatView);
        setOnclick();
        return chatView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    /**
     *
     * @param view
     */
    private void initView(View view){
        liveTv = view.findViewById(R.id.liveTv);
        vodTv = view.findViewById(R.id.vodTv);
        liveRoom = view.findViewById(R.id.liveRoom);
    }

    /**
     * 点击事件
     */
    private void setOnclick(){
        liveTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LiveTVActivity.class);
                startActivity(intent);
            }
        });
    }
}
