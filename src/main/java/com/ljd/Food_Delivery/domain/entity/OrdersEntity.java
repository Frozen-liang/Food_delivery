package com.ljd.Food_Delivery.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("orders")
public class OrdersEntity {


  private Long id;

  private String number;

  private int status;

  private long userId;

  private long addressBookId;

  private LocalDateTime orderTime;

  private LocalDateTime checkoutTime;

  private int payMethod;

  private BigDecimal amount;

  private String remark;

  private String phone;

  private String address;

  private String userName;

  private String consignee;

}
