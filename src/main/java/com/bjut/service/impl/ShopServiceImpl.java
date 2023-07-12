package com.bjut.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjut.dto.Result;
import com.bjut.entity.Dish;
import com.bjut.entity.Shop;
import com.bjut.mapper.ShopMapper;
import com.bjut.service.IShopService;
import com.bjut.utils.CacheClient;
import com.bjut.utils.SystemConstants;
import com.bjut.utils.UserHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.concurrent.TimeUnit;

import static com.bjut.utils.RedisConstants.*;

@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {

    @Resource
    private CacheClient cacheClient;
    @Override
    public Result queryShopByType(Integer typeId, Integer current, Double x, Double y) {
        if (x == null || y == null) {
            // 不需要根据坐标查询，按照数据库进行查询
            Page<Shop> page = query().eq("type_id", typeId)
                    .page(new Page<>(current, SystemConstants.DEFAULT_PAGE_SIZE));
            //返回
            return Result.ok(page.getRecords());
        }

        // TODO 坐标查询
        return null;
    }

    @Override
    public Result queryById(Long id) {
        //查询-添加到redis缓存-缓存穿透
        Shop shop = cacheClient.queryWithPassThrough(CACHE_SHOP_KEY, id, Shop.class, this::getById, CACHE_SHOP_TTL, TimeUnit.MINUTES);
        if (shop == null) {
            return Result.fail("商铺不存在");
        }
        return Result.ok(shop);
    }

    @Override
    @Transactional
    public Result updateMyShop(Shop shop) {

        updateById(shop);
        return Result.ok();
    }

}
