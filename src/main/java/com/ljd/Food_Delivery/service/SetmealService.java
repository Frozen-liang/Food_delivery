package com.ljd.Food_Delivery.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljd.Food_Delivery.domain.entity.SetmealEntity;

import java.util.List;

public interface SetmealService extends IService<SetmealEntity> {

    // 查询
    Page<SetmealEntity> getByPage(int page, int pageSize, String name);

    List<SetmealEntity> getList();

}
