package com.xie.com.imoocmusic.adapters;


import android.content.Context;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xie.com.imoocmusic.R;
import com.xie.com.imoocmusic.activity.AlbumListActivity;
import com.xie.com.imoocmusic.activity.MainActivity;
import com.xie.com.imoocmusic.domain.AlbumModel;

import java.util.List;

import cn.salesuite.saf.inject.annotation.InjectView;

public class MusicGridAdapter extends RecyclerView.Adapter<MusicGridAdapter.ViewHolder> {

    private  Context mContext;
    private List<AlbumModel> list;
    public  MusicGridAdapter (Context context, List<AlbumModel> albumModelList){
        this.mContext = context;
        this.list = albumModelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.music_grid_adapter,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        final AlbumModel albumModel = list.get(i);

        Glide.with(mContext).load(albumModel.getPoster()).into(viewHolder.imageView);
        viewHolder.albumName.setText(albumModel.getName());
        viewHolder.playNum.setText(albumModel.getPlayNum());



        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,AlbumListActivity.class);
                intent.putExtra("albumId",albumModel.getAlbumId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView albumName,playNum;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_icon);
            albumName = itemView.findViewById(R.id.album_name);
            playNum = itemView.findViewById(R.id.play_num);
        }
    }

}
