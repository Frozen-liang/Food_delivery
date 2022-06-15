package com.ljd.Food_Delivery.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ljd.Food_Delivery.domain.entity.CategoryEntity;
import com.ljd.Food_Delivery.dto.Response;
import com.ljd.Food_Delivery.dto.request.CategoryRequest;
import com.ljd.Food_Delivery.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/page")
    // 查询
    public Response<IPage<CategoryEntity>> getByPage(int page, int pageSize){
        return Response.ok(categoryService.getByPage(page,pageSize));
    }

    @GetMapping("/list")
    // 查询
    public Response<List<CategoryEntity>> getList(CategoryRequest request){
        return Response.ok(categoryService.getList(request));
    }

    @PostMapping
    // 添加
    public Response<Boolean> save(@RequestBody CategoryRequest request){
        return Response.ok(categoryService.save(request));
    }

    // 修改
    @PutMapping
    public Response<Boolean> update(@RequestBody CategoryRequest request){
        return Response.ok(categoryService.update(request));
    }

    // 删除
    @DeleteMapping
    public Response<Boolean> delete(Long id){
        return Response.ok(categoryService.deleteById(id));
    }
}
