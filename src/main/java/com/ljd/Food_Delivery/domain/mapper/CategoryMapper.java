package com.ljd.Food_Delivery.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljd.Food_Delivery.domain.entity.CategoryEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
//@Repository
public interface CategoryMapper extends BaseMapper<CategoryEntity> {
}
