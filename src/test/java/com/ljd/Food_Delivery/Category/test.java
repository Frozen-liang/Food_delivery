package com.ljd.Food_Delivery.Category;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ljd.Food_Delivery.domain.entity.DishEntity;
import com.ljd.Food_Delivery.service.CategoryService;
import org.junit.jupiter.api.Test;

public class test {

    public final CategoryService service;

    public test(CategoryService service) {
        this.service = service;
    }

    @Test
    public void getAddType() {
        service.getAddType();
    }

    @Test
    public void page(){
        Long id = 1L;
        LambdaQueryWrapper<DishEntity> delqw = new LambdaQueryWrapper<>();
        delqw.eq(DishEntity::getCategoryId,id);
        System.out.println(delqw);
    }

}
