package com.xie.com.imoocmusic.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

public class TagFlowLayoutViewGroup extends FlowLayoutViewGroup {
    //FlowLayoutViewGroup,进行测量，布局

    private TagAdapter mAdapter;

    public void setmMaxSelectedCount(int mMaxSelectedCount) {
        this.mMaxSelectedCount = mMaxSelectedCount;
    }

    private int mMaxSelectedCount;


    public TagFlowLayoutViewGroup(Context context) {
        super(context);
    }

    public TagFlowLayoutViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setTagAddpter(TagAdapter tagAddpter){
        mAdapter = tagAddpter;
        onDataChanged();
    }

    private void onDataChanged() {
        removeAllViews();
        TagAdapter adapter = mAdapter;
        for (int i=0;i<adapter.getItemCount();i++){
            View view = adapter.CreateView(LayoutInflater.from(getContext()),this,i);
            adapter.bindView(view,i);
            addView(view);

            if (view.isSelected()){
                mAdapter.onItemSelected(view,i);
            }else {
                mAdapter.onItemUnSelected(view,i);
            }

            bindViewMethod(i,view);
        }
    }

    /**
     * 单选：可以直接选择一个，当选择下一个的时候，上一个选择自动取消
     * 多选：需要手动反选
     * @param position
     * @param view
     */
    private void bindViewMethod(final int position, final View view) {
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.onItemViewClick(position,view);

                if (mMaxSelectedCount<=0){
                    return;
                }
                //特殊case
                if (!v.isSelected()){
                    //view没有被选中
                    if (getSelectViewCount() >= mMaxSelectedCount){ //已经选择到达最大值
                        if (getSelectViewCount() ==1){ //单选情况
                            View selectView = getSelectedView();
                            if (selectView!=null){
                                selectView.setSelected(false);
                                mAdapter.onItemUnSelected(selectView,getPositionForChild(selectView));
                            }
                        }else {
                            //多选
                            mAdapter.tipForSelectMax(v,mMaxSelectedCount);

                            return;
                        }
                    }
                }
                if (v.isSelected()){
                    v.setSelected(false);
                    mAdapter.onItemUnSelected(v,position);
                }else {
                    v.setSelected(true);
                    mAdapter.onItemSelected(v,position);
                }
            }
        });

    }

    private int getPositionForChild(View selectView) {
        for (int i=0;i<getChildCount();i++){
            View view = getChildAt(i);
            if (view ==selectView){
                return i;
            }
        }
        return 0;
    }

    private View getSelectedView() {

        for (int i=0;i<getChildCount();i++){
            View view = getChildAt(i);
            if (view.isSelected()){
                return view;
            }
        }
        return null;
    }

    public int getSelectViewCount() {
        int result = 0;
        for (int i=0;i<getChildCount();i++){
            View view = getChildAt(i);
            if (view.isSelected()){
                result++;
            }
        }
        return result;
    }
}
