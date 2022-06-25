package com.ljd.Food_Delivery.dto.request;

import com.ljd.Food_Delivery.domain.entity.DishEntity;
import com.ljd.Food_Delivery.domain.entity.DishFlavorEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class DishRequest extends DishEntity {

    // 数据
    private List<DishFlavorEntity> flavors;

    private String categoryName;

    private Integer copies;
}
