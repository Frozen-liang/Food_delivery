package com.ljd.Food_Delivery.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@TableName("setmeal_dish")
public class SetmealDishEntity extends BaseEntity {

    @TableId
    private Long id;

    private String setmealId;

    private String dishId;

    private String name;

    private BigDecimal price;

    private int copies;

    private int sort;

    private int isDeleted;

}
