package com.bjut.entity;

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
@TableName("tb_ordered_dish")
@AllArgsConstructor
@NoArgsConstructor
public class OrderedDish implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    private Long dishId;

    private Long orderId;

    private Long dishNum;
}
