package com.bjut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjut.dto.Result;
import com.bjut.entity.Order;
import com.bjut.mapper.OrderMapper;
import com.bjut.service.IOrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
    @Override
    public Result addOrder(Long orderId) {
        return null;
    }

    @Override
    public Result removeById(Long orderId) {
        return null;
    }
}
