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
@TableName("tb_cart")
@AllArgsConstructor
@NoArgsConstructor
public class Cart implements Serializable {

    private static final long serialVersionUID = 4000L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Long dishId;

    private Integer number;

    public void addNumber() {
        this.number++;
    }

    public void minusNumber() {
        this.number--;
    }
}
