package com.xie.com.imoocmusic.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class TagAdapter {

    public  abstract int getItemCount();

    public abstract View CreateView(LayoutInflater inflater, ViewGroup parent,int position);

    public abstract void bindView(View view,int position);

    public void onItemViewClick(int position, View view){

    }

    public  void tipForSelectMax(View v, int maxSelectedCount){

    }

    public void onItemSelected(View view,int position){

    }
    public void onItemUnSelected(View view ,int position){

    }

    public void notifyDataSetChanged(){

    }
}
