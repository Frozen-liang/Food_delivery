package com.ljd.Food_Delivery.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljd.Food_Delivery.domain.entity.OrderDetailEntity;
import com.ljd.Food_Delivery.domain.mapper.OrderDetailMapper;
import com.ljd.Food_Delivery.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetailEntity> implements OrderDetailService {
}
