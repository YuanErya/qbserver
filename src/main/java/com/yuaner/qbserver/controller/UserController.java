package com.yuaner.qbserver.controller;


import com.yuaner.qbserver.common.api.ApiResult;
import com.yuaner.qbserver.model.dto.LoginDTO;
import com.yuaner.qbserver.model.dto.RegisterDTO;
import com.yuaner.qbserver.model.enity.User;
import com.yuaner.qbserver.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/qbserver/user")
public class UserController {
    @Resource
    UserService userService;

    /**
     * 注册账号，必须验证邮箱
     * @param registerDTO
     * @return
     */
    @PostMapping("/register")
    public ApiResult<User> registerUser(@RequestBody RegisterDTO registerDTO){
        return userService.registerUser(registerDTO);
    }

    /**
     * 通过密码登录
     * @param loginDTO
     * @return
     */
    @PostMapping("/login/password")
    public  ApiResult<User> loginUserByPassword(@RequestBody LoginDTO loginDTO){
        return userService.loginUserByPassword(loginDTO);
    }

    /**
     * 通过邮箱验证码登录
     * @param loginDTO
     * @return
     */
    @PostMapping("/login/code")
    public  ApiResult<User> loginUserByCode(@RequestBody LoginDTO loginDTO){
        return userService.loginUserByCode(loginDTO);
    }

    /**
     *注销登录状态
     * @return
     */
    @PostMapping("/logout")
    public  ApiResult<String> logOutUser(){
        return userService.logout();
    }


}
