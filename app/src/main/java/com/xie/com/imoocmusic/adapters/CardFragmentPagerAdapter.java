package com.xie.com.imoocmusic.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.xie.com.imoocmusic.domain.QuestionInfo;
import com.xie.com.imoocmusic.fragment.CardFragment;

import java.util.List;

public class CardFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private List<QuestionInfo> infoList;

    public CardFragmentPagerAdapter(FragmentManager fm,List<QuestionInfo> list) {
        super(fm);
        this.infoList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return CardFragment.newInstance(infoList.get(position));
    }

    @Override
    public int getCount() {
        return infoList.size();
    }
}
