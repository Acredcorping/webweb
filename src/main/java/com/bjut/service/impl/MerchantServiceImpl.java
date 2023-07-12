package com.bjut.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjut.dto.MerchantDTO;
import com.bjut.dto.Result;
import com.bjut.entity.Merchant;
import com.bjut.entity.Shop;
import com.bjut.mapper.MerchantMapper;
import com.bjut.service.IMerchantService;
import com.bjut.service.IShopService;
import com.bjut.utils.PasswordEncoder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.bjut.utils.RedisConstants.LOGIN_MERCHANT_KEY;
import static com.bjut.utils.RedisConstants.LOGIN_MERCHANT_TTL;

@Service
public class MerchantServiceImpl extends ServiceImpl<MerchantMapper, Merchant> implements IMerchantService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private IShopService shopService;

    @Override
    @Transactional
    public Result login(String userName, String password) {
        Merchant merchant = query().eq("user_name", userName).one();
        if (merchant == null) {
            return Result.fail("用户不存在");
        }
        String pwd = merchant.getPassword();
        if (!PasswordEncoder.matches(pwd, password)) {
            return Result.fail("密码错误");
        }

        //保存用户信息到redis中
        String token = UUID.randomUUID().toString(true);
        String tokenKey = LOGIN_MERCHANT_KEY + token;
        //将merchant对象转为hashmap储存
        MerchantDTO merchantDTO = BeanUtil.copyProperties(merchant, MerchantDTO.class);
        Map<String, Object> merchantMap = BeanUtil.beanToMap(merchantDTO, new HashMap<>(),
                CopyOptions.create().setIgnoreNullValue(true).setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString()));
        stringRedisTemplate.opsForHash().putAll(tokenKey, merchantMap);
        stringRedisTemplate.expire(tokenKey, LOGIN_MERCHANT_TTL, TimeUnit.MINUTES);
        return Result.ok(token);
    }

    @Override
    @Transactional
    public Result register(String userName, String password) {
        Merchant merchant = query().eq("user_name", userName).one();
        if (merchant != null) {
            return Result.fail("用户名已存在");
        }
        String encodedPassword = PasswordEncoder.encode(password);
        //创建一个新的商铺
        String shopName = userName + "_" + RandomUtil.randomString(15);
        Shop shop = new Shop(null, shopName, 0L, null,
                null, null, null, null, 0L, 0, 0, null, null,
                LocalDateTime.now(), LocalDateTime.now(), null);
        shopService.save(shop);
        shop = shopService.query().eq("name", shopName).one();
        Merchant newMerchant = new Merchant(null, userName, encodedPassword, shop.getId());
        save(newMerchant);
        return Result.ok();
    }
}
