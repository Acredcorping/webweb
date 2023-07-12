package com.bjut.controller;

import com.bjut.dto.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @GetMapping("/add")
    public Result addOrder(@RequestParam("id") Long orderId){
        return Result.ok();
    };

    @GetMapping("/remove/{id}")
    public Result removeById(@PathVariable("id") Long orderId){
        return Result.ok();
    };
}
