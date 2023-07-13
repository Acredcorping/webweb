package com.bjut.controller;

import com.bjut.dto.Result;
import com.bjut.mapper.OrderMapper;
import com.bjut.service.IOrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private IOrderService orderService;

    @Resource
    private OrderMapper orderMapper;

    @GetMapping("/add")
    public Result addOrder(@RequestParam("id") Long shopId, Map<Long, Long> dishMap) {
        return orderService.addOrder(shopId, dishMap);
    }

    @GetMapping("/remove/{id}")
    public Result removeById(@PathVariable("id") Long orderId) {
        return Result.ok();
    }

}
