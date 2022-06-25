package com.ljd.Food_Delivery.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljd.Food_Delivery.convert.SetMealConvert;
import com.ljd.Food_Delivery.domain.entity.SetmealDishEntity;
import com.ljd.Food_Delivery.domain.entity.SetmealEntity;
import com.ljd.Food_Delivery.domain.mapper.SetmealMapper;
import com.ljd.Food_Delivery.dto.request.SetMealRequest;
import com.ljd.Food_Delivery.dto.response.SetMealResponse;
import com.ljd.Food_Delivery.exception.ErrorCode;
import com.ljd.Food_Delivery.exception.FoodException;
import com.ljd.Food_Delivery.service.SetmealDishService;
import com.ljd.Food_Delivery.service.SetmealService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, SetmealEntity>
        implements SetmealService {

    private final SetMealConvert convert;
    private final SetmealDishService setmealDishService;

    public SetmealServiceImpl(SetMealConvert convert, SetmealDishService setmealDishService) {
        this.convert = convert;
        this.setmealDishService = setmealDishService;
    }

    @Override
    public IPage<SetMealResponse> getByPage(int page, int pageSize, String name) {
        try {
            // 构建分页构造器
            Page<SetMealResponse> setMealResponsePage = new Page<>(page, pageSize);
            return getBaseMapper().selectByPage(setMealResponsePage,name);
//            // 创建分页构造器
//            Page<SetmealEntity> setmealEntityPage = new Page<>(page, pageSize);
//            LambdaQueryWrapper<SetmealEntity> slqw = new LambdaQueryWrapper<>();
//            // 三个参数 判断是否为空 列名 查询的值
//            slqw.like(StringUtils.isNotEmpty(name), SetmealEntity::getName, name);
//            // 降序排序
//            slqw.orderByDesc(SetmealEntity::getCreateTime);
//            // 分页查询
//            return page(setmealEntityPage, slqw);
        } catch (Exception e) {
            throw new FoodException(ErrorCode.EMPLOYEE_PAGE_ERROR);
        }
    }

    @Override
    public SetMealResponse getEntityById(Long id) {
        // 查询两个表 setmealEntity
        SetmealEntity setmealEntity = getById(id);
        SetMealResponse setMealResponse = convert.toSetMealResponse(setmealEntity);

        // SetmealDishEntity
        LambdaQueryWrapper<SetmealDishEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(SetmealDishEntity::getSetmealId, setmealEntity.getId());
        List<SetmealDishEntity> list = setmealDishService.list(lqw);

        // 整合
        setMealResponse.setSetmealDishes(list);

        return setMealResponse;

    }

    @Override
    public List<SetmealEntity> getList(SetMealRequest request) {
        try {
            LambdaQueryWrapper<SetmealEntity> slqw = new LambdaQueryWrapper<>();
            // 查询分类下的套餐
            slqw.eq(request.getCategoryId() != null, SetmealEntity::getCategoryId, request.getCategoryId());
            // 判断套餐售卖状态
            slqw.eq(SetmealEntity::getStatus, 1);
            slqw.orderByDesc(SetmealEntity::getUpdateTime);
            return list(slqw);
        } catch (Exception e) {
            throw new FoodException(ErrorCode.SHOPPING_CART_LIST_ERROR);
        }
    }

    @Override
    public boolean save(SetMealRequest request) {
        try {
            // 保存到SetmealEntity
            SetmealEntity setmealEntity = convert.toEntity(request);
            save(setmealEntity);

            // 获取setmealDishes
            List<SetmealDishEntity> setmealDishes = request.getSetmealDishes();
            // 关联到保存到SetmealEntity id
            List<SetmealDishEntity> list = setmealDishes.stream().peek((sid) -> sid.setSetmealId(setmealEntity.getId()))
                    .collect(Collectors.toList());
            setmealDishService.saveBatch(list);
        } catch (Exception e) {
            throw new FoodException(ErrorCode.DISH_SAVE_ERROR);
        }
        return true;
    }

    @Override
    public boolean update(SetMealRequest request) {
        try {
            // 先更新
            SetmealEntity setmealEntity = convert.toEntity(request);
            updateById(setmealEntity);

            // 清空原来获取setmealDishes表 根据setmeal表中的id
            LambdaQueryWrapper<SetmealDishEntity> lqw = new LambdaQueryWrapper<>();
            lqw.eq(SetmealDishEntity::getSetmealId, setmealEntity.getId());
            setmealDishService.remove(lqw);

            // 重新保存 setmealDishes表
            List<SetmealDishEntity> setmealDishes = request.getSetmealDishes();
            List<SetmealDishEntity> list = setmealDishes.stream().peek((sid) -> sid.setSetmealId(setmealEntity.getId()))
                    .collect(Collectors.toList());
            setmealDishService.saveBatch(list);
        } catch (Exception e) {
            throw new FoodException(ErrorCode.CATEGORY_UPDATE_ERROR);
        }
        return true;
    }

    @Override
    public boolean updateStatus(int status, List<Long> ids) {
        return getBaseMapper().updateStatus(status, ids);
    }

    @Override
    public boolean deleteByIds(List<Long> ids) {
        return removeByIds(ids);
    }
}
