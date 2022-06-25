package com.ljd.Food_Delivery.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljd.Food_Delivery.domain.entity.SetmealEntity;
import com.ljd.Food_Delivery.dto.response.SetMealResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
//@Repository
public interface SetmealMapper extends BaseMapper<SetmealEntity> {

    boolean updateStatus(@Param("status") int status, @Param("ids") List<Long> ids);

    IPage<SetMealResponse> selectByPage(Page<SetMealResponse> page, @Param("name") String name);
}
