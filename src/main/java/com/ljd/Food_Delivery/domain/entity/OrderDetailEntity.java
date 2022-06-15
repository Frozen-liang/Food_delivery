package com.ljd.Food_Delivery.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("order_detail")
public class OrderDetailEntity {

  @TableId
  private Long id;

  private String name;

  private String image;

  private long orderId;

  private Long dishId;

  private Long setmealId;

  private String dishFlavor;

  private int number;

  private BigDecimal amount;

}
