package com.bjut.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bjut.dto.Result;
import com.bjut.entity.Order;

public interface IOrderService extends IService<Order> {
    public Result addOrder(Long orderId);
    public Result removeById(Long orderId);
}
