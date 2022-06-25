package com.ljd.Food_Delivery.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ljd.Food_Delivery.domain.entity.SetmealEntity;
import com.ljd.Food_Delivery.dto.Response;
import com.ljd.Food_Delivery.dto.request.SetMealRequest;
import com.ljd.Food_Delivery.dto.response.SetMealResponse;
import com.ljd.Food_Delivery.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public Response<SetMealResponse> getEntityById(@PathVariable long id) {
        return Response.ok(service.getEntityById(id));
    }

    @GetMapping("/page")
    public Response<IPage<SetMealResponse>> getByPage(int page, int pageSize, String name) {
        return Response.ok(service.getByPage(page, pageSize, name));
    }

    @GetMapping("/list")
    public Response<List<SetmealEntity>> getList(SetMealRequest request) {
        return Response.ok(service.getList(request));
    }

    @PostMapping
    // 添加
    public Response<Boolean> save(@RequestBody SetMealRequest request) {
        return Response.ok(service.save(request));
    }

    // 修改菜品信息
    @PutMapping
    public Response<Boolean> update(@RequestBody SetMealRequest request) {
        return Response.ok(service.update(request));
    }

    // 修改菜品状态信息
    @PostMapping("/status/{status}")
    public Response<Boolean> updateStatus(@PathVariable int status, @RequestParam List<Long> ids) {
        return Response.ok(service.updateStatus(status, ids));
    }

    // 删除
    @DeleteMapping
    public Response<Boolean> delete(@RequestParam List<Long> ids) {
        return Response.ok(service.deleteByIds(ids));
    }

}