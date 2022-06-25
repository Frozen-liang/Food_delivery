package com.ljd.Food_Delivery.dto.response;

import com.ljd.Food_Delivery.domain.entity.SetmealDishEntity;
import com.ljd.Food_Delivery.domain.entity.SetmealEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SetMealResponse extends SetmealEntity {

    private List<SetmealDishEntity> setmealDishes;

    private String categoryName;
}
