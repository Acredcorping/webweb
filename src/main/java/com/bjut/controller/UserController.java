package com.bjut.controller;

import com.bjut.dto.LoginFormDTO;
import com.bjut.dto.Result;
import com.bjut.dto.UserDTO;
import com.bjut.entity.UserInfo;
import com.bjut.service.IMerchantService;
import com.bjut.service.IUserInfoService;
import com.bjut.service.IUserService;
import com.bjut.utils.UserHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;

    @Resource
    private IMerchantService merchantService;

    @Resource
    private IUserInfoService userInfoService;

    @PostMapping("/code")
    public Result sendCode(@RequestParam("phone") String phone, HttpSession session) {
        // 发送短信验证码
        return userService.sendCode(phone, session);
    }

    @PostMapping("/login")
    public Result login(@RequestBody LoginFormDTO loginForm, HttpSession session) {
        return userService.login(loginForm, session);
    }

    @PostMapping("/logout")
    public Result logout(@RequestHeader("authorization") String token) {
        // 登出
        return userService.logout(token);
    }

    @GetMapping("/me")
    public Result me() {
        // 获取当前登录的用户并返回
        UserDTO user = UserHolder.getUser();
        return Result.ok(user);
    }

    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") Long userId) {
        // 查询详情
        UserInfo info = userInfoService.getById(userId);
        if (info == null) {
            return Result.fail("error");
        }
        info.setCreateTime(null);
        info.setUpdateTime(null);
        return Result.ok(info);
    }

    @PostMapping("/info/update")
    public Result updateInfo(@RequestBody UserInfo userInfo) {
        return userService.updateInfo(userInfo);
    }

    @PostMapping("/merchant/login")
    public Result merchantLogin(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        return merchantService.login(userName, password);
    }

    @PostMapping("/merchant/register")
    public Result merchantResister(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        return merchantService.register(userName, password);
    }
}
