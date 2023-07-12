package com.bjut.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bjut.dto.Result;
import com.bjut.entity.Dish;

public interface IMenuService extends IService<Dish> {

    public Result queryMenuByShopId(Long id);

}
