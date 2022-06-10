package com.ljd.Food_Delivery.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@TableName("dish_flavor")
public class DishFlavorEntity extends BaseEntity {

    @TableId
    private long id;

    private long dishId;

    private String name;

    private String value;

    private int isDeleted;

}
