package com.bjut.controller;

import com.bjut.dto.CartDTO;
import com.bjut.dto.Result;
import com.bjut.service.ICartService;
import com.bjut.utils.UserHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Resource
    private ICartService cartService;

    @GetMapping("/add")
    public Result addCart(@RequestParam("dishId") Long dishId) {
        return cartService.addCart(dishId);
    }

    @PostMapping("/update")
    public Result updateCart(@RequestBody List<CartDTO> dishes) {
        return cartService.updateCart(dishes);
    }

    @GetMapping("remove/{id}")
    public Result removeById(@PathVariable("id") Long id) {
        return cartService.removeCartById(id);
    }

    @GetMapping("/q")
    public Result queryCartsById() {
        Long userId = UserHolder.getUser().getId();
        return cartService.queryAll(userId);
    }

}
