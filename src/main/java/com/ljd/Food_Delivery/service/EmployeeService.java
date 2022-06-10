package com.ljd.Food_Delivery.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljd.Food_Delivery.domain.entity.EmployeeEntity;
import com.ljd.Food_Delivery.dto.request.EmployeeRequest;

import javax.servlet.http.HttpServletRequest;

public interface EmployeeService extends IService<EmployeeEntity> {

    // 分页模糊查询员工
    Page<EmployeeEntity> page(int page,int pageSize,String name);

    // 添加用户
    boolean save(EmployeeRequest employeeRequest);

    // 登录
    boolean login(HttpServletRequest request, EmployeeRequest employeeRequest);

    // 退出
    boolean logout(HttpServletRequest request);

    // 编辑
    boolean update(EmployeeRequest employeeRequest);

    // 根据id查找
    EmployeeEntity getEntityById(long id);

}
