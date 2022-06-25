package com.ljd.Food_Delivery.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class BaseEntity implements Serializable {

    //@CreatedDate// 自动赋值
    @TableField(fill = FieldFill.INSERT)
    //@JsonIgnore//一般标记在属性或者方法上，返回的json数据即不包含该属性。
    private LocalDateTime createTime;

    //@LastModifiedDate
    @TableField(fill = FieldFill.INSERT_UPDATE)
    //@JsonIgnore
    private LocalDateTime updateTime;

    //@CreatedBy
    @TableField(fill = FieldFill.INSERT)
    @JsonIgnore
    private long createUser;

    //@LastModifiedBy
    @JsonIgnore
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private long updateUser;

    //@TableField(value = "is_delete")    //通过tableField进行字段不一致的映射
    //private int delete;

}
