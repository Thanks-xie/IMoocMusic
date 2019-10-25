package com.xie.com.imoocmusic.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xie.com.imoocmusic.R;
import com.xie.com.imoocmusic.activity.AlbumListActivity;
import com.xie.com.imoocmusic.activity.PlayMusicActivity;
import com.xie.com.imoocmusic.domain.MusicModel;

import java.util.List;

public class NewMusicListAdapter extends RecyclerView.Adapter<NewMusicListAdapter.ViewHolder>{

    private Context mContext;
    private View mItemView;
    private RecyclerView mRv;
    private boolean isSetRecyclerViewHeight;
    private List<MusicModel> musicModels;
    public NewMusicListAdapter(Context context, RecyclerView recyclerView, List<MusicModel> musicModelList){
        this.mContext = context;
        this.mRv = recyclerView;
        this.musicModels = musicModelList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mItemView=  LayoutInflater.from(mContext).inflate(R.layout.new_music_list_adapter,parent,false);
        return new ViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        setRecyclerViewHeight();
        final  MusicModel musicModel = musicModels.get(position);
        Glide.with(mContext).load(musicModel.getPoster()).into(holder.imageView);
        holder.musicAuthor.setText(musicModel.getAuthor());
        holder.musicName.setText(musicModel.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,PlayMusicActivity.class);
                intent.putExtra("musicId",musicModel.getMusicId());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return musicModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView musicName,musicAuthor;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_icon);
            musicName = itemView.findViewById(R.id.music_name);
            musicAuthor = itemView.findViewById(R.id.music_author);
        }
    }

    /**
     * 1.获取itemview高度
     * 2.获取item数量
     * 3.用每个itemview高度*item数量 = RecyclerView高度
     */
    private void setRecyclerViewHeight(){
        if (isSetRecyclerViewHeight||mRv==null) return;

        isSetRecyclerViewHeight = true;

        //        获取ItemView的高度
        RecyclerView.LayoutParams itemViewLP = (RecyclerView.LayoutParams)mItemView.getLayoutParams();
//        itemView的数量
        int itemnum = getItemCount();
//        使用 itemViewHeight * itemViewNum = RecyclerView的高度
        int recyclerViewHeight = itemViewLP.height * itemnum;
//        设置RecyclerView高度
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)mRv.getLayoutParams();
        layoutParams.height = recyclerViewHeight;
        mRv.setLayoutParams(layoutParams);


    }
}
