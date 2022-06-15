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

    List<CategoryEntity> getList(CategoryRequest request);

    // 菜品添加分类选择类型 查询
    List<CategoryEntity> getAddType();
    // 新增
    boolean save(CategoryRequest request);
    // 修改
    boolean update(CategoryRequest request);
    // 删除
    boolean deleteById(Long ids);
}
