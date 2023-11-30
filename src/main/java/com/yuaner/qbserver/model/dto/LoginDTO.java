package com.yuaner.qbserver.model.dto;

import lombok.Data;

/**
 * 封装登录相关信息的消息体
 */
@Data
public class LoginDTO {
    String usernameOrEmail;
    String passwordOrCode;
}
