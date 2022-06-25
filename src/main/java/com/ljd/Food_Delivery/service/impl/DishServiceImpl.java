package com.ljd.Food_Delivery.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljd.Food_Delivery.convert.DishConvert;
import com.ljd.Food_Delivery.domain.entity.CategoryEntity;
import com.ljd.Food_Delivery.domain.entity.DishEntity;
import com.ljd.Food_Delivery.domain.entity.DishFlavorEntity;
import com.ljd.Food_Delivery.domain.mapper.DishMapper;
import com.ljd.Food_Delivery.dto.request.DishRequest;
import com.ljd.Food_Delivery.dto.response.DishResponse;
import com.ljd.Food_Delivery.exception.ErrorCode;
import com.ljd.Food_Delivery.exception.FoodException;
import com.ljd.Food_Delivery.service.CategoryService;
import com.ljd.Food_Delivery.service.DishFlavorService;
import com.ljd.Food_Delivery.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, DishEntity> implements DishService {

    private final DishFlavorService dishFlavorService;
    private final DishConvert dishConvert;
    @Lazy
    @Autowired
    private CategoryService categoryService;

    public DishServiceImpl(DishFlavorService dishFlavorService, DishConvert dishConvert) {
        this.dishFlavorService = dishFlavorService;
        this.dishConvert = dishConvert;
    }

    @Override
    public IPage<DishResponse> getByPage(int page, int pageSize, String name) {
        try {
            // 构建分页构造器
            Page<DishResponse> dishResponsePage = new Page<>(page, pageSize);
            return getBaseMapper().selectByPage(dishResponsePage, name);
//            // 创造条件查询对象
//            LambdaQueryWrapper<DishEntity> dish = new LambdaQueryWrapper<>();
//            dish.like(StringUtils.isNotEmpty(name), DishEntity::getName, name);
//            // 条件查询 先按照sort分类 如果相同 按照创建时间
//            dish.orderByDesc(DishEntity::getSort).orderByDesc(DishEntity::getCreateTime);
//            // 分页查询
//            return page(dishEntityPage, dish);

// 自定义分页

// 升级1
//            BeanUtils.copyProperties(dishEntityPage, dishEntityPage, "records");
//
//            List<DishEntity> records = dishEntityPage.getRecords();
//
//            List<DishRequest> requests = records.stream().map((tpe) -> {
//                DishRequest dishRequest = new DishRequest();
//                Long categoryId = dishRequest.getCategoryId();
//
//                CategoryEntity category = categoryService.getById(categoryId);
//
//                if (category != null) {
//                    dishRequest.setCategoryName(category.getName());
//                }
//                return dishRequest;
//            }).collect(Collectors.toList());
//
//            return requests;
        } catch (Exception e) {
            throw new FoodException(ErrorCode.DISH_PAGE_ERROR);
        }
    }

    @Override
    public List<DishResponse> getList(DishRequest request) { // 接收到分类CategoryId
        try {
            LambdaQueryWrapper<DishEntity> dlqw = new LambdaQueryWrapper<>();
            // 根据分类底下的菜品查出来
            dlqw.eq(request.getCategoryId() != null, DishEntity::getCategoryId, request.getCategoryId());
            // 判断菜品的售卖状态
            dlqw.eq(DishEntity::getStatus, 1);

            // 排序
            dlqw.orderByDesc(DishEntity::getCreateTime);

            List<DishEntity> dishEntityList = list(dlqw);

            return dishEntityList.stream().map((flavor) -> {
                DishResponse dishResponse = new DishResponse();
                BeanUtils.copyProperties(flavor, dishResponse);
                Long categoryId = flavor.getCategoryId();
                CategoryEntity categoryEntity = categoryService.getById(categoryId);
                if (categoryEntity != null)
                    dishResponse.setCategoryName(categoryEntity.getName());

                Long dishId = flavor.getId();
                LambdaQueryWrapper<DishFlavorEntity> dflqw = new LambdaQueryWrapper<>();
                dflqw.eq(DishFlavorEntity::getDishId, dishId);
                dishResponse.setFlavors(dishFlavorService.list(dflqw));
                return dishResponse;
            }).collect(Collectors.toList());

        } catch (Exception e) {
            throw new FoodException(ErrorCode.SHOPPING_CART_LIST_ERROR);
        }
    }

    @Override
    public DishResponse getEntityById(Long id) {
        // 查找菜单信息
        DishEntity dishEntity = getById(id);
        // 转换
        DishResponse dishResponse = dishConvert.toDishResponse(dishEntity);
        // 查询DishFlavor表
        LambdaQueryWrapper<DishFlavorEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(DishFlavorEntity::getDishId, id);
        List<DishFlavorEntity> list = dishFlavorService.list(lqw);
        // 存放
        dishResponse.setFlavors(list);

        return dishResponse;
    }

    @Override
    public boolean save(DishRequest request) {
        try {
            // 先保存到dish表中
            DishEntity dishEntity = dishConvert.toEntity(request);
            dishEntity.setCode("123");
            save(dishEntity);

            // 保存到dish_flavor表中
            List<DishFlavorEntity> dishFlavor = request.getFlavors();
            // 设置setDishId
            List<DishFlavorEntity> list = dishFlavor.stream()
                    .peek((dishFlavorEntity) -> dishFlavorEntity.setDishId(dishEntity.getId()))
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
        try {
            DishEntity dishEntity = dishConvert.toEntity(request);
            // 根据id更新dish表
            updateById(dishEntity);

            // 清除dish_flavor表中关联数据
            LambdaQueryWrapper<DishFlavorEntity> lqw = new LambdaQueryWrapper<>();
            lqw.eq(DishFlavorEntity::getDishId, request.getId());
            dishFlavorService.remove(lqw);

            // 更新dish_flavor表
            List<DishFlavorEntity> flavors = request.getFlavors();
            List<DishFlavorEntity> list = flavors.stream()
                    .peek((dishFlavorEntity) -> dishFlavorEntity.setDishId(dishEntity.getId()))
                    .collect(Collectors.toList());
            dishFlavorService.saveBatch(list);
        } catch (Exception e) {
            throw new FoodException(ErrorCode.DISH_UPDATE_ERROR);
        }

        return true;
    }

    @Override
    public boolean updateStatus(int status, List<Long> ids) {
        return getBaseMapper().updateStatus(status, ids);
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
