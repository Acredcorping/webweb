package com.bjut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjut.dto.Result;
import com.bjut.entity.Address;
import com.bjut.mapper.AddressMapper;
import com.bjut.service.IAddressService;
import com.bjut.utils.UserHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {
    @Override
    public Result queryUserAddress() {
        Long id = UserHolder.getUser().getId();
        List<Address> addresses = query().eq("user_id", id).list();
        return Result.ok(addresses);
    }
}
