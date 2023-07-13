package com.bjut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjut.dto.Result;
import com.bjut.entity.OrderedDish;
import com.bjut.mapper.OrderedDishMapper;
import com.bjut.service.IOrderedDishService;
import org.springframework.stereotype.Service;

@Service
public class OrderedDishServiceImpl extends ServiceImpl<OrderedDishMapper, OrderedDish> implements IOrderedDishService {
    @Override
    public Result addOrderedDish(Long orderId, Long dishId, Long dishNum) {
        OrderedDish orderedDish = new OrderedDish(null, dishId, orderId, dishNum);
        save(orderedDish);
        return Result.ok();
    }
}
