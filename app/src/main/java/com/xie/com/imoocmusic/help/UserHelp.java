package com.xie.com.imoocmusic.help;

/**
 * 1.用户登录
 *      利用SharedPreferences保存用户的登录标记
 * 2.用户退出
 */
public class UserHelp {
    private static UserHelp instance;
    private String phone;

    private UserHelp(){

    }

    /**
     * 单例UserHelp
     * @return
     */
    public static UserHelp getInstance(){
        if (instance == null){
            synchronized (UserHelp.class){
                if (instance == null){
                    instance = new UserHelp();
                }
            }
        }
        return instance;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
