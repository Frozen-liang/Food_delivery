package com.ljd.Food_Delivery.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("dish")
public class DishEntity extends BaseEntity{

  @TableId
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
