package com.ljd.Food_Delivery.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljd.Food_Delivery.convert.CategoryConverter;
import com.ljd.Food_Delivery.domain.entity.CategoryEntity;
import com.ljd.Food_Delivery.domain.entity.DishEntity;
import com.ljd.Food_Delivery.domain.entity.SetmealEntity;
import com.ljd.Food_Delivery.domain.mapper.CategoryMapper;
import com.ljd.Food_Delivery.dto.request.CategoryRequest;
import com.ljd.Food_Delivery.exception.ErrorCode;
import com.ljd.Food_Delivery.exception.FoodException;
import com.ljd.Food_Delivery.service.CategoryService;
import com.ljd.Food_Delivery.service.DishService;
import com.ljd.Food_Delivery.service.SetmealService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryEntity>
        implements CategoryService {

    private final CategoryConverter categoryConverter;
    private final DishService dishService;
    private final SetmealService setmealService;

    public CategoryServiceImpl(CategoryConverter categoryConverter, DishService dishService, SetmealService setmealService) {
        this.categoryConverter = categoryConverter;
        this.dishService = dishService;
        this.setmealService = setmealService;
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
            Page<CategoryEntity> categoryEntityPage = new Page<>(page, pageSize);
            // 创建对象
            LambdaQueryWrapper<CategoryEntity> lqm = new LambdaQueryWrapper<>();
            lqm.orderByDesc(CategoryEntity::getSort);
            return page(categoryEntityPage, lqm);
        } catch (Exception e) {
            throw new FoodException(ErrorCode.CATEGORY_PAGE_ERROR);
        }
    }

    @Override
    public List<CategoryEntity> getList(CategoryRequest request) {
        try {
            // 创建查询条件对象
            LambdaQueryWrapper<CategoryEntity> lqm = new LambdaQueryWrapper<>();
            lqm.eq(request.getType() != null, CategoryEntity::getType, request.getType());
            lqm.orderByDesc(CategoryEntity::getType);
            return list(lqm);
        } catch (Exception e) {
            throw new FoodException(ErrorCode.CATEGORY_PAGE_ERROR);
        }
    }

    @Override
    public List<CategoryEntity> getAddType() {
        List<CategoryEntity> list = list();
        return list;
    }

    @Override
    public boolean save(CategoryRequest request) {
        try {
            CategoryEntity categoryEntity = categoryConverter.toEntity(request);
            return save(categoryEntity);
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
    public boolean deleteById(Long id) {
        try {
            // 判断关联菜品
            LambdaQueryWrapper<DishEntity> delqw = new LambdaQueryWrapper<>();
            delqw.eq(id != null, DishEntity::getCategoryId, id);
            if (dishService.count(delqw) >0 ) {
                throw new FoodException(ErrorCode.CATEGORY_DELETE_ERROR);
            }

            // 判断关联套餐
            LambdaQueryWrapper<SetmealEntity> selqw = new LambdaQueryWrapper<>();
            selqw.eq(id != null, SetmealEntity::getCategoryId, id);
            if (setmealService.count(selqw) >0 ) {
                throw new FoodException(ErrorCode.CATEGORY_DELETE_ERROR);
            }

            return removeById(id);
        } catch (Exception e) {
            throw new FoodException(ErrorCode.CATEGORY_DELETE_ERROR);
        }
    }
}
