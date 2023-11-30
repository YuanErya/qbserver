package com.yuaner.qbserver.model.enity;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.Date;

/**
 * 只包含用户名，密码，邮箱等用于验证身份的核心信息
 * 没有用户昵称简介等
 */
@Data
@AllArgsConstructor
public class CheckUser {
    Long userId;
    String userName;
    String userPassword;
    String userEmail;
    Date createTime;
}


