package com.bjut.controller;

import com.bjut.dto.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {


    @GetMapping("/add")
    public Result addOrder(Long orderId){ };

    @GetMapping("/remove/{id}")
    public Result removeById(Long orderId){};
}
