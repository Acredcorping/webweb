package com.bjut.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bjut.dto.Result;
import com.bjut.entity.Order;

import java.util.Map;

public interface IOrderService extends IService<Order> {
    public Result addOrder(Long shopId, Map<Long, Long> dishMap);

    public Result removeById(Long orderId);

    public Result updateOrder(Long orderId, int status);

    public Result queryByUserId();
}
