package com.bjut.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bjut.dto.Result;
import com.bjut.entity.Address;

import java.util.List;

public interface IAddressService extends IService<Address> {

    Result queryUserAddress();
}
