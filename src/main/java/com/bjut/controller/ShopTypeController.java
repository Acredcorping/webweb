package com.bjut.controller;

import com.bjut.dto.Result;
import com.bjut.entity.ShopType;
import com.bjut.service.IShopTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/shop-type")
public class ShopTypeController {

    @Resource
    private IShopTypeService shopTypeService;

    @GetMapping("/list")
    public Result queryTypeList() {
        List<ShopType> typeList = shopTypeService
                .query().orderByAsc("sort").list();
        return Result.ok(typeList);
    }
}
