package com.bjut.service;

import com.bjut.dto.Result;

public interface IOrderService {
    public Result addOrder(Long orderId);
    public Result removeById(Long orderId);
}
