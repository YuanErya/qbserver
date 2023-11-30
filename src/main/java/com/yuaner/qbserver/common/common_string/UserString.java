package com.yuaner.qbserver.common.common_string;

public abstract class UserString {

    public static final String md5_password_salt = "to be better javaer !";
    public static final String md5_email_salt = "login in success!";
    public static final String key_keep_alive_user="user:loginAlive:TOKEN:";

    public final static Integer user_redis_timeout =30;

    //返回登录信息的格式为：user_login_information1+UUID+user_login_information2+在线人数+user_login_information3
    public final  static  String user_login_information1 ="token:";
    public final  static  String user_login_information2 ="  当前该账号在线人数：";
    public final  static  String user_login_information3 ="/1";

    //允许同时在线的人数
    public final static Integer user_online_number =1;


}
