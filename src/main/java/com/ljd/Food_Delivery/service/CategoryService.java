package com.ljd.Food_Delivery.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljd.Food_Delivery.domain.entity.CategoryEntity;
import com.ljd.Food_Delivery.dto.request.CategoryRequest;

import java.util.List;

public interface CategoryService extends IService<CategoryEntity> {
    // 查询
    CategoryEntity getEntityById(long id);

    Page<CategoryEntity> getByPage(int page,int pageSize);

    // 新增
    boolean save(CategoryRequest request);
    // 修改
    boolean update(CategoryRequest request);
    // 删除
    boolean deleteByIds(List<Long> ids);
}
