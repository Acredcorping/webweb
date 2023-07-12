package com.bjut;

import com.bjut.entity.CartDishShop;
import com.bjut.entity.Shop;
import com.bjut.mapper.CartMapper;
import com.bjut.service.IMerchantService;
import com.bjut.service.IShopService;
import com.bjut.utils.PasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@Slf4j
class MidnightDelightsApplicationTests {

    @Resource
    private IMerchantService merchantService;

    @Resource
    private IShopService shopService;

    @Resource
    private CartMapper cartMapper;

    @Test
    void PasswordEncoderTest() {
        String userName = "test";
        String password = "123456";
        String encodedPwd = PasswordEncoder.encode(password);
        //merchantService.save(new Merchant(null, userName, encodedPwd, 1L));
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
    void testzr() {
        List<CartDishShop> cartDTOS = cartMapper.queryUserId(1015L);
        System.out.println(cartDTOS.size());

        System.out.println(cartDTOS.toString());
    }


}
