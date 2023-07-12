package com.bjut.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bjut.dto.LoginFormDTO;
import com.bjut.dto.Result;
import com.bjut.entity.User;
import com.bjut.entity.UserInfo;

import javax.servlet.http.HttpSession;


public interface IUserService extends IService<User> {
    Result sendCode(String phone, HttpSession session);

    Result login(LoginFormDTO loginForm, HttpSession session);

    Result logout(String token);

    Result updateInfo(UserInfo userInfo);
}

