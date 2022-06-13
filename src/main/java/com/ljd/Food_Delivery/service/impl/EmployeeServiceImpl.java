package com.ljd.Food_Delivery.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljd.Food_Delivery.convert.EmployeeConverter;
import com.ljd.Food_Delivery.domain.entity.EmployeeEntity;
import com.ljd.Food_Delivery.domain.mapper.EmployeeMapper;
import com.ljd.Food_Delivery.dto.request.EmployeeRequest;
import com.ljd.Food_Delivery.exception.ErrorCode;
import com.ljd.Food_Delivery.exception.FoodException;
import com.ljd.Food_Delivery.service.EmployeeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, EmployeeEntity> implements EmployeeService {

    // 用getBaseMapper().login()
//    private final EmployeeMapper employeeMapper;
    private final EmployeeConverter employeeConverter;

    public EmployeeServiceImpl(EmployeeConverter employeeConverter) {
        this.employeeConverter = employeeConverter;
    }

    @Override
    public EmployeeEntity getEntityById(long id) {
        return Optional.ofNullable(getById(id))
                .orElseThrow(() -> new FoodException(ErrorCode.EMPLOYEE_ID_ERROR));
    }

    @Override
    public Page<EmployeeEntity> page(int page, int pageSize, String name) {
        try {
            // 创建分页构造器
            Page<EmployeeEntity> employeeEntityPage = new Page<>(page, pageSize);
            LambdaQueryWrapper<EmployeeEntity> entityWrapper = new LambdaQueryWrapper<>();
            // 三个参数 判断是否为空 列名 查询的值
            entityWrapper.like(StringUtils.isNotEmpty(name), EmployeeEntity::getName, name);
            // 降序排序
            entityWrapper.orderByDesc(EmployeeEntity::getCreateTime);
            // 分页查询
            return page(employeeEntityPage, entityWrapper);
        } catch (Exception e) {
            throw new FoodException(ErrorCode.EMPLOYEE_PAGE_ERROR);
        }

    }

    @Override
    public boolean save(EmployeeRequest employeeRequest) {
        try {
            EmployeeEntity employeeEntity = employeeConverter.toEntity(employeeRequest);
            // MD5加密密码
            String password = DigestUtils.md5DigestAsHex(employeeEntity.getPassword().getBytes());
            employeeEntity.setPassword(password);
            // 存储
            return save(employeeEntity);
        } catch (Exception e) {
            throw new FoodException(ErrorCode.EMPLOYEE_ADD_ERROR);
        }

    }

    @Override
    public boolean update(EmployeeRequest employeeRequest) {
        try {
            // 判断用户是否存在
            getEntityById(employeeRequest.getId());
            // 密码加密
            employeeRequest.setPassword(DigestUtils.md5DigestAsHex(employeeRequest.getPassword().getBytes()));
            EmployeeEntity employeeEntity = employeeConverter.toEntity(employeeRequest);

            return saveOrUpdate(employeeEntity);
        } catch (Exception e) {
            throw new FoodException(ErrorCode.EMPLOYEE_UPDATE_ERROR);
        }
    }

    @Override
    public Boolean deleteByIds(List<Long> ids) {
        try {
            return removeByIds(ids);
        } catch (Exception e) {
            throw new FoodException(ErrorCode.EMPLOYEE_DELETE_ERROR);
        }
    }


}
