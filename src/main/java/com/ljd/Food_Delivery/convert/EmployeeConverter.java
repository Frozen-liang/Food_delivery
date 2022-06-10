package com.ljd.Food_Delivery.convert;

import com.ljd.Food_Delivery.domain.entity.EmployeeEntity;
import com.ljd.Food_Delivery.dto.request.EmployeeRequest;
import com.ljd.Food_Delivery.dto.response.EmployeeResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeConverter {

    EmployeeEntity toEntity(EmployeeRequest employeeRequest);

    EmployeeResponse toBookResponse(EmployeeEntity employeeEntity);

}
