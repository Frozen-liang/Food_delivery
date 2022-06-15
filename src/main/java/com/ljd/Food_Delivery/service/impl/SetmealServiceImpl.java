package com.ljd.Food_Delivery.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljd.Food_Delivery.domain.entity.SetmealEntity;
import com.ljd.Food_Delivery.domain.mapper.SetmealMapper;
import com.ljd.Food_Delivery.exception.ErrorCode;
import com.ljd.Food_Delivery.exception.FoodException;
import com.ljd.Food_Delivery.service.SetmealService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, SetmealEntity>
        implements SetmealService {

    @Override
    public Page<SetmealEntity> getByPage(int page, int pageSize, String name) {
        try {
            // 创建分页构造器
            Page<SetmealEntity> setmealEntityPage = new Page<>(page, pageSize);
            LambdaQueryWrapper<SetmealEntity> slqw = new LambdaQueryWrapper<>();
            // 三个参数 判断是否为空 列名 查询的值
            slqw.like(StringUtils.isNotEmpty(name), SetmealEntity::getName, name);
            // 降序排序
            slqw.orderByDesc(SetmealEntity::getCreateTime);
            // 分页查询
            return page(setmealEntityPage, slqw);
        } catch (Exception e) {
            throw new FoodException(ErrorCode.EMPLOYEE_PAGE_ERROR);
        }
    }

    @Override
    public List<SetmealEntity> getList() {
        try {
            LambdaQueryWrapper<SetmealEntity> slqw = new LambdaQueryWrapper<>();
            slqw.orderByDesc(SetmealEntity::getCreateTime);
            return list(slqw);
        } catch (Exception e) {
            throw new FoodException(ErrorCode.SHOPPING_CART_LIST_ERROR);
        }
    }
}
