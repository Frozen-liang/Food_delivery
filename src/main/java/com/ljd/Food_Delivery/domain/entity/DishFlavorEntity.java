package com.ljd.Food_Delivery.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@TableName("dish_flavor")
public class DishFlavorEntity extends BaseEntity {

    private Long id;

    private Long dishId;

    private String name;

    private String value;

    private int isDeleted;

}
