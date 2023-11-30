package com.yuaner.qbserver.service;

import com.yuaner.qbserver.common.api.ApiResult;
import com.yuaner.qbserver.model.dto.LoginDTO;
import com.yuaner.qbserver.model.dto.RegisterDTO;
import com.yuaner.qbserver.model.enity.User;

public interface UserService {

    ApiResult<User> registerUser(RegisterDTO registerDTO);

    ApiResult<User> loginUserByPassword(LoginDTO loginDTO);

    ApiResult<User> loginUserByCode(LoginDTO loginDTO);

    ApiResult<String> logout();
}
