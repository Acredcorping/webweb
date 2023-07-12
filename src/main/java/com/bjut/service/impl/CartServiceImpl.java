package com.bjut.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjut.dto.CartDTO;
import com.bjut.dto.Result;
import com.bjut.entity.Cart;
import com.bjut.mapper.CartMapper;
import com.bjut.service.ICartService;
import com.bjut.utils.UserHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {
    @Override
    @Transactional
    public Result addCart(Long dishId) {
        Long userId = UserHolder.getUser().getId();
        //判断是否初次添加
        Cart cart = query().eq("dish_id", dishId).one();
        if (cart == null) {
            cart = new Cart(null, userId, dishId, 1);
            save(cart);
        } else {
            //不是初次添加, 数量+1
            cart.addNumber();
            updateById(cart);
        }
        return Result.ok();
    }

    @Override
    public Result updateCart(List<CartDTO> dishes) {
        Long userId = UserHolder.getUser().getId();
        List<Cart> carts = new ArrayList<>();

        for(CartDTO dish : dishes) {
            Cart cart = new Cart();
            BeanUtil.copyProperties(dish, cart, CopyOptions.create().setIgnoreNullValue(true));
            cart.setUserId(userId);
            carts.add(cart);
        }
        log.debug(carts.toString());
        saveOrUpdateBatch(carts);
        return Result.ok();
    }

    @Override
    @Transactional
    public Result removeCartById(Long id) {
        Cart cart = getById(id);
        if (cart == null){
            return Result.fail("id不存在");
        }
        removeById(id);
        return Result.ok();
    }
}
