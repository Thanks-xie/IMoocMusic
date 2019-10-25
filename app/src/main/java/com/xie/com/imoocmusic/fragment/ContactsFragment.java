package com.xie.com.imoocmusic.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xie.com.imoocmusic.R;
import com.xie.com.imoocmusic.activity.ChatFriendListActivity;

/**
 * 通讯录
 */
public class ContactsFragment extends Fragment {
    private LinearLayout myRemind,myComputer,myFriends,myGroup;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        View chatView = inflater.inflate(R.layout.activity_tab_contacts, container,false);
        initView(chatView);
        return chatView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    private void initView(View chatView){
        myRemind = chatView.findViewById(R.id.my_remind);
        myComputer = chatView.findViewById(R.id.my_computer);
        myFriends = chatView.findViewById(R.id.my_friend);
        myFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChatFriendListActivity.class);
                startActivity(intent);
            }
        });
        myGroup = chatView.findViewById(R.id.my_group);
    }
}
