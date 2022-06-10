package com.ljd.Food_Delivery.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("shopping_cart")
public class ShoppingCartEntity {

    @TableId
    private long id;

    private String name;

    private String image;

    private long userId;

    private Long dishId;

    private Long setmealId;

    private String dishFlavor;

    private int number;

    private BigDecimal amount;

    private LocalDateTime createTime;

}
