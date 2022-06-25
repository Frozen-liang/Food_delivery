package com.ljd.Food_Delivery.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ljd.Food_Delivery.domain.entity.OrdersEntity;
import com.ljd.Food_Delivery.dto.Response;
import com.ljd.Food_Delivery.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/userPage")
    public Response<IPage<OrdersEntity>> getByPage(int page, int pageSize) {
        return Response.ok(orderService.getUserByPage(page, pageSize));
    }

    @GetMapping("/page")
    public Response<IPage<OrdersEntity>> getByPage(int page, int pageSize, String number) {
        return Response.ok(orderService.getOrderByPage(page, pageSize, number));
    }

    @PostMapping("/submit")
    public Response<Boolean> submit(@RequestBody OrdersEntity entity){
        return Response.ok(orderService.submit(entity));
    }
}