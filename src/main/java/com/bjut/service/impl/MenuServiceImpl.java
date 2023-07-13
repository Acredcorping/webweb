package com.bjut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjut.dto.Result;
import com.bjut.entity.Dish;
import com.bjut.mapper.DishMapper;
import com.bjut.service.IMenuService;
import com.bjut.utils.CacheClient;
import com.bjut.utils.QueryWrapperUtils;
import com.bjut.utils.UserHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.bjut.utils.RedisConstants.CACHE_MENU_KEY;
import static com.bjut.utils.RedisConstants.CACHE_MENU_TTL;

@Service
public class MenuServiceImpl extends ServiceImpl<DishMapper, Dish> implements IMenuService {
    @Resource
    private CacheClient cacheClient;
    @Override
    public Result queryMenuByShopId(Long id) {
        List<Dish> dishes = cacheClient.queryListWithPassThrough(CACHE_MENU_KEY, id, Dish.class, this::test, CACHE_MENU_TTL, TimeUnit.MINUTES);
        return Result.ok(dishes);
    }

    @Override
    public Result searchDishById(Long id, String content) {
        QueryWrapper<Dish> queryWrapper = QueryWrapperUtils.createNameLikeQueryWrapper("title", content);
        queryWrapper.eq("shop_id", id);
        List<Dish> dishes = getBaseMapper().selectList(queryWrapper);
        return Result.ok(dishes);
    }

    @Override
    public Result queryMe() {
        Long id = UserHolder.getMerchant().getShopId();
        List<Dish> dishes = query().eq("shop_id", id).list();
        return Result.ok(dishes);
    }

    private List<Dish> test(Long id) {
        return query().eq("shop_id", id).list();
    }
}
