package com.bjut.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bjut.dto.Result;
import com.bjut.entity.Shop;

public interface IShopService extends IService<Shop> {

    public Result queryShopByType(Integer typeId, Integer current, Double x, Double y);

    public Result queryById(Long id);

    public Result updateMyShop(Shop shop);
}
