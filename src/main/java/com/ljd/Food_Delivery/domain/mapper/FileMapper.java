package com.ljd.Food_Delivery.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljd.Food_Delivery.domain.entity.FileEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FileMapper extends BaseMapper<FileEntity> {
}
