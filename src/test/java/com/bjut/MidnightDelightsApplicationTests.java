package com.bjut;

import com.bjut.entity.Merchant;
import com.bjut.entity.Shop;
import com.bjut.service.IMerchantService;
import com.bjut.service.IShopService;
import com.bjut.utils.PasswordEncoder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class MidnightDelightsApplicationTests {

    @Resource
    private IMerchantService merchantService;

    @Resource
    private IShopService shopService;

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
}
