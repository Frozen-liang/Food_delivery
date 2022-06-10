package com.ljd.Food_Delivery.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("user")
public class UserEntity {

  @TableId
  private long id;

  private String name;

  private String phone;

  private String sex;

  private String idNumber;

  private String avatar;

  private Integer status;

}
