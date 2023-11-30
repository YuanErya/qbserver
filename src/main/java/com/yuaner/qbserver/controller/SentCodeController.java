package com.yuaner.qbserver.controller;

import com.yuaner.qbserver.common.api.ApiResult;
import com.yuaner.qbserver.service.PostMessageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/qbserver")
public class SentCodeController {
    @Resource
    PostMessageService postMessageService;

    /**
     * 这个接口需要校验权限
     * token的算法为MD5Hex(email+salt+typeId+salt+timeStamp)
     * @param checkType 邮件验证码类型
     * @param email 邮箱地址
     * @param token 权限校验
     * @return
     */
    @PostMapping("api/sentEmailCode")
    public ApiResult<Boolean> sentEmailCode(@RequestParam(value = "checkType", required = true) Integer checkType,
                                            @RequestParam(value = "email", required = true) String email,
                                            @RequestParam(value = "token", required = true) String token,
                                            @RequestParam(value = "time_stamp",required = true) Long timeStamp){
        return postMessageService.sentCode(token,email,checkType,timeStamp);
    }
}
