package com.yuaner.qbserver.common.utils;

import com.yuaner.qbserver.model.enity.Message;

public class MessageUtils {
    /**
     * 用户登录时的系统消息
     * @param username 上线人用户名
     * @param onlineNum 在线人数
     * @return
     */
    public static Message sentUserOnline(String username, Integer onlineNum){
        return new Message(null,null,"来自【系统】的消息:欢迎 "+username+" 加入聊天室！"+"当前在线人数"+onlineNum+"\n"
                +"可以通过发送/help 指令获取聊天室指南哟！");
    }
    /**
     * 用户离线时的系统消息
     * @param username 离线人用户名
     * @param onlineNum 在线人数
     * @return
     */
    public static Message sentUserOffline(String username, Integer onlineNum){
        return new Message(null,null,"来自【系统】的消息:"+username+" 下线了。"+"当前在线人数:"+onlineNum);
    }

    /**
     * 聊天室只有一人时候自动回复
     * @param question
     * @return
     */
    public static Message autoAnswer(String question,String username){
        String result="";
        //防止报错拿不到数据崩溃
        try{
            result=new AutoAnswerUtils().autoAnswer(question);
        }catch (Exception e){
            result="你到底想干嘛呀？！";
            return new Message(null,null,"来自【小精灵（系统）】的消息:\n"+result);
        }

        if(result.contains("{name}")){
            result=result.replace("{name}"," "+username+" ");
        }
        if(result.contains("{segment}")){
            result=result.replace("{segment}","小精灵");
        }
        if(result.equals("词库不匹配")){
            result="小精灵听不懂你在说什么。。。";
        }
        return new Message(null,null,"来自【小精灵（系统）】的消息:\n"+result);
    }

    /**
     * 消息转发包装成Message类
     * @param username
     * @param message
     * @return
     */
    public static Message forwardToAll(String username,String message){
        return  new Message(null,username,message);
    }

}
