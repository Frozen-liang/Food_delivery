package com.ljd.Food_Delivery.dto.request;

import com.ljd.Food_Delivery.domain.entity.DishFlavorEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DishRequest {

    private Long id;

    private String name;

    private Long categoryId;

    private List<DishFlavorEntity> flavors;

    private String categoryName;

    private BigDecimal price;

    private String code;

    private String image;

    private String description;

    private int status;

    private int sort;

    private int isDeleted;

}
