package com.bjut.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjut.dto.CartDTO;
import com.bjut.entity.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper extends BaseMapper<Cart> {

    List<CartDTO> queryAll(@Param("userId") Long userId);
}
