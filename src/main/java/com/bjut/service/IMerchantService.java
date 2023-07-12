package com.bjut.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bjut.dto.Result;
import com.bjut.entity.Merchant;

public interface IMerchantService extends IService<Merchant> {

    public Result login(String userName, String password);

    public Result register(String userName, String password);
}
