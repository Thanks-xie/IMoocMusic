package com.xie.com.imoocmusic.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.xie.com.imoocmusic.R;
import com.xie.com.imoocmusic.activity.MainActivity;
import com.xie.com.imoocmusic.activity.PlayMusicActivity;


public class AlbumListAdapter extends RecyclerView.Adapter<AlbumListAdapter.ViewHolder> {

    private Context mContext;
    public AlbumListAdapter (Context context){
        this.mContext = context;
    }

    @Override
    public AlbumListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.album_music_list_adapter,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(mContext).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561027386341&di=9104a579fc43965bd18dc0e8c6d54586&imgtype=0&src=http%3A%2F%2Fpic31.nipic.com%2F20130728%2F3822951_140613063000_2.jpg")
                .into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,PlayMusicActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class  ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_icon);
        }
    }

}
