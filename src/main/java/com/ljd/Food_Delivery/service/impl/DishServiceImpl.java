package com.ljd.Food_Delivery.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljd.Food_Delivery.convert.DishConvert;
import com.ljd.Food_Delivery.domain.entity.DishEntity;
import com.ljd.Food_Delivery.domain.entity.DishFlavorEntity;
import com.ljd.Food_Delivery.domain.mapper.DishMapper;
import com.ljd.Food_Delivery.dto.request.DishRequest;
import com.ljd.Food_Delivery.exception.ErrorCode;
import com.ljd.Food_Delivery.exception.FoodException;
import com.ljd.Food_Delivery.service.DishFlavorService;
import com.ljd.Food_Delivery.service.DishService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, DishEntity> implements DishService {

    private final DishFlavorService dishFlavorService;
    private final DishConvert dishConvert;

    public DishServiceImpl(DishFlavorService dishFlavorService, DishConvert dishConvert) {
        this.dishFlavorService = dishFlavorService;
        this.dishConvert = dishConvert;
    }

    @Override
    public Page<DishEntity> getByPage(int page, int pageSize,String name) {
        try {
            // 构建分页构造器
            Page<DishEntity> dishEntityPage = new Page<>(page, pageSize);
            // 创造条件查询对象
            LambdaQueryWrapper<DishEntity> dish = new LambdaQueryWrapper<>();
            dish.like(StringUtils.isNotEmpty(name),DishEntity::getName,name);
            // 条件查询 先按照sort分类 如果相同 按照创建时间
            dish.orderByDesc(DishEntity::getSort).orderByDesc(DishEntity::getCreateTime);
            // 分页查询
            return page(dishEntityPage, dish);
        } catch (Exception e) {
            throw new FoodException(ErrorCode.DISH_PAGE_ERROR);
        }
    }

    @Override
    public List<DishEntity> getList() {
        try {
            LambdaQueryWrapper<DishEntity> dlqw = new LambdaQueryWrapper<>();
            dlqw.orderByDesc(DishEntity::getCreateTime);
            return list(dlqw);
        } catch (Exception e) {
            throw new FoodException(ErrorCode.SHOPPING_CART_LIST_ERROR);
        }
    }

    @Override
    public boolean save(DishRequest request) {
        try {
            // 先保存到dish表中
            DishEntity dishEntity = dishConvert.toEntity(request);
            save(dishEntity);

            // 保存到dish_flavor表中
            List<DishFlavorEntity> dishFlavor = request.getFlavors();
            // 设置CategoryId
            List<DishFlavorEntity> list = dishFlavor.stream()
                    .peek((dishFlavorEntity) -> dishFlavorEntity.setDishId(request.getId()))
                    .collect(Collectors.toList());
            // 保存到 dishFlavor表中
            dishFlavorService.saveBatch(list);
            return true;
        } catch (Exception e) {
            throw new FoodException(ErrorCode.DISH_SAVE_ERROR);
        }
    }

    @Override
    public boolean update(DishRequest request) {
        return false;
    }

    @Override
    public boolean deleteByIds(List<Long> ids) {
        try {
            return removeByIds(ids);
        } catch (Exception e) {
            throw new FoodException(ErrorCode.DISH_DELETE_ERROR);
        }
    }
}
