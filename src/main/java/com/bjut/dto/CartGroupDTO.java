package com.bjut.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CartGroupDTO {
    private String name;

    private List<CartInfoDTO> dishes;
}
