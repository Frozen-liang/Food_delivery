package com.ljd.Food_Delivery.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljd.Food_Delivery.domain.entity.OrdersEntity;

public interface OrderService extends IService<OrdersEntity> {
    // 查询
    Page<OrdersEntity> getOrderByPage(int page, int pageSize,String number);

    Page<OrdersEntity> getUserByPage(int page, int pageSize);

    boolean submit(OrdersEntity entity);
}
