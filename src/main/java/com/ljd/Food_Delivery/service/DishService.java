package com.ljd.Food_Delivery.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljd.Food_Delivery.domain.entity.DishEntity;
import com.ljd.Food_Delivery.dto.request.DishRequest;

import java.util.List;

public interface DishService  {
    // 分页查询
    Page<DishEntity> getByPage(int page, int pageSize,String name);

    List<DishEntity> getList();

    // 新增
    boolean save(DishRequest request);

    // 修改
    boolean update(DishRequest request);

    // 删除
    boolean deleteByIds(List<Long> ids);
}
