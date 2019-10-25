package com.xie.com.imoocmusic.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.widget.Toast;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.xie.com.imoocmusic.R;
import com.xie.com.imoocmusic.activity.LoginActivity;
import com.xie.com.imoocmusic.domain.UserModel;
import com.xie.com.imoocmusic.help.RealmHelp;
import com.xie.com.imoocmusic.help.UserHelp;

import java.nio.file.FileStore;
import java.util.List;

import io.realm.annotations.PrimaryKey;

public class UserUtil {

    /**
     * 验证登录用户输入的合法性
     * @param context 上下文
     * @param phone   手机号
     * @param password  密码
     * @return
     */
    public static boolean isValidityLogin(Context context,String phone,String password){

        RegexUtils.isMobileExact(phone);  //手机号验证
        if (!RegexUtils.isMobileExact(phone)) {
            Toast.makeText(context, "无效手机号", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(context, "请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        /**
         * 判断当前手机号是否已经被注册
         */
        if (!isRegistered(phone)){
            Toast.makeText(context, "当前手机号未注册", Toast.LENGTH_SHORT).show();
            return false;
        }

        /**
         * 判断输入手机号密码是否正确
         */

        RealmHelp realmHelp = new RealmHelp();
        boolean result = realmHelp.loginUser(phone,EncryptUtils.encryptMD5ToString(password));
        if (!result){
            Toast.makeText(context, "输入的手机号或者密码错误", Toast.LENGTH_SHORT).show();
            return false;
        }

        //保存用户标记
        boolean isSave = SharedPreferenceUtil.saveUser(context,phone);
        if (!isSave){
            Toast.makeText(context, "系统错误", Toast.LENGTH_SHORT).show();
            return false;
        }

        //
        UserHelp.getInstance().setPhone(phone);

        //        保存音乐源数据
        realmHelp.setMusicSource(context);

        realmHelp.close();

        return true;
    }

    /**
     * 验证登录用户输入的合法性
     * @param context 上下文
     * @param phone   手机号
     * @param password  密码
     * @param rePassword  确认密码
     *
     */
    public static boolean registerUtil(Context context,String phone,String password,String rePassword){

        if (StringUtils.isEmpty(phone)||!RegexUtils.isMobileExact(phone)) {
            Toast.makeText(context, "无效手机号", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (StringUtils.isEmpty(password)||!password.equals(rePassword)){
            Toast.makeText(context, "请确认密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        //判断手机号是否已经注册过

        if (isRegistered(phone)){
            Toast.makeText(context, "该手机号已经注册，请直接登录", Toast.LENGTH_SHORT).show();
            return false;
        }

        UserModel userModel = new UserModel();
        userModel.setPhone(phone);
        userModel.setPassword(EncryptUtils.encryptMD5ToString(password));

        saveUser(userModel);
        return true;
    }

    /**
     * 保存数据到Realm
     * @param user
     */
    public static void saveUser(UserModel user){
        RealmHelp realmHelp = new RealmHelp();
        realmHelp.saveUser(user);
        realmHelp.close();
    }

    /**
     * 退出登录
     * @param context
     */
    public static  void logout(Context context){
        //删除保存的用户标记
        boolean isRemove = SharedPreferenceUtil.removeUser(context);
        if (!isRemove){
            Toast.makeText(context, "系统错误", Toast.LENGTH_SHORT).show();
            return;
        }
        //        删除数据源
        RealmHelp realmHelper = new RealmHelp();
        realmHelper.removeMusicSource();
        realmHelper.close();

        Intent intent = new Intent(context, LoginActivity.class);
        //添加intent标志符，清理task栈，并新建栈
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        //重新设置activity动画，且必须在startActivity之后
        ((Activity)context).overridePendingTransition(R.anim.open_enter,R.anim.open_exit);

    }

    public static boolean isRegistered(String phone){
        boolean result = false;
        RealmHelp realmHelp = new RealmHelp();
        List<UserModel> userModelList = realmHelp.getAllUsers();
        realmHelp.close();
        if (userModelList != null && userModelList.size()>=0){
            for (int i=0;i<userModelList.size();i++){
                if (userModelList.get(i).getPhone().equals(phone)){
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 验证是否已存在登录用户
     * @param context
     * @return
     */
    public static boolean validateUserLogin(Context context){
        return SharedPreferenceUtil.isLoginUser(context);
    }

    /**
     * 密码修改
     * @return
     */
    public static  boolean changePassWord(Context context,String oldPd,String newPd,String newPdConfirm){
        if (TextUtils.isEmpty(oldPd)){
            Toast.makeText(context, "请输入原密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(newPd) || !newPd.equals(newPdConfirm)){
            Toast.makeText(context, "请确认密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        RealmHelp realmHelp = new RealmHelp();
        UserModel userModel = realmHelp.getUser();
        if (!EncryptUtils.encryptMD5ToString(oldPd).equals(userModel.getPassword())){
            Toast.makeText(context, "原密码输入错误", Toast.LENGTH_SHORT).show();
            return false;
        }

        realmHelp.changePassWord(EncryptUtils.encryptMD5ToString(newPd));
        realmHelp.close();

        return true;

    }

}
