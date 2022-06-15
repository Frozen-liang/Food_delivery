package com.ljd.Food_Delivery.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljd.Food_Delivery.domain.entity.DishFlavorEntity;
import com.ljd.Food_Delivery.domain.mapper.DishFlavorMapper;
import com.ljd.Food_Delivery.service.DishFlavorService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavorEntity>
        implements DishFlavorService {
}
