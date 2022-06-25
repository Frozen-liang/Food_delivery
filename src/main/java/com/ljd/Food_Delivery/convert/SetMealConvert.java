package com.ljd.Food_Delivery.convert;

import com.ljd.Food_Delivery.domain.entity.SetmealEntity;
import com.ljd.Food_Delivery.dto.request.SetMealRequest;
import com.ljd.Food_Delivery.dto.response.SetMealResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SetMealConvert {

    SetmealEntity toEntity(SetMealRequest request);

    SetMealResponse toSetMealResponse(SetmealEntity entity);
}
