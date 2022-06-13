package com.ljd.Food_Delivery.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljd.Food_Delivery.domain.entity.EmployeeEntity;
import com.ljd.Food_Delivery.dto.request.EmployeeRequest;

import javax.servlet.http.HttpServletRequest;

public interface LoginService extends IService<EmployeeEntity> {
    // 登录
    boolean login(HttpServletRequest request, EmployeeRequest employeeRequest);

    // 退出
    boolean logout(HttpServletRequest request);

}
