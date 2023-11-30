package com.yuaner.qbserver.model.dto;

import lombok.Data;


/**
 * 封装注册相关消息体
 */
@Data
public class RegisterDTO {

    String userName;

    String userPassword;

    String userPasswordAgain;

    String userEmail;

    String code;
}
