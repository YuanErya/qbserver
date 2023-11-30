package com.yuaner.qbserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuaner.qbserver.common.api.ApiResult;
import com.yuaner.qbserver.model.enity.PostMessage;

public interface PostMessageService {
    public ApiResult<Boolean> sentCode(String token,String emailAddress,Integer messageId,Long timeStamp);
}
