package com.xie.com.imoocmusic.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.xie.com.imoocmusic.R;
import com.xie.com.imoocmusic.activity.LivePlayTvActivity;
import com.xie.com.imoocmusic.domain.LiveTvModel;

import java.util.ArrayList;
import java.util.List;


public class LiveTVAdapter extends RecyclerView.Adapter<LiveTVAdapter.ViewHolder> {

    private Context mContext;
    List<LiveTvModel> listTv;
    public LiveTVAdapter(Context context,List<LiveTvModel> list){
        this.mContext = context;
        this.listTv = list;
    }

    @Override
    public LiveTVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.activity_live_tv_adapter,parent,false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final LiveTvModel liveTvModel = listTv.get(position);
        holder.liveTVName.setText(liveTvModel.getTvName());

        holder.to_live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, LivePlayTvActivity.class);
                intent.putExtra("path",liveTvModel.getTvPath());
                intent.putExtra("tvName",liveTvModel.getTvName());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTv.size();
    }

    class  ViewHolder extends RecyclerView.ViewHolder{
        TextView liveTVName;
        LinearLayout to_live;

        public ViewHolder(View itemView) {
            super(itemView);
            to_live =itemView.findViewById(R.id.to_live);
            liveTVName = itemView.findViewById(R.id.tv_name);
        }
    }

}
