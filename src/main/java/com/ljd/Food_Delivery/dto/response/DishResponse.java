package com.ljd.Food_Delivery.dto.response;

import com.ljd.Food_Delivery.domain.entity.DishEntity;
import com.ljd.Food_Delivery.domain.entity.DishFlavorEntity;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishResponse extends DishEntity {

    // 数据
    private List<DishFlavorEntity> flavors;

    private String categoryName;

    private Integer copies;

}
