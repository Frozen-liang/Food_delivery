package com.ljd.Food_Delivery.convert;

import com.ljd.Food_Delivery.domain.entity.DishEntity;
import com.ljd.Food_Delivery.dto.request.DishRequest;
import com.ljd.Food_Delivery.dto.response.DishResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DishConvert {

    DishEntity toEntity(DishRequest dishRequest);

    DishResponse toBookResponse(DishEntity dishEntity);
}
