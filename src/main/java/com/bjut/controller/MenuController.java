package com.bjut.controller;

import com.bjut.dto.Result;
import com.bjut.service.IMenuService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/s/query/{id}")
    public Result searchDishById(@PathVariable("id") Long id, @RequestParam("content") String content) {
        return menuService.searchDishById(id, content);
    }

    @GetMapping("/q/me")
    public Result getMe() {
        return menuService.queryMe();
    }

    @DeleteMapping("/del/{id}")
    public Result deleteById(@PathVariable("id") String id) {
        menuService.getBaseMapper().deleteById(id);
        return Result.ok();
    }
}
