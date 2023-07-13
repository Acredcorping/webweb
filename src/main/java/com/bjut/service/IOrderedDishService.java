package com.bjut.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bjut.dto.Result;
import com.bjut.entity.OrderedDish;

public interface IOrderedDishService extends IService<OrderedDish> {
    public Result addOrderedDish(Long orderId, Long dishId, Long dishNum);
}