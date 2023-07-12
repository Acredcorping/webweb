package com.bjut.entity;

import lombok.Data;

@Data
public class CartDishShop {
    private Long userId;
    private Dish dish;
    private Shop shop;
}
