package com.ljd.Food_Delivery.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ljd.Food_Delivery.domain.entity.DishEntity;
import com.ljd.Food_Delivery.dto.Response;
import com.ljd.Food_Delivery.dto.request.DishRequest;
import com.ljd.Food_Delivery.service.DishService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dish")
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }


    @GetMapping("/page")
    // 查询
    public Response<IPage<DishEntity>> getByPage(int page, int pageSize, String name) {
        return Response.ok(dishService.getByPage(page, pageSize, name));
    }

    @GetMapping("/list")
    public Response<List<DishEntity>> getList() {
        return Response.ok(dishService.getList());
    }

    @PostMapping
    // 添加
    public Response<Boolean> save(@RequestBody DishRequest request) {
        return Response.ok(dishService.save(request));
    }

    // 修改
    @PutMapping
    public Response<Boolean> update(@RequestBody DishRequest request) {
        return Response.ok(dishService.update(request));
    }

    // 删除
    @DeleteMapping("/delete")
    public Response<Boolean> delete(List<Long> ids) {
        return Response.ok(dishService.deleteByIds(ids));
    }
}
