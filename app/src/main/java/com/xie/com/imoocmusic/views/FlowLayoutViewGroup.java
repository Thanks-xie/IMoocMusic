package com.xie.com.imoocmusic.views;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.nfc.FormatException;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.xie.com.imoocmusic.R;

import java.util.ArrayList;
import java.util.List;

public class FlowLayoutViewGroup extends ViewGroup {
    private List<List<View>> mAllView = new ArrayList<>();
    private List<Integer> mLineHeight = new ArrayList<>();
    private int maxLine;
    public FlowLayoutViewGroup(Context context) {
        super(context);
    }

    public FlowLayoutViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout);
        maxLine = typedArray.getInt(R.styleable.FlowLayout_maxline, Integer.MAX_VALUE);
        typedArray.recycle();

        Log.e("xiejinbo","maxLine: "+maxLine);

    }

    /**
     * layout宽度一定，高度有三种模式
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mAllView.clear();
        mLineHeight.clear();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //widthMeasureSpec和heightMeasureSpec包含两个字段（建议宽度或高度+mode）
        //1.300dp+exactly
        //2.parent_width + at_most
        //3.0,parent_width +unspecified

        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);

        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);



        int lineWidth = 0;
        int lineHeight = 0;
        int height = 0;
        int mCount = getChildCount();
        List<View> lineViews = new ArrayList<>();
        if (modeHeight == MeasureSpec.EXACTLY){
            height = sizeHeight;
        }else if (modeHeight == MeasureSpec.AT_MOST){
            //遍历所有child的原因是拿到所有child的高度，并设置给容器
            for (int i=0;i<mCount;i++){
                View child = getChildAt(i);
                if (child.getVisibility() == View.GONE){
                    continue;
                }
                //child也要确定宽高,child也有size+mode
                //1.依赖xml中设置的300dp，match_parent,wrap_content
                //2.父控件的mode
                measureChild(child,widthMeasureSpec,heightMeasureSpec);

                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

                int cWidth = child.getMeasuredWidth()+lp.leftMargin+lp.rightMargin;
                int cHeight = child.getMeasuredHeight()+lp.topMargin+lp.bottomMargin;

                //如果当前宽度和大于容器宽度，要做换行处理
                Log.e("xiejinbo","sizeWidth: "+sizeWidth);
                Log.e("xiejinbo","lineWidth+cWidth: "+(lineWidth+cWidth));
                if (lineWidth+cWidth > sizeWidth-(getPaddingLeft()+getPaddingRight())){
                    //换行
                    height +=lineHeight;
                    mAllView.add(lineViews);
                    mLineHeight.add(lineHeight);

                    lineWidth = cWidth;
                    lineHeight = cHeight;
                    lineViews = new ArrayList<>();
                    lineViews.add(child);

                }else {
                    //未换行
                    lineWidth += cWidth;
                    lineHeight = Math.max(lineHeight,cHeight);
                    lineViews.add(child);
                }
                if (i == mCount-1){ //最后一行
                    height += lineHeight;
                    mLineHeight.add(lineHeight);
                    mAllView.add(lineViews);
                }
            }

            //maxLine校正
            if (maxLine < mLineHeight.size()){
                height = getMaxLineHeight();
            }

            height = Math.min(sizeHeight,height);
            height = height + getPaddingTop() +getPaddingBottom();
        }else if (modeHeight == MeasureSpec.UNSPECIFIED){
            height = height + getPaddingTop() +getPaddingBottom();
        }
        setMeasuredDimension(sizeWidth,height);
    }

    private int getMaxLineHeight() {
        int height = 0;
        for (int i=0;i<maxLine;i++){
            height += mLineHeight.get(i);
        }
        return height;
    }

    /**
     * 摆放view
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left = getPaddingLeft();
        int top = getPaddingTop();
        int lineNums = mAllView.size();
        for (int i=0;i<lineNums;i++){
            List<View> lineViews = mAllView.get(i);
            int lineHeight = mLineHeight.get(i);

            for (int j=0;j<lineViews.size();j++){ //在每一行摆放每一个view
                View child = lineViews.get(j);
             MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
             //l t r b
                int leftChild = left + lp.leftMargin;
                int topChild = top + lp.topMargin;
                int rightChild = leftChild + child.getMeasuredWidth();
                int bottomChild = topChild + child.getMeasuredHeight();
                child.layout(leftChild,topChild,rightChild,bottomChild);
                left += child.getMeasuredWidth() + lp.leftMargin +lp.rightMargin;
            }
            left = getPaddingLeft();
            top += lineHeight;
        }
    }

    /**
     * child直接new，没有设置LayoutParams
     * @return
     */
    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
    }
    //in
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }
    //addview
    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }
    //addview
    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof MarginLayoutParams;
    }
}
