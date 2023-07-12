package com.bjut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjut.entity.ShopType;
import com.bjut.mapper.ShopTypeMapper;
import com.bjut.service.IShopTypeService;
import org.springframework.stereotype.Service;

@Service
public class ShopTypeServiceImpl extends ServiceImpl<ShopTypeMapper, ShopType> implements IShopTypeService {
}
