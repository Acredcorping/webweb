package com.bjut.utils;

import com.bjut.dto.MerchantDTO;
import com.bjut.dto.UserDTO;

public class UserHolder {
    private static final ThreadLocal<UserDTO> tl_user = new ThreadLocal<>();
    private static final ThreadLocal<MerchantDTO> tl_merchant = new ThreadLocal<>();

    public static void saveUser(UserDTO user) {
        tl_user.set(user);
    }
    public static void saveUser(MerchantDTO merchant) {
        tl_merchant.set(merchant);
    }

    public static UserDTO getUser() {
        return tl_user.get();
    }

    public static MerchantDTO getMerchant() {
        return tl_merchant.get();
    }

    public static void removeUser() {
        tl_user.remove();
    }
}
