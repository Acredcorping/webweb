package com.bjut;


import com.bjut.dto.CartInfoDTO;
import com.bjut.dto.Result;
import com.bjut.entity.Merchant;
import com.bjut.entity.Shop;
import com.bjut.mapper.CartMapper;
import com.bjut.service.IMerchantService;
import com.bjut.service.IShopService;
import com.bjut.service.IUserService;
import com.bjut.service.impl.UserServiceImpl;
import com.bjut.utils.PasswordEncoder;
import com.bjut.utils.RedisIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@SpringBootTest
@Slf4j
class MidnightDelightsApplicationTests {

    @Resource
    private IMerchantService merchantService;

    @Resource
    private IShopService shopService;

    @Resource
    private CartMapper cartMapper;

    @Resource
    private IUserService userService;


    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisIdWorker redisIdWorker;

    private ExecutorService es = Executors.newFixedThreadPool(500);




    @Test
    void updateTest() {
        Shop shop = new Shop();
        shop.setId(26L);
        shop.setAddress("测试地址测试地址");
        shop.setTypeId(2L);
        shopService.updateById(shop);
    }

    @Test
    void queryCartTest() {
        List<CartInfoDTO> cartDTOS = cartMapper.queryAll(1015L);
        log.debug(cartDTOS.toString());
    }

    @Test
    void interceptorTest() {
        String token = "testuser";
        String type = Boolean.TRUE.equals(stringRedisTemplate.hasKey("login:user:token:" + token)) ? "user" :
                Boolean.TRUE.equals(stringRedisTemplate.hasKey("login:merchant:token:" + token)) ? "merchant" : "admin";
        String key = "login:" + type + ":token" + token;

        log.debug(type);
        log.debug(key);
    }

    @Test
    void idWorkerTest() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(300);
        Runnable task = () -> {
            for (int i = 0; i < 100; i++) {
                long id = redisIdWorker.nextId("test");
                System.out.println("id = " + id);
            }
            countDownLatch.countDown();
        };
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 300; i++) {
            es.submit(task);
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();

        System.out.println("time = " + (end - begin));
    }

    @Test
    void test() {
        Result result = shopService.queryById(1L);
        System.out.println(result);
    }
}
