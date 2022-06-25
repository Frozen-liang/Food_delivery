package com.ljd.Food_Delivery.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ljd.Food_Delivery.dto.Response;
import com.ljd.Food_Delivery.dto.request.DishRequest;
import com.ljd.Food_Delivery.dto.response.DishResponse;
import com.ljd.Food_Delivery.service.DishService;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dish")
public class DishController {

    // 用来观察缓存过程
    private final CacheManager cacheManager;

    private final DishService dishService;

    public DishController(CacheManager cacheManager, DishService dishService) {
        this.cacheManager = cacheManager;
        this.dishService = dishService;
    }

//    // 缓存名字为 Dish 键为id 缓存条件为结果不为空
//    @Cacheable(value = "Dish", key = "#id")
    // 根据id回显
    @GetMapping("/{id}")
    public Response<DishResponse> getEntityById(@PathVariable long id) {
        return Response.ok(dishService.getEntityById(id));
    }

    @GetMapping("/page")
    // 后台菜品分页展示
    public Response<IPage<DishResponse>> getByPage(int page, int pageSize, String name) {
        return Response.ok(dishService.getByPage(page, pageSize, name));
    }

    // 添加缓存
    @Cacheable(value = "Dish", key = "#request.categoryId")
    // 前端显示菜品
    @GetMapping("/list")
    public Response<List<DishResponse>> getList(DishRequest request) {
        return Response.ok(dishService.getList(request));
    }

    // 保存菜品时删除缓存
    @CacheEvict(value = "Dish",key = "#request.categoryId")
    @PostMapping
    // 添加菜品
    public Response<Boolean> save(@RequestBody DishRequest request) {
        return Response.ok(dishService.save(request));
    }

    // 修改时删除缓存
    @CacheEvict(value = "Dish",key = "#request.categoryId")
    // 修改菜品信息
    @PutMapping
    public Response<Boolean> update(@RequestBody DishRequest request) {
        return Response.ok(dishService.update(request));
    }

    // 修改时删除缓存
    @CacheEvict(value = "Dish",key = "#ids")
    // 修改菜品状态信息
    @PostMapping("/status/{status}")
    public Response<Boolean> updateStatus(@PathVariable int status, @RequestParam List<Long> ids) {
        return Response.ok(dishService.updateStatus(status, ids));
    }

    // 修改时删除缓存
    @CacheEvict(value = "Dish",key = "#ids")
    // 删除
    @DeleteMapping
    public Response<Boolean> delete(@RequestParam List<Long> ids) {
        return Response.ok(dishService.deleteByIds(ids));
    }
}
