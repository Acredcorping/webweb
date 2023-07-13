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

    @PostMapping("/add")
    public Result addOrder(@RequestParam("id") Long shopId, @RequestBody Map<Long, Long> dishMap) {
        return orderService.addOrder(shopId, dishMap);
    }

    @GetMapping("/remove")
    public Result removeById(@RequestParam("id") Long orderId) {
        return orderService.removeByOrderId(orderId);
    }

    @GetMapping("/q")
    public Result queryByUserId() {
        return orderService.queryByUserId();
    }

    @GetMapping("/update")
    public Result updateOrder(@RequestParam("orderId") Long orderId, @RequestParam("status") int status) {
        return orderService.updateOrder(orderId, status);
    }
}
