package com.bjut.controller;

import com.bjut.dto.Result;
import com.bjut.entity.Shop;
import com.bjut.service.IShopService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @Resource
    private IShopService shopService;

    @GetMapping("/of/type")
    public Result queryShopByType(@RequestParam("typeId") Integer typeId,
                                  @RequestParam(value = "current", defaultValue = "1") Integer current,
                                  @RequestParam(value = "x", required = false) Double x,
                                  @RequestParam(value = "y", required = false) Double y
    ) {
        return shopService.queryShopByType(typeId, current, x, y);
    }

    @GetMapping("/q/{id}")
    public Result queryShopById(@PathVariable("id") Long id) {
        return shopService.queryById(id);
    }

    @GetMapping("/q/menu/{id}")
    public Result queryShopMenuById(@PathVariable("id") Long id) {
        return Result.ok();
    }

    @PostMapping("/update/me")
    public Result updateMyShop(@RequestBody Shop shop) {
        shop.setUpdateTime(LocalDateTime.now());
        shopService.updateById(shop);
        return Result.ok();
    }
}
