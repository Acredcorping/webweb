package com.bjut.config;

import com.bjut.utils.LoginMerchantInterceptor;
import com.bjut.utils.LoginUserInterceptor;
import com.bjut.utils.RefreshTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // token刷新的拦截器
        registry.addInterceptor(new RefreshTokenInterceptor(stringRedisTemplate)).addPathPatterns("/**").order(0);
        // 登录拦截器
        registry.addInterceptor(new LoginUserInterceptor())
                .excludePathPatterns(
                        "/cart/**",
                        "/error",
                        "/shop/**",
                        "/menu/s/**",
                        "/voucher/**",
                        "/shop-type/**",
                        "/upload/**",
                        "/blog/hot",
                        "/user/code",
                        "/user/login",
                        "/user/merchant/login",
                        "/user/merchant/register"
                ).order(1);
        registry.addInterceptor(new LoginMerchantInterceptor())
                .addPathPatterns(
                        "/shop/update/me"
                ).order(2);
    }
}
