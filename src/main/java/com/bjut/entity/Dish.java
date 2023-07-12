package com.bjut.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_menu")
@AllArgsConstructor
@NoArgsConstructor
public class Dish implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商铺Id
     */
    private Long shopId;

    /**
     * 菜品标题
     */
    private String title;

    /**
     * 菜品描述
     */
    private String description;

    /**
     * 菜品图片
     */
    private String image;

    /**
     * 菜品价格
     */
    private Long price;
}
