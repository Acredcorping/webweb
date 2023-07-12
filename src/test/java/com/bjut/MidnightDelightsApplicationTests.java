package com.bjut;

import com.bjut.dto.CartDTO;
import com.bjut.dto.CartInfoDTO;
import com.bjut.entity.Merchant;
import com.bjut.entity.Shop;
import com.bjut.mapper.CartMapper;
import com.bjut.service.ICartService;
import com.bjut.service.IMerchantService;
import com.bjut.service.IShopService;
import com.bjut.utils.PasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

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
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void PasswordEncoderTest() {
        String userName = "test";
        String password = "123456";
        String encodedPwd = PasswordEncoder.encode(password);
        merchantService.save(new Merchant(null, userName, encodedPwd, 1L));
    }

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
}
