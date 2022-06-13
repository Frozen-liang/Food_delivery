package com.ljd.Food_Delivery.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljd.Food_Delivery.domain.entity.EmployeeEntity;
import com.ljd.Food_Delivery.domain.mapper.EmployeeMapper;
import com.ljd.Food_Delivery.dto.request.EmployeeRequest;
import com.ljd.Food_Delivery.dto.response.EmployeeResponse;
import com.ljd.Food_Delivery.exception.ErrorCode;
import com.ljd.Food_Delivery.exception.FoodException;
import com.ljd.Food_Delivery.service.LoginService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;

@Service
public class LoginServiceImpl extends ServiceImpl<EmployeeMapper, EmployeeEntity>
        implements LoginService {
    @Override
    public boolean login(HttpServletRequest request, EmployeeRequest employeeRequest) {

//        EmployeeResponse employeeResponse = baseMapper.login(employeeRequest);
        try {
            employeeRequest.setPassword(DigestUtils.md5DigestAsHex(employeeRequest.getPassword().getBytes()));
            EmployeeResponse employeeResponse = getBaseMapper().login(employeeRequest);
            if (employeeResponse == null) {
                throw new FoodException(ErrorCode.EMPLOYEE_LOGIN_ERROR);
            } else if (employeeResponse.getStatus() == 0) {
                throw new FoodException(ErrorCode.EMPLOYEE_FORBIDDEN_ERROR);
            }
            // 存放域数据 保存当前的id
            request.getSession().setAttribute("employee", employeeResponse.getId());
            return true;
        } catch (Exception e) {
            throw new FoodException(ErrorCode.EMPLOYEE_LOGIN_ERROR);
        }
    }

    @Override
    public boolean logout(HttpServletRequest request) {
        try {
            // 移除域属性
            request.getSession().removeAttribute("employee");
            return true;
        } catch (Exception e) {
            throw new FoodException(ErrorCode.EMPLOYEE_LOGIN_ERROR);
        }
    }

}
