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
@TableName("category")
public class CategoryEntity extends BaseEntity{

    // 雪花id
    private Long id;

    private Integer type;

    private String name;

    private int sort;
}
