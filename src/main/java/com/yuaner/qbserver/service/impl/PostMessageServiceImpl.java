package com.yuaner.qbserver.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.yuaner.qbserver.common.api.ApiResult;
import com.yuaner.qbserver.common.common_string.SentCodeString;
import com.yuaner.qbserver.common.utils.RegexUtils;
import com.yuaner.qbserver.common.utils.SentCodeUtils;
import com.yuaner.qbserver.model.enity.PostMessage;
import com.yuaner.qbserver.service.PostMessageService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.concurrent.TimeUnit;

@Service
public class PostMessageServiceImpl  implements PostMessageService {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    /**
     * 发送验证码校验逻辑
     * @param token
     * @param emailAddress
     * @param messageId
     * @param timeStamp
     * @return
     */
    @Override
    public ApiResult<Boolean> sentCode(String token,String emailAddress, Integer messageId,Long timeStamp) {
        //验证时间戳是否满足要求
        Long currentTime = System.currentTimeMillis();
        if(currentTime-timeStamp>5000000){
            return ApiResult.failed("操作超时!");
        }
        //校验token
        String md5token=DigestUtil.md5Hex(emailAddress+SentCodeString.md5_salt+messageId+SentCodeString.md5_salt+timeStamp.toString());
        if(!md5token.equals(token.toLowerCase())){
            return ApiResult.failed("权限校验失败！");
        }
        if(RegexUtils.isEmailInvalid(emailAddress)){
            return ApiResult.failed("邮箱格式错误！");
        }
        if(messageId<1||messageId>4){
            return ApiResult.failed("类型校验错误！");
        }
        //随机生成验证码
        String code= RandomUtil.randomNumbers(6);
        stringRedisTemplate.opsForValue().set(SentCodeString.get_code_key1+messageId+
                SentCodeString.get_code_key2+emailAddress,code,SentCodeString.code_redis_timeout, TimeUnit.MINUTES);
        return ApiResult.success(SentCodeUtils.sentCode(code,emailAddress,PostMessage.getPostMessageById(messageId)),"发送成功！");
    }
}
