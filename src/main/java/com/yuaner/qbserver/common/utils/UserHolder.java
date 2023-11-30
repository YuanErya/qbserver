package com.yuaner.qbserver.common.utils;

import com.yuaner.qbserver.model.enity.User;

public class UserHolder {
    //线程共享存储对象，保存当前user信息
    private  static final ThreadLocal<User> tl=new ThreadLocal<User>();
    public static Boolean saveUser(User user){
        tl.set(user);
        return true;
    }
    public  static User getUser(){
        return tl.get();
    }
    public static Boolean removeUser(){
        tl.remove();
        return true;
    }
}
