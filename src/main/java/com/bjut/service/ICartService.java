package com.bjut.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bjut.dto.CartDTO;
import com.bjut.dto.Result;
import com.bjut.entity.Cart;

import java.util.List;

public interface ICartService extends IService<Cart> {
    public Result addCart(Long dishId);

    public Result updateCart(List<CartDTO> dishes);

    public Result removeCartById(Long id);

    public Result queryAll(Long id);
}
