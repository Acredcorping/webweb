package com.bjut.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjut.dto.CartInfoDTO;
import com.bjut.entity.Cart;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CartMapper extends BaseMapper<Cart> {
    @Select("select c.id ,m.title, m.image, m.price, c.number, s.name from midnight_delights.tb_cart c " +
            "join midnight_delights.tb_menu m " +
            "join midnight_delights.tb_shop s " +
            "on c.dish_id = m.id and m.shop_id = s.id where c.user_id = #{userId}")
    List<CartInfoDTO> queryAll(@Param("userId") Long userId);

}
