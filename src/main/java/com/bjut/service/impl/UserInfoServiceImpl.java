package com.bjut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjut.entity.UserInfo;
import com.bjut.mapper.UserInfoMapper;
import com.bjut.service.IUserInfoService;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {
}
