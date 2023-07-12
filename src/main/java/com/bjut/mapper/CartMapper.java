package com.bjut.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.bjut.dto.CartDTO;
import com.bjut.dto.CartInfoDTO;

import com.bjut.entity.Cart;
import com.bjut.entity.CartDishShop;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CartMapper extends BaseMapper<Cart> {


    @Select("select c.id ,m.title, m.image, c.number, s.name from tb_cart c join tb_menu m join tb_shop s on c.dish_id = m.id and m.shop_id = s.id where c.user_id = 1015")
    List<CartInfoDTO> queryAll(@Param("userId") Long userId);

}
