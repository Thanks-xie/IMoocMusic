package com.xie.com.imoocmusic.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xie.com.imoocmusic.R;
import com.xie.com.imoocmusic.adapters.FragmentAdapter;
import com.xie.com.imoocmusic.fragment.ChatFragment;
import com.xie.com.imoocmusic.fragment.ContactsFragment;
import com.xie.com.imoocmusic.fragment.LiveRoomFragment;

import java.util.ArrayList;
import java.util.List;

public class IMActivity extends FragmentActivity {

    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;
    private ViewPager mPageVp;
    private TextView mTabChatTv, mTabContactsTv, mTabLiveRoomTv,title;
    private ImageView mTabLineIv,back,me;
    private ChatFragment mChatFg;
    private LiveRoomFragment mLiveRoomFg;
    private ContactsFragment mContactsFg;
    private int currentIndex,screenWidth;
    private LinearLayout tab_chat,tab_contacts,tab_live_room;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im);
        findById();
        init();
        initTabLineWidth();

    }

    private void findById() {
        mTabContactsTv = (TextView) this.findViewById(R.id.id_contacts_tv);
        mTabChatTv = (TextView) this.findViewById(R.id.id_chat_tv);
        mTabLiveRoomTv = (TextView) this.findViewById(R.id.id_liveroom_tv);
        mTabLineIv = (ImageView) this.findViewById(R.id.id_tab_line_iv);
        mPageVp = (ViewPager) this.findViewById(R.id.id_page_vp);
        back = this.findViewById(R.id.nav_back);
        me  = this.findViewById(R.id.nav_me);
        title = this.findViewById(R.id.nav_title);
        tab_chat = this.findViewById(R.id.id_tab_chat);
        tab_contacts = this.findViewById(R.id.id_tab_contacts);
        tab_live_room = this.findViewById(R.id.id_tab_live_room);
    }

    private void init() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        me.setVisibility(View.GONE);
        title.setText("IM");

        mLiveRoomFg = new LiveRoomFragment();
        mContactsFg = new ContactsFragment();
        mChatFg = new ChatFragment();
        mFragmentList.add(mChatFg);
        mFragmentList.add(mContactsFg);
        mFragmentList.add(mLiveRoomFg);

        mFragmentAdapter = new FragmentAdapter(this.getSupportFragmentManager(), mFragmentList);
        mPageVp.setAdapter(mFragmentAdapter);
        mPageVp.setCurrentItem(0);
        mTabChatTv.setTextColor(getResources().getColor(R.color.checkboxcolor));
        changePageScroll();
        tab_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPageVp.setCurrentItem(0);
                mTabChatTv.setTextColor(getResources().getColor(R.color.checkboxcolor));
            }
        });
        tab_contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPageVp.setCurrentItem(1);
                mTabContactsTv.setTextColor(getResources().getColor(R.color.checkboxcolor));
            }
        });
        tab_live_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPageVp.setCurrentItem(2);
                mTabLiveRoomTv.setTextColor(getResources().getColor(R.color.checkboxcolor));
            }
        });

    }

    /**
     * 设置滑动条的宽度为屏幕的1/3(根据Tab的个数而定)
     */
    private void initTabLineWidth() {
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
                .getLayoutParams();
        lp.width = screenWidth / 3;
        mTabLineIv.setLayoutParams(lp);
    }

    /**
     * 重置颜色
     */
    private void resetTextView() {
        mTabChatTv.setTextColor(getResources().getColor(R.color.infoColor));
        mTabLiveRoomTv.setTextColor(getResources().getColor(R.color.infoColor));
        mTabContactsTv.setTextColor(getResources().getColor(R.color.infoColor));
    }

    /**
     * 左右滑动页面
     */
    private void changePageScroll(){
        mPageVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            /**
             * state滑动中的状态 有三种状态（0，1，2） 1：正在滑动 2：滑动完毕 0：什么都没做。
             */
            @Override
            public void onPageScrollStateChanged(int state) {

            }

            /**
             * position :当前页面，及你点击滑动的页面 offset:当前页面偏移的百分比
             * offsetPixels:当前页面偏移的像素位置
             */
            @Override
            public void onPageScrolled(int position, float offset,
                                       int offsetPixels) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
                        .getLayoutParams();

                Log.e("offset:", offset + "");
                /**
                 * 利用currentIndex(当前所在页面)和position(下一个页面)以及offset来
                 * 设置mTabLineIv的左边距 滑动场景：
                 * 记3个页面,
                 * 从左到右分别为0,1,2
                 * 0->1; 1->2; 2->1; 1->0
                 */

                if (currentIndex == 0 && position == 0)// 0->1
                {
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));

                } else if (currentIndex == 1 && position == 0) // 1->0
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));

                } else if (currentIndex == 1 && position == 1) // 1->2
                {
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));
                } else if (currentIndex == 2 && position == 1) // 2->1
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));
                }
                mTabLineIv.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position) {
                    case 0:
                        mTabChatTv.setTextColor(getResources().getColor(R.color.checkboxcolor));
                        break;
                    case 1:
                        mTabContactsTv.setTextColor(getResources().getColor(R.color.checkboxcolor));
                        break;
                    case 2:
                        mTabLiveRoomTv.setTextColor(getResources().getColor(R.color.checkboxcolor));
                        break;
                }
                currentIndex = position;
            }
        });

    }


}
