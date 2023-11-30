package com.yuaner.qbserver.websocket;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.yuaner.qbserver.common.common_string.UserString;
import com.yuaner.qbserver.common.utils.InstructionHandler;
import com.yuaner.qbserver.common.utils.MessageUtils;
import com.yuaner.qbserver.common.utils.UserHolder;
import com.yuaner.qbserver.mapper.UserMapper;
import com.yuaner.qbserver.model.enity.Message;
import com.yuaner.qbserver.model.enity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@ServerEndpoint(value = "/qbserver/{token}")
@Component
@Slf4j
public class WebSocket {
    private static RedisTemplate<String,Object> redisTemplate;
    @Resource
    private void setRedisTemplate(RedisTemplate<String,Object>  redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    private static UserMapper mapper;
    @Resource
    private void setUserMapper(UserMapper userMapper) {
        this.mapper=userMapper;
    }
    private static ConcurrentHashMap<String, WebSocket> webSocketMap = new ConcurrentHashMap<>();
    //实例一个session，这个session是websocket的session
    private Session session;

    public static ConcurrentHashMap<String, WebSocket> getWebSocketMap() {
        return webSocketMap;
    }

    public static void setWebSocketMap(ConcurrentHashMap<String, WebSocket> webSocketMap) {
        WebSocket.webSocketMap = webSocketMap;
    }
    /**
     * 连接请求
     * @param session 会话
     * @param token 登录凭证
     * @throws IOException
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) throws IOException {
        Map<Object,Object> userMap=redisTemplate.opsForHash().entries(tokenToRedisKEY(token));
        if(userMap.isEmpty()){
            session.getBasicRemote().sendText("连接失败！请重新登录！");
            session.close();
            return;
        }
        User user= BeanUtil.fillBeanWithMap(userMap,new User(),false);
        UserHolder.saveUser(user);
        this.session = session;
        webSocketMap.put(user.getUserName()+"*"+token, this);
        redisTemplate.expire(tokenToRedisKEY(token),UserString.user_redis_timeout, TimeUnit.MINUTES);
        sendNotice(MessageUtils.sentUserOnline(user.getUserName(),webSocketMap.size()));
        log.info("【qqserver消息】有新的连接,用户携带token:"+token);
    }
    /**
     * 关闭连接
     * @param token 登录凭证
     */
    @OnClose
    public void onClose(@PathParam("token") String token) {
        User user=getUserByToken(token);
        //携带错误的token连接失败
        if(user.getUserName()==null){
            log.info("【qqserver消息】有客户端尝试携带无效token:"+token+"进行连接");
            return ;
        }
        String key=tokenToRedisKEY(token);
        //从redis中注销
        redisTemplate.delete(key);
        webSocketMap.remove(user.getUserName()+"*"+token);
        log.info("【qqserver消息】连接断开,当前连接总数:"+ webSocketMap.size());
        sendNotice(MessageUtils.sentUserOffline(user.getUserName(),webSocketMap.size()));
    }
    //前端向后端发送消息
    @OnMessage
    public void onMessage(String message,@PathParam("token") String token) {
        redisTemplate.expire(tokenToRedisKEY(token),UserString.user_redis_timeout, TimeUnit.MINUTES);
        Map<Object,Object> userMap=redisTemplate.opsForHash().entries(tokenToRedisKEY(token));
        User user= BeanUtil.fillBeanWithMap(userMap,new User(),false);
        log.debug("【qqserver消息】收到 "+user.getUserName()+ " 发来的消息:"+message);
        Message msg=JSONUtil.toBean(message, Message.class);
        //判断是否是私聊消息
        if(msg.getTo()!=null){
            sendToOne(msg);
            return;
        }
        //判断是否为指令
        if(msg.getMessage().startsWith("/")){
            //创建指令处理器（应为涉及到在线人数统计需要将map传过去）
            InstructionHandler handler=new InstructionHandler(webSocketMap,mapper);
            Message re=handler.toAnswer(user.getUserName(), msg.getMessage());
            if(re!=null){
                sendToOne(re);
                return;
            }else {
                String key=tokenToRedisKEY(token);
                //从redis中注销
                redisTemplate.delete(key);
                try {
                    webSocketMap.get(user.getUserName()+"*"+token).session.close();
                } catch (IOException e) {
                    log.error("server:连接断开错误!");
                }
                webSocketMap.remove(user.getUserName()+"*"+token);

            }
        }
        //聊天室只有一个人的时候触发自动回复小精灵
        if(webSocketMap.size()==1){
            sendNotice(MessageUtils.autoAnswer(msg.getMessage(),user.getUserName()));
            return;
        }
        //正常的消息转发操作
        sendMessageToAll(MessageUtils.forwardToAll(user.getUserName(),msg.getMessage()), user.getUserName()+"*"+token);
    }
    /**
     * 发生错误时
     * @param error 捕获错误
     */
    @OnError
    public void onError(Throwable error){
        log.debug("【error】:"+ error.getMessage());
    }
    /**
     * 暂时弃用，Threadlocal保存不了用户
     * 向某个客户端发送消息
     */
//    public static void sendMessage(Message message, String token) {
//        WebSocket webSocket = webSocketMap.get(token);
//        if (webSocket != null) {
//            try {
//                webSocket.session.getBasicRemote().sendText(JSONUtil.toJsonStr(message));
//                log.debug("【qqserver消息】发送消息成功,目标用户="+UserHolder.getUser().getUserName()+",消息内容:"+message.toString());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public static void sendToOne(Message message){
        for (String s : webSocketMap.keySet()) {
            //如果私发对象用户在线
            if(s.startsWith(message.getTo())){
                try {
                    webSocketMap.get(s).session.getBasicRemote().sendText(JSONUtil.toJsonStr(message));
                    return;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            //不在线，逻辑还未完成
        }
    }
    /**
     * 向其余客户端转发消息
     * @param message
     */
    public static void sendMessageToAll(Message message,String key) {
        if (!webSocketMap.isEmpty()) {
            try {
                for(String key0: webSocketMap.keySet()){
                    if(!key.equals(key0)){
                        webSocketMap.get(key0).session.getBasicRemote().sendText(JSONUtil.toJsonStr(message));
                    }
                }
                log.debug("【qqserver消息】消息转发成功,目标用户:其余全体在线用户，消息内容:"+message.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 系统消息
     * @param message
     */
    public static void sendNotice(Message message) {
        if (!webSocketMap.isEmpty()) {
            try {
                for(String key: webSocketMap.keySet()){
                        webSocketMap.get(key).session.getBasicRemote().sendText(JSONUtil.toJsonStr(message));
                }
                log.debug("【qqserver消息】发送消息成功,目标用户:全体在线用户，消息内容:"+message.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将token转化成取redis中用户数据的key
     * @param token 用户登录凭证
     * @return rediskey
     */
    public static String tokenToRedisKEY(String token) {
        String emailMD5=token.substring(token.indexOf("_")+1);
        String UUID=token.substring(0,token.indexOf("_"));
        return UserString.key_keep_alive_user+emailMD5+":"+UUID;
    }

    /**
     * 根据token获取用户
     * @param token 用户登录凭证
     * @return 用户对象
     */
    public static User getUserByToken(String token){
        Map<Object,Object> userMap=redisTemplate.opsForHash().entries(tokenToRedisKEY(token));
        return  BeanUtil.fillBeanWithMap(userMap,new User(),false);
    }
}
