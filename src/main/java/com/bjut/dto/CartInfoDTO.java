package com.bjut.dto;

import lombok.Data;

import java.util.HashMap;

@Data
public class CartInfoDTO {

//    public static class DishInfo {
//        protected String title;
//
//        protected String image;
//
//        protected Integer number;
//    }

    private Long id;

    private String name;

    private String title;

    private String image;

    private Integer number;

//    private HashMap<String, DishInfo> data;
}
