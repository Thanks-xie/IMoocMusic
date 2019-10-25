package com.xie.com.imoocmusic.help;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.xie.com.imoocmusic.domain.AlbumModel;
import com.xie.com.imoocmusic.domain.MusicModel;
import com.xie.com.imoocmusic.domain.MusicResourceModel;
import com.xie.com.imoocmusic.domain.UserModel;
import com.xie.com.imoocmusic.migration.Migration;
import com.xie.com.imoocmusic.utils.DataUtils;

import java.io.FileNotFoundException;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class RealmHelp {
    private Realm mRealm;

    public RealmHelp (){
        mRealm = Realm.getDefaultInstance();
    }

    /**
     * 保存数据
     */
    public void saveUser(UserModel userModel){
        mRealm.beginTransaction();
        mRealm.insert(userModel);
        //mRealm.insertOrUpdate(userModel);
        mRealm.commitTransaction();
    }

    /**
     * 关闭数据库
     */
    public void close(){
        if (mRealm != null || !mRealm.isClosed()){
            mRealm.isClosed();
        }
    }

    /**
     * Realm数据库发生结构性变化（模型或者模型中的字段发生了新增、修改、删除）
     * 的时候，我们就需要对数据库进行迁移。
     */

    /**
     * 告诉Realm数据需要迁移，并且为Realm设置最新的配置
     */
    public static void migration () {
        RealmConfiguration conf = getRealmConf();

//        Realm设置最新的配置
        Realm.setDefaultConfiguration(conf);
//        告诉Realm数据需要迁移
        try {
            Realm.migrateRealm(conf);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回 RealmConfiguration
     * @return
     */
    private static RealmConfiguration getRealmConf () {
        return new RealmConfiguration.Builder()
                .schemaVersion(1)
                .migration(new Migration())
                .build();
    }

    /**
     * 查询所有注册用户
     * @return
     */
    public List<UserModel> getAllUsers(){
        RealmQuery<UserModel> query = mRealm.where(UserModel.class);
        RealmResults<UserModel> results = query.findAll();
        return results;
    }

    /**
     * 用户登录
     * @param phone
     * @param password
     * @return
     */
    public boolean loginUser(String phone ,String password){
        RealmQuery<UserModel> query = mRealm.where(UserModel.class);
        query = query.equalTo("phone",phone).equalTo("password",password);
        UserModel user = query.findFirst();
        if (user != null){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 获取登录用户
     * @return
     */
    public UserModel getUser(){
        RealmQuery<UserModel> query = mRealm.where(UserModel.class);
        UserModel userModel = query.equalTo("phone",UserHelp.getInstance().getPhone()).findFirst();
        return userModel;
    }

    /**
     * 修改密码
     * @param passWord
     */
    public void changePassWord(String passWord){
        UserModel userModel = getUser();
        mRealm.beginTransaction();
        userModel.setPassword(passWord);
        mRealm.commitTransaction();
    }

    /**
     * 保存音乐源数据
     */
    public void setMusicSource (Context context) {
//        拿到资源文件中的数据
        String musicSourceJson = DataUtils.getJsonFromAssets(context, "DataSource.json");
        Log.e("音乐：", musicSourceJson);
        mRealm.beginTransaction();
        mRealm.createObjectFromJson(MusicResourceModel.class, musicSourceJson);
        mRealm.commitTransaction();
    }

    /**
     * 删除音乐源数据
     * 1、RealmResult delete
     * 2、Realm delete 删除这个模型下所有的数据
     */
    public void removeMusicSource () {
        mRealm.beginTransaction();
        mRealm.delete(MusicResourceModel.class);
        mRealm.delete(MusicModel.class);
        mRealm.delete(AlbumModel.class);
        mRealm.commitTransaction();
    }

    /**
     * 返回音乐源数据
     */
    public MusicResourceModel getMusicSource () {
        return mRealm.where(MusicResourceModel.class).findFirst();
    }

    /**
     * 返回歌单
     */
    public AlbumModel getAlbum (String albumId) {
        return mRealm.where(AlbumModel.class).equalTo("albumId", albumId).findFirst();
    }

    /**
     * 返回音乐
     */
    public MusicModel getMusic (String musicId) {
        return mRealm.where(MusicModel.class).equalTo("musicId", musicId).findFirst();
    }

}

