package com.ljd.Food_Delivery.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DishResponse {

    private long id;

    private String name;

    private long categoryId;

    private BigDecimal price;

    private String code;

    private String image;

    private String description;

    private int status;

    private int sort;

    private int isDeleted;

}
