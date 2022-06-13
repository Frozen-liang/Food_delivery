package com.ljd.Food_Delivery.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljd.Food_Delivery.domain.entity.EmployeeEntity;
import com.ljd.Food_Delivery.dto.request.EmployeeRequest;
import com.ljd.Food_Delivery.dto.response.EmployeeResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface EmployeeMapper extends BaseMapper<EmployeeEntity> {

    EmployeeResponse login(@Param("employeeRequest") EmployeeRequest employeeRequest);

}
