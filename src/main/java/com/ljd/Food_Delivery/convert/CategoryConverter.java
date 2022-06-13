package com.ljd.Food_Delivery.convert;

import com.ljd.Food_Delivery.domain.entity.CategoryEntity;
import com.ljd.Food_Delivery.dto.request.CategoryRequest;
import com.ljd.Food_Delivery.dto.response.CategoryResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryConverter {

    CategoryEntity toEntity(CategoryRequest categoryRequest);

    CategoryResponse toCategoryResponse(CategoryEntity categoryEntity);
}