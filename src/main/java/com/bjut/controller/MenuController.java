package com.bjut.controller;

import com.bjut.dto.Result;
import com.bjut.service.IMenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private IMenuService menuService;

    @GetMapping("/s/{id}")
    public Result queryByShopId(@PathVariable("id") Long id) {
        return menuService.queryMenuByShopId(id);
    }
}
