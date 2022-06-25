package com.ljd.Food_Delivery.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljd.Food_Delivery.domain.entity.OrderDetailEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetailEntity> {
}
