package com.ljd.Food_Delivery.convert;

import com.ljd.Food_Delivery.domain.entity.ShoppingCartEntity;
import com.ljd.Food_Delivery.dto.request.ShoppingCartRequest;
import com.ljd.Food_Delivery.dto.response.ShoppingCartResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShoppingCarConverter {

    ShoppingCartEntity toEntity(ShoppingCartRequest request);

    ShoppingCartResponse toShoppingCartResponse(ShoppingCartEntity shoppingCartEntity);
}