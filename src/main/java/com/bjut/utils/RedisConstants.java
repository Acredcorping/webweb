package com.bjut.utils;

public class RedisConstants {
    public static final String LOGIN_CODE_KEY = "login:code:";
    public static final Long LOGIN_CODE_TTL = 2L;

    public static final String LOGIN_USER_KEY = "login:user:token:";
    public static final Long LOGIN_USER_TTL = 30L;

    public static final String LOGIN_MERCHANT_KEY = "login:merchant:token:";

    public static final Long LOGIN_MERCHANT_TTL = 25L;
    public static final Long CACHE_NULL_TTL = 2L;

    public static final String LOCK_SHOP_KEY = "lock:shop:";
    public static final Long LOCK_SHOP_TTL = 10L;

    public static final String CACHE_SHOP_KEY = "cache:shop:";
    public static final Long CACHE_SHOP_TTL = 30L;

    public static final String CACHE_MENU_KEY = "cache:menu:";

    public static final Long CACHE_MENU_TTL = 30L;
}
