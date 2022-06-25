package com.ljd.Food_Delivery.dto.request;

import com.ljd.Food_Delivery.domain.entity.SetmealDishEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SetMealRequest {

    private Long id;

    private Long categoryId;

    private List<SetmealDishEntity> setmealDishes;

    private String name;

    private BigDecimal price;

    private Integer status;

    private String code;

    private String description;

    private String image;

    private int isDeleted;

}
