package com.bjut.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjut.entity.OrderedDish;
import org.apache.ibatis.annotations.Param;

public interface OrderedDishMapper extends BaseMapper<OrderedDish> {
    void removeByOrderId(@Param("orderId") Long orderId);
}
