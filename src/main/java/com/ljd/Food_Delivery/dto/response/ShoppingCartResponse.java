package com.ljd.Food_Delivery.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartResponse {

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
