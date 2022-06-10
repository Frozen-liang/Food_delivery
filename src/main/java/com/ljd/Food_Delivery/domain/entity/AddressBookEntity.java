package com.ljd.Food_Delivery.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.*;
import lombok.experimental.SuperBuilder;

// 此注解会生成equals(Object other) 和 hashCode()方法。
@EqualsAndHashCode(callSuper = true)//可通过callSuper=true解决上一点问题。让其生成的方法中调用父类的方法。
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@TableName("address_book")
public class AddressBookEntity extends BaseEntity{

    @TableId
    private long id;

    private long userId;

    private String consignee;

    private byte sex;

    private String phone;

    private String provinceCode;

    private String provinceName;

    private String cityCode;

    private String cityName;

    private String districtCode;

    private String districtName;

    private String detail;

    private String label;

    private byte isDefault;

    private int isDeleted;
}
