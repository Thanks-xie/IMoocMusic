package com.xie.com.imoocmusic.config;

import android.util.Log;


import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

public class MyXmppConfig extends XMPPTCPConnection {

    private static MyXmppConfig connect;
    public MyXmppConfig(XMPPTCPConnectionConfiguration config) {

        super(config);
    }

    /**
     * 初始化连接数据
     * @return
     */
    public static synchronized MyXmppConfig getInstance(){
        //初始化配置
        if (connect ==null){
            XMPPTCPConnectionConfiguration.Builder builder = XMPPTCPConnectionConfiguration.builder();
            //设置连接超时的最大时间
            builder.setConnectTimeout(10000);
            //设置登录openfire的用户名和密码
            builder.setUsernameAndPassword("17761207349","123");
            //设置安全模式
            builder.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
            //设置客服端类型
            builder.setResource("Android");
            //设置服务器名称
            builder.setServiceName("xie-pc");
            //设置主机地址
            builder.setHost("192.168.0.130");
            //设置端口号
            builder.setPort(5222);
            //设置是否查看日志
            builder.setDebuggerEnabled(true);

            connect = new MyXmppConfig(builder.build());
        }
        Log.e("MyXmppConfig","connect: "+ connect);
        connectionListener();
        return connect;
    }

    /**
     * 连接openfire服务器
     */
    public static void connectOpenfire(){
        try {
            if (connect != null){
                //如果没有连接openfire服务器，则连接；若已连接openfire服务器则跳过。
                if(!connect.isConnected()){
                    connect.connect();
                }
            }else {
                MyXmppConfig.getInstance();
                connect.connect();
            }

        }catch (Exception e){
            Log.e("connectOpenfire","Exception: "+e.toString());
        }

    }

    /**
     *
     * @param phone 登录用户名
     * @param password 登录密码
     */
    public static void loginOpenfire(String phone,String password){
        try {
        if(connect.isConnected()){
            //第一个参数是 用户名（即你在openfire服务器用户管理页面创建的用户名）
            //第二个参数是 用户密码（同上）
            //connection.login("用户名", "密码")
            connect.login( phone, password);
        }else {
            connectOpenfire();
            connect.login(phone, password);
        }
        } catch (Exception e) {
            Log.e("Login","Exception: "+e.toString());
        }
    }

    public MyXmppConfig(CharSequence jid, String password) {
        super(jid, password);
    }

    public MyXmppConfig(CharSequence username, String password, String serviceName) {
        super(username, password, serviceName);
    }

    public static void connectionListener(){
        if (connect!=null){
            connect.addConnectionListener(new ConnectionListener() {
                @Override
                public void connected(XMPPConnection connection) {
                    Log.e("connected", "connected");
                }

                @Override
                public void authenticated(XMPPConnection connection, boolean resumed) {
                    Log.e("authenticated", "authenticated");
                }

                @Override
                public void connectionClosed() {
                    Log.e("connectionClosed", "connectionClosed");
                }

                @Override
                public void connectionClosedOnError(Exception e) {
                    Log.e("connectionClosedOnError", "connectionClosedOnError");
                }

                @Override
                public void reconnectionSuccessful() {
                    Log.e("reconnectionSuccessful", "reconnectionSuccessful");
                }

                @Override
                public void reconnectingIn(int seconds) {
                    Log.e("reconnectingIn", "reconnectingIn");
                }

                @Override
                public void reconnectionFailed(Exception e) {
                    Log.e("reconnectionFailed", "reconnectionFailed");
                }
            });
        }
    }
}
