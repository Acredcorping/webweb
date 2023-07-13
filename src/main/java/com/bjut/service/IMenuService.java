package com.bjut.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bjut.dto.Result;
import com.bjut.entity.Dish;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface IMenuService extends IService<Dish> {

    public Result queryMenuByShopId(Long id);

    public Result searchDishById(Long id, String content);

    public Result queryMe();

}
