package com.xie.com.imoocmusic.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.blankj.utilcode.util.StringUtils;
import com.xie.com.imoocmusic.help.UserHelp;

import io.realm.annotations.PrimaryKey;

public class SharedPreferenceUtil {

    /**
     * 保存用户登录的标记
     * @param context
     * @param phone
     * @return
     */
    public static  boolean saveUser(Context context,String phone){
        SharedPreferences sp = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("phone",phone);
        boolean result = editor.commit();
        return result;
    }

    /**
     * 验证是否存在已经登录的用户
     * @param context
     * @return
     */
    public static  boolean isLoginUser(Context context){
        boolean isLogin = false;

        SharedPreferences sp = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        String phone = sp.getString("phone","");
        if (!TextUtils.isEmpty(phone)){
            UserHelp.getInstance().setPhone(phone);
            isLogin = true;
        }

        return isLogin;
    }

    /**
     * 删除保存的用户标记
     * @param context
     * @return
     */
    public static boolean removeUser(Context context){
        SharedPreferences sp = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove("phone");
        boolean isRemove = editor.commit();
        return isRemove;

    }
}
