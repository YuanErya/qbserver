package com.yuaner.qbserver.model.enity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

/**
 * 不包含密码敏感信息
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    Long userId;
    String userName;
    String userEmail;
    /**
     * 用户创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="Asia/Shanghai")
    Date createTime;
    Long userInfoId;
    String userNickname;
    String userIntroduction;
    Integer userSex;
    /**
     * 用户修改详细信息的时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="Asia/Shanghai")
    Date updateTime;
    /**
     * 登陆时返回UUID和该账号同时在线信息
     */
    String loginInformation;
    /**
     * 此账号登录在线的人数
     */
    String onlineNumber;
}
