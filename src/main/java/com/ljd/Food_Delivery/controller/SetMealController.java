package com.ljd.Food_Delivery.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ljd.Food_Delivery.domain.entity.SetmealEntity;
import com.ljd.Food_Delivery.dto.Response;
import com.ljd.Food_Delivery.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 购物车
 */
@Slf4j
@RestController
@RequestMapping("/setmeal")
public class SetMealController {

    private final SetmealService service;

    public SetMealController(SetmealService service) {
        this.service = service;
    }

    @GetMapping("/page")
    public Response<IPage<SetmealEntity>> getByPage(int page, int pageSize, String name) {
        return Response.ok(service.getByPage(page, pageSize, name));
    }

    @GetMapping("/list")
    public Response<List<SetmealEntity>> getList() {
        return Response.ok(service.getList());
    }
}