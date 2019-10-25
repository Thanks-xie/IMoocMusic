package com.xie.com.imoocmusic.domain;

import org.jivesoftware.smack.roster.RosterEntry;

public class Friend {
    private String friendId;
    private String friendName;

    public Friend(RosterEntry entry){
        this.friendId = entry.getUser();
        this.friendName = entry.getName();
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }
}
