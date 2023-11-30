package com.yuaner.qbserver.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.digest.DigestUtil;
import com.yuaner.qbserver.common.api.ApiResult;
import com.yuaner.qbserver.common.common_string.SentCodeString;
import com.yuaner.qbserver.common.common_string.UserString;
import com.yuaner.qbserver.common.utils.RegexUtils;
import com.yuaner.qbserver.common.utils.UserHolder;
import com.yuaner.qbserver.mapper.UserMapper;
import com.yuaner.qbserver.model.dto.LoginDTO;
import com.yuaner.qbserver.model.dto.RegisterDTO;
import com.yuaner.qbserver.model.enity.CheckUser;
import com.yuaner.qbserver.model.enity.User;
import com.yuaner.qbserver.service.UserService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;
    @Resource
    RedisTemplate<String,Object> redisTemplate;


    /**
     * 注册
     * @param registerDTO
     * @return
     */
    @Override
    public ApiResult<User> registerUser(RegisterDTO registerDTO) {
        if(RegexUtils.isEmailInvalid(registerDTO.getUserEmail())){
            return ApiResult.failed("邮箱格式错误！");
        }
        if(!userMapper.checkUserEmailAndUsernameUnique(registerDTO.getUserEmail(),registerDTO.getUserName()).isEmpty()){
            return ApiResult.failed("邮箱或用户名已存在！");
        }
        if(!registerDTO.getUserPassword().equals(registerDTO.getUserPasswordAgain())){
            return ApiResult.failed("两次密码输入不一致！");
        }
        //key的命名为key=key1+typeId+key2+email
        Object code= redisTemplate.opsForValue().get(SentCodeString.get_code_key1+SentCodeString.ID_message_register+
                SentCodeString.get_code_key2+registerDTO.getUserEmail());
        if(code==null||code.toString().isEmpty()||!code.toString().equals(registerDTO.getCode())){
            return ApiResult.failed("验证码错误！");
        }
        CheckUser user=new CheckUser(null, registerDTO.getUserName(),
                DigestUtil.md5Hex(registerDTO.getUserPassword()+ UserString.md5_password_salt +registerDTO.getUserEmail()),
                registerDTO.getUserEmail(), new Date(System.currentTimeMillis()));
        userMapper.addUser(user);
        User rUser= BeanUtil.toBean(user,User.class);
        rUser.setUpdateTime(new Date(System.currentTimeMillis()));
        userMapper.addUserInfo(rUser);
        return ApiResult.success(rUser,"注册成功！");
    }

    /**
     * 使用用户名或者邮箱通过验证密码登录
     * @param loginDTO
     * @return
     */
    @Override
    public ApiResult<User> loginUserByPassword(LoginDTO loginDTO) {
        CheckUser user;
        if(RegexUtils.isEmailInvalid(loginDTO.getUsernameOrEmail())) {
            //用户名登录
            user= userMapper.getCheckUserByUserName(loginDTO.getUsernameOrEmail());
        }else{
            //邮箱号登录
            user= userMapper.getCheckUserByEmail(loginDTO.getUsernameOrEmail());
        }
        if(user==null){
            return ApiResult.failed("该账户暂未注册！");
        }
        if(!DigestUtil.md5Hex(loginDTO.getPasswordOrCode()+ UserString.md5_password_salt +user.getUserEmail()).equals(user.getUserPassword())){
            return ApiResult.failed("密码错误！");
        }
        User result=userMapper.getUserById(user.getUserId());
        String loginInfomation=putUserToRedis(result);
        if(loginInfomation==null){
            return ApiResult.failed("登陆失败,该账号已有"+UserString.user_online_number+"人同时在线!");
        }
        return ApiResult.success(result,"登录成功");
    }

    /**
     * 使用邮箱和验证码登录
     * @param loginDTO
     * @return
     */
    @Override
    public ApiResult<User> loginUserByCode(LoginDTO loginDTO) {
        if(RegexUtils.isEmailInvalid(loginDTO.getUsernameOrEmail())){
            return ApiResult.failed("邮箱格式错误！");
        }

        Object code=redisTemplate.opsForValue().get(SentCodeString.get_code_key1+SentCodeString.ID_message_login+
                SentCodeString.get_code_key2+loginDTO.getUsernameOrEmail());
        if(code==null||code.toString().isEmpty()||!code.toString().equals(loginDTO.getPasswordOrCode())){
            return ApiResult.failed("验证码错误！");
        }
        //验证成功一次后将验证码设置为60s过期
        redisTemplate.expire(SentCodeString.get_code_key1+SentCodeString.ID_message_login+
                SentCodeString.get_code_key2+loginDTO.getUsernameOrEmail(),30,TimeUnit.SECONDS);
        User result=userMapper.getUserByEmail(loginDTO.getUsernameOrEmail());
        String loginInfomation=putUserToRedis(result);
        if(loginInfomation==null){
            return ApiResult.failed("登陆失败,该账号已有"+UserString.user_online_number+"人同时在线!");
        }
        return ApiResult.success(result,"登录成功");
    }

    /**
     * 将redis中用户登录信息删除
     * @return
     */
    @Override
    public ApiResult<String> logout() {
        User user=UserHolder.getUser();
        String token=user.getLoginInformation();
        if (token.startsWith("token:")) {
            token=token.replace("token:","");
        }
        String emailMD5=token.substring(token.indexOf("_")+1);
        String UUID=token.substring(0,token.indexOf("_"));
        String key= UserString.key_keep_alive_user+emailMD5+":"+UUID;
        redisTemplate.delete(key);
        return ApiResult.success("注销成功!");
    }

    private String putUserToRedis (User user){
        String k1=UserString.key_keep_alive_user+DigestUtil.md5Hex(UserString.md5_email_salt+user.getUserEmail())+":";
//        //模糊查询到该用户放在redis中的UUID个数
//        Set<String> keys = redisTemplate.keys(k1+"*");
//        //最多允许4人在线
//        if(keys.size()>=UserString.user_online_number){
//            return null;
//        }
        String token=UUID.randomUUID().toString(true);
        user.setOnlineNumber((1)+UserString.user_login_information3);
        String loginInformation=UserString.user_login_information1+token+"_"+DigestUtil.md5Hex(UserString.md5_email_salt+user.getUserEmail());
        user.setLoginInformation(loginInformation);
        Map<String,Object> userMap=BeanUtil.beanToMap(user);
        redisTemplate.opsForHash().putAll(k1+token,userMap);
        redisTemplate.expire(k1+token,UserString.user_redis_timeout, TimeUnit.MINUTES);
        return loginInformation;
    }
}

