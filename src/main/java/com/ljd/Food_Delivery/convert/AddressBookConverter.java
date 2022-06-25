package com.ljd.Food_Delivery.convert;

import com.ljd.Food_Delivery.domain.entity.AddressBookEntity;
import com.ljd.Food_Delivery.dto.request.AddressBookRequest;
import com.ljd.Food_Delivery.dto.response.AddressBookResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressBookConverter {

    AddressBookEntity toEntity(AddressBookRequest request);

    AddressBookResponse toAddressBookResponse(AddressBookEntity entity);
}