package com.bjut.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjut.dto.LoginFormDTO;
import com.bjut.dto.Result;
import com.bjut.dto.UserDTO;
import com.bjut.entity.User;
import com.bjut.entity.UserInfo;
import com.bjut.mapper.UserMapper;
import com.bjut.service.IUserInfoService;
import com.bjut.service.IUserService;
import com.bjut.utils.PasswordEncoder;
import com.bjut.utils.RegexUtils;
import com.bjut.utils.UserHolder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.bjut.utils.RedisConstants.*;
import static com.bjut.utils.SystemConstants.USER_NICK_NAME_PREFIX;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private IUserInfoService userInfoService;

    @Override
    public Result sendCode(String phone, HttpSession session) {
        //校验手机号是否合法
        if (RegexUtils.isPhoneInvalid(phone)) {
            return Result.fail("手机号格式错误！");
        }

        //符合，生成验证码
        String code = RandomUtil.randomNumbers(6);

        //保存验证码到Redis
        stringRedisTemplate.opsForValue().set(LOGIN_CODE_KEY + phone, code, LOGIN_CODE_TTL, TimeUnit.MINUTES);

        log.debug("发送短信验证码到:" + phone + ":" + code);

        return Result.ok();
    }

    @Override
    @Transactional
    public Result login(LoginFormDTO loginForm, HttpSession session) {
        String phone = loginForm.getPhone();
        String code = loginForm.getCode();
        String password = loginForm.getPassword();
        //校验手机号是否合法
        if (RegexUtils.isPhoneInvalid(phone)) {
            return Result.fail("手机号格式错误！");
        }
        //验证码和密码不能同时填写或同时为空
        if (!StrUtil.isEmpty(code) ^ StrUtil.isEmpty(password)) {
            return Result.fail("提交格式不正确");
        }
        boolean isLoginWithPassword = StrUtil.isEmpty(code);
        //验证码登录
        if (!isLoginWithPassword) {
            //从Redis中获取验证码并进行校验
            String cacheCode = stringRedisTemplate.opsForValue().get(LOGIN_CODE_KEY + phone);
            if (cacheCode == null || !cacheCode.equals(code)) {
                //与验证码不一致
                return Result.fail("验证码错误");
            }
        }
        //根据手机号查询用户
        User user = query().eq("phone", phone).one();
        //验证码登录若查不到用户，则注册
        if (user == null && !isLoginWithPassword) {
            user = createUserWithPhone(phone);
        }
        //密码登录
        if (isLoginWithPassword) {
            if (user == null) {
                return Result.fail("用户不存在");
            }
            String storedPassword = user.getPassword();
            if (!PasswordEncoder.matches(storedPassword, password)) {
                return Result.fail("密码错误");
            }
        }

        //保存用户信息到redis中
        String token = UUID.randomUUID().toString(true);
        //将user对象转为hashmap储存
        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
        Map<String, Object> userMap = BeanUtil.beanToMap(userDTO, new HashMap<>(),
                CopyOptions.create().setIgnoreNullValue(true).setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString()));
        //储存
        String tokenKey = LOGIN_USER_KEY + token;
        stringRedisTemplate.opsForHash().putAll(tokenKey, userMap);
        //设置token有效期
        stringRedisTemplate.expire(tokenKey, LOGIN_USER_TTL, TimeUnit.MINUTES);
        //返回token
        return Result.ok(token);
    }

    @Override
    public Result logout(String token) {
        stringRedisTemplate.delete(LOGIN_USER_KEY + token);
        return Result.ok();
    }

    @Override
    @Transactional
    public Result updateInfo(UserInfo userInfo) {
        if(userInfo.getUserId() == null) {
            return Result.fail("fail");
        }
        userInfo.setUpdateTime(LocalDateTime.now());
        userInfoService.updateById(userInfo);
        return Result.ok();
    }


    private User createUserWithPhone(String phone) {
        User user = new User();
        user.setPhone(phone);
        user.setNickName(USER_NICK_NAME_PREFIX + RandomUtil.randomString(10));
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        save(user);
        //创建用户详细信息
        UserInfo userInfo = new UserInfo(user.getId(), "北京", "我的个人介绍", 0, 0, 0, null, LocalDateTime.now(), LocalDateTime.now());
        userInfoService.save(userInfo);
        return user;
    }
}
