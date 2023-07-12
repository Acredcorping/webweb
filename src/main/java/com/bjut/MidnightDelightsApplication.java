package com.bjut;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.bjut.mapper")
@SpringBootApplication
public class MidnightDelightsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MidnightDelightsApplication.class, args);
    }

}
