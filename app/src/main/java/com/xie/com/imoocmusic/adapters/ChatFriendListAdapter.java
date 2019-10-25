package com.xie.com.imoocmusic.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.xie.com.imoocmusic.R;
import com.xie.com.imoocmusic.activity.PlayMusicActivity;

import java.util.ArrayList;
import java.util.List;


public class ChatFriendListAdapter extends RecyclerView.Adapter<ChatFriendListAdapter.ViewHolder> {

    private Context mContext;
    private int width;
    List<String> list = new ArrayList<>();
    public ChatFriendListAdapter(Context context){
        this.mContext = context;
        width = mContext.getResources().getDisplayMetrics().widthPixels;
    }

    @Override
    public ChatFriendListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.activity_chat_friend_adapter,parent,false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Glide.with(mContext)
                .load(R.mipmap.img1)
                .into(holder.imageView);
        //list用来记录itemView是否滑开，避免recyclerview复用出现bug
        if (list.contains(position + "")) {
            holder.itemView.scrollTo(holder.delete.getWidth(), 0);
        } else {
            holder.itemView.scrollTo(0, 0);
        }
        //holder.text.setText(items[position]);
        //将item要展示的内容宽度设置为屏幕宽度，删除按钮则会隐藏在屏幕右侧
        ViewGroup.LayoutParams layoutParams = holder.text.getLayoutParams();
        layoutParams.width = width;
        holder.hsv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        //获取滑动的距离
                        int scrollX = holder.hsv.getScrollX();
                        //获取右侧删除按钮的宽度
                        int width = holder.delete.getWidth();
                        //如果滑动距离超过了删除按钮宽度的1/2，则将删除按钮滑出，否则隐藏
                        if (scrollX >= width / 2) {
                            Log.e("xiejinbo","以显示："+ JSON.toJSONString(list));
                            holder.itemView.scrollTo(width, 0);
                            //滑动的item记录下来
                            list.add(position + "");
                        } else {
                            holder.itemView.scrollTo(0, 0);
                            if (list.contains(position + "")) {
                                //记录中移除
                                list.remove(position + "");
                            }
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:

                        break;
                }
                //注意此处返回false
                return false;
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"删除："+position,Toast.LENGTH_SHORT).show();
                holder.itemView.scrollTo(0, 0);
                if (list.contains(position + "")) {
                    //记录中移除
                    list.remove(position + "");
                }
            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"点击好友头像："+position,Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class  ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView delete;
        HorizontalScrollView hsv;
        LinearLayout text;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_icon);
            delete = itemView.findViewById(R.id.delete);
            hsv = itemView.findViewById(R.id.hsv);
            text =itemView.findViewById(R.id.text);
        }
    }

}
