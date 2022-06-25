package com.ljd.Food_Delivery.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljd.Food_Delivery.domain.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;

public interface UserService extends IService<UserEntity> {
    // 查询
    Page<UserEntity> getByPage(int page, int pageSize);

    // 用户退出
    boolean logout(HttpServletRequest request);
}
