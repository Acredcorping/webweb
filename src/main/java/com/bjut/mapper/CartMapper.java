package com.bjut.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjut.entity.Cart;
import com.bjut.entity.CartDishShop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper extends BaseMapper<Cart> {
    List<CartDishShop> queryUserId(@Param("userId") Long userId);
}
