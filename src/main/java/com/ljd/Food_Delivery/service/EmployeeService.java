package com.ljd.Food_Delivery.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljd.Food_Delivery.domain.entity.EmployeeEntity;
import com.ljd.Food_Delivery.dto.request.EmployeeRequest;

import java.util.List;

public interface EmployeeService extends IService<EmployeeEntity> {

    // 根据id查找
    EmployeeEntity getEntityById(long id);

    // 分页模糊查询员工
    Page<EmployeeEntity> page(int page,int pageSize,String name);

    // 添加用户
    boolean save(EmployeeRequest employeeRequest);

    // 编辑
    boolean update(EmployeeRequest employeeRequest);

    // 删除员工
    Boolean deleteByIds(List<Long> ids);

}
