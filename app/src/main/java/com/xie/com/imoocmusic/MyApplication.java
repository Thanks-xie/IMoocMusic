package com.xie.com.imoocmusic;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.Utils;
import com.http.api.OkHttpUtil;
import com.xie.com.imoocmusic.help.RealmHelp;

import io.realm.Realm;

public class MyApplication extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        Realm.init(this);
        RealmHelp.migration();
        OkHttpUtil.init();
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
