package com.bjut;


import com.bjut.utils.PasswordEncoder;
import org.junit.jupiter.api.Test;

public class NoneSpringTest {

    @Test
    void PasswordEncoderTest() {
        String password = "123456";
        String encodedPwd = PasswordEncoder.encode(password);
        System.out.println(encodedPwd);
    }
}
