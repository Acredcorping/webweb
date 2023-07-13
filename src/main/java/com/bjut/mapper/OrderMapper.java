package com.bjut.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjut.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper extends BaseMapper<Order> {
    List<Order> queryAllByUserId(@Param("userId") Long userId);
}
