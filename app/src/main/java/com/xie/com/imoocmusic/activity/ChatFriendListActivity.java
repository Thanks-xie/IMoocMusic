package com.xie.com.imoocmusic.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.xie.com.imoocmusic.R;
import com.xie.com.imoocmusic.adapters.ChatFriendListAdapter;
import com.xie.com.imoocmusic.config.MyXmppConfig;
import com.xie.com.imoocmusic.domain.Friend;

import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;

import java.util.ArrayList;
import java.util.List;

public class ChatFriendListActivity extends BaseActivity {

    private List<Friend> friendList;
    private MyXmppConfig connection;
    private RecyclerView recyclerView;
    private ChatFriendListAdapter chatFriendListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_friend_list);
        initView();
        initData();
    }

    public void initView(){
        initNavBar(true,"好友列表",false);
        recyclerView = findViewById(R.id.recyclerView);
    }

    /**
     * 加载好友数据
     */
    private void initData(){
        friendList = new ArrayList<Friend>();
        connection = MyXmppConfig.getInstance();
        if (connection==null){
            MyXmppConfig.loginOpenfire("17761207347","123");
        }
        for(RosterEntry entry : Roster.getInstanceFor(connection).getEntries()) {
            Friend friend = new Friend(entry);
            friendList.add(friend);
        }
        Log.e("xiejinbo","好友："+ JSON.toJSONString(friendList));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        chatFriendListAdapter = new ChatFriendListAdapter(this);
        recyclerView.setAdapter(chatFriendListAdapter);
    }


}
