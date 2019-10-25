package com.xie.com.imoocmusic.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xie.com.imoocmusic.R;

import java.util.List;
import java.util.zip.Inflater;

public class FlexBoxLayoutManagerAdapter extends RecyclerView.Adapter<FlexBoxLayoutManagerAdapter.ViewHolder> {
    private List<String> mDatas;
    private Context mContext;
    private LayoutInflater inflater;

    public FlexBoxLayoutManagerAdapter(Context context,List<String> datas){
        mContext = context;
        mDatas = datas;
        inflater = LayoutInflater.from(mContext);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_tag,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv);
        }
    }

}
