package com.bjut.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.hutool.core.util.StrUtil;

public class QueryWrapperUtils {
    public static <T> QueryWrapper<T> createNameLikeQueryWrapper(String col, String content) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(content)) {
            queryWrapper.like(col, content);
        }
        return queryWrapper;
    }
}
