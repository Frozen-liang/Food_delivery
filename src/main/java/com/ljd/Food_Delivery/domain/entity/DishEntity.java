package com.ljd.Food_Delivery.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@TableName("dish")
public class DishEntity extends BaseEntity{

  private Long id;

  private String name;

  // 菜品分类
  private Long categoryId;

  private BigDecimal price;

  @JsonIgnore
  private String code;

  private String image;

  private String description;

  private int status;

  @JsonIgnore
  private int sort;

  @JsonIgnore
  private int isDeleted;

}
