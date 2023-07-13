package com.bjut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjut.dto.Result;
import com.bjut.entity.Order;
import com.bjut.mapper.OrderMapper;
import com.bjut.mapper.OrderedDishMapper;
import com.bjut.service.IOrderService;
import com.bjut.utils.RedisIdWorker;
import com.bjut.utils.UserHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
    public static final int STATUS_READY = 0;
    public static final int STATUS_SUCCESS = 1;
    public static final int STATUS_CANCEL = -1;

    @Resource
    public OrderedDishServiceImpl orderedDishService;

    @Resource
    public OrderedDishMapper orderedDishMapper;

    @Resource
    public OrderMapper orderMapper;

    @Resource
    private RedisIdWorker redisIdWorker;

    @Override
    public Result addOrder(Long shopId, Map<Long, Long> dishMap) {
        Long userId = UserHolder.getUser().getId();
        LocalDateTime createTime = LocalDateTime.now();
        LocalDateTime updateTime = createTime;

        long orderId = redisIdWorker.nextId("order");

        Order order = new Order(orderId, userId, shopId, createTime, updateTime, STATUS_READY);
        save(order);

        for (Map.Entry<Long, Long> entry : dishMap.entrySet()) {
            long dishId = entry.getKey();
            long num = entry.getValue();
            orderedDishService.addOrderedDish(orderId, dishId, num);
        }
        return Result.ok();
    }

    @Override
    public Result removeByOrderId(Long id) {
        Order order = getById(id);
        if (order == null) {
            return Result.fail("id不存在");
        }
        removeById(id);
        /**
         * 2、删除OrderedDish
         */
        orderedDishMapper.removeByOrderId(id);
        return Result.ok();
    }

    @Override
    public Result updateOrder(Long orderId, int status) {
        Order order = getById(orderId);
        if (order == null) {
            return Result.fail("fail");
        }
        order.setUpdateTime(LocalDateTime.now());
        order.setOrderStatus(status);
        updateById(order);
        return Result.ok();
    }

    @Override
    public Result queryByUserId() {
        Long userId = UserHolder.getUser().getId();
        List<Order> orderList = orderMapper.queryAllByUserId(userId);
        return Result.ok(orderList);
    }
}