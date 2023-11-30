package com.yuaner.qbserver.common.utils;

import com.yuaner.qbserver.mapper.UserMapper;
import com.yuaner.qbserver.model.enity.Message;
import com.yuaner.qbserver.model.enity.User;
import com.yuaner.qbserver.websocket.WebSocket;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


public class InstructionHandler {
    private static ConcurrentHashMap<String, WebSocket> webSocketMap;

    private static UserMapper mapper;

    /**
     * 指令处理器的处理方法
     * @param user
     * @param message
     * @return
     */
    public Message toAnswer(String user, String message){
        if(message.contains("/help")){
            return new Message(user,null,"Hi!欢迎光临qb聊天室！\n" +
                    "以下是特殊指令消息：\n" +
                    "/help 获取指令帮助\n" +
                    "/getOPeople 获取当前在线人员名单\n" +
                    "/getAPeople 获取本聊天室所有注册人员的用户名\n" +
                    "/q 离开聊天室\n" +
                    "/sl [私聊对象用户名] [消息文本] 用于私发消息，对方不在线也可以发送哟，会在对方上线后推送给对方\n" +
                    "直接发送消息就是普通的聊天室群聊哦！\n");
        }
        if(message.contains("/getAPeople")){
            String answer ="下面是所有注册用户：\n";
            List<User> list=mapper.getAllUsersName();
            for (User user0 : list) {
                answer=answer+user0.getUserName()+"\n";
            }
            return new Message(user,null,answer);
        }
        if(message.contains("/getOPeople")){
            String answer="下面是所有在线用户：\n";
            for (String s : webSocketMap.keySet()) {
                answer=answer+s.substring(0,s.indexOf("*"))+"\n";
            }
            return new Message(user,null,answer);
        }
        if(message.equals("/q")){
            return  null;
        }
        return new Message(user,null,"无效指令!");
    }

    public InstructionHandler(ConcurrentHashMap<String, WebSocket> webSocketMap,UserMapper mapper) {
        this.webSocketMap = webSocketMap;
        this.mapper=mapper;
    }
}
