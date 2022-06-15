package com.ljd.Food_Delivery.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljd.Food_Delivery.domain.entity.ShoppingCartEntity;
import com.ljd.Food_Delivery.dto.request.ShoppingCartRequest;

import java.util.List;

public interface ShoppingCartService extends IService<ShoppingCartEntity> {

    List<ShoppingCartEntity> getList();

    boolean add(ShoppingCartRequest request);

    ShoppingCartEntity getEntityById(Long id);
}
