package com.ljd.Food_Delivery.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljd.Food_Delivery.convert.CategoryConverter;
import com.ljd.Food_Delivery.domain.entity.CategoryEntity;
import com.ljd.Food_Delivery.domain.mapper.CategoryMapper;
import com.ljd.Food_Delivery.dto.request.CategoryRequest;
import com.ljd.Food_Delivery.exception.ErrorCode;
import com.ljd.Food_Delivery.exception.FoodException;
import com.ljd.Food_Delivery.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryEntity>
        implements CategoryService {

    private final CategoryConverter categoryConverter;

    public CategoryServiceImpl(CategoryConverter categoryConverter) {
        this.categoryConverter = categoryConverter;
    }

    @Override
    public CategoryEntity getEntityById(long id) {
        return Optional.ofNullable(getById(id))
                .orElseThrow(() -> new FoodException(ErrorCode.CATEGORY_ID_ERROR));
    }

    @Override
    public Page<CategoryEntity> getByPage(int page, int pageSize) {
        try {
            // 构建分页构造器
            Page<CategoryEntity> categoryEntityPage = new Page<>();
            // 创建对象
            LambdaQueryWrapper<CategoryEntity> lqm = new LambdaQueryWrapper<>();

            return page(categoryEntityPage, lqm);
        } catch (Exception e) {
            throw new FoodException(ErrorCode.CATEGORY_PAGE_ERROR);
        }
    }

    @Override
    public boolean save(CategoryRequest request) {
        try {
            CategoryEntity categoryEntity = categoryConverter.toEntity(request);
            return saveOrUpdate(categoryEntity);
        } catch (Exception e) {
            throw new FoodException(ErrorCode.CATEGORY_ADD_ERROR);
        }
    }

    @Override
    public boolean update(CategoryRequest request) {
        try {
            getEntityById(request.getId());
            CategoryEntity categoryEntity = categoryConverter.toEntity(request);
            return saveOrUpdate(categoryEntity);
            //return save(categoryEntity);不能使用save方法
        } catch (Exception e) {
            throw new FoodException(ErrorCode.CATEGORY_UPDATE_ERROR);
        }
    }

    @Override
    public boolean deleteByIds(List<Long> ids) {
        try {
            return removeByIds(ids);
        } catch (Exception e) {
            throw new FoodException(ErrorCode.CATEGORY_DELETE_ERROR);
        }
    }
}
