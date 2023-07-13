package com.bjut.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjut.entity.Shop;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ShopMapper extends BaseMapper<Shop> {

    @Select("SELECT s.* FROM tb_menu m RIGHT JOIN tb_shop s ON s.id = m.shop_id WHERE s.name LIKE CONCAT('%', #{content}, '%')" +
            " OR m.title LIKE CONCAT('%', #{content}, '%')")
    List<Shop> searchShopByNameAndDish(@Param("content") String content);
}
