package com.ljd.Food_Delivery.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljd.Food_Delivery.domain.entity.UserEntity;
import com.ljd.Food_Delivery.domain.mapper.UserMapper;
import com.ljd.Food_Delivery.exception.ErrorCode;
import com.ljd.Food_Delivery.exception.FoodException;
import com.ljd.Food_Delivery.service.UserService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity>
        implements UserService {

    @Override
    public Page<UserEntity> getByPage(int page, int pageSize) {
        Page<UserEntity> userEntityPage = new Page<>(page, pageSize);

        LambdaQueryWrapper<UserEntity> lqw = new LambdaQueryWrapper<>();

        return page(userEntityPage, lqw);

    }

    @Override
    public boolean logout(HttpServletRequest request) {
        try {
            // 移除域属性
            request.getSession().removeAttribute("user");
            return true;
        } catch (Exception e) {
            throw new FoodException(ErrorCode.EMPLOYEE_LOGIN_ERROR);
        }
    }
}