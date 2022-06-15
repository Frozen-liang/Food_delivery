package com.ljd.Food_Delivery.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ljd.Food_Delivery.common.validate.InsertGroup;
import com.ljd.Food_Delivery.domain.entity.EmployeeEntity;
import com.ljd.Food_Delivery.dto.Response;
import com.ljd.Food_Delivery.dto.request.EmployeeRequest;
import com.ljd.Food_Delivery.service.EmployeeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // 分页查询 page 当前页码数 pageSize 页面显示条数 name 模糊查询
    @GetMapping("/page")
    public Response<IPage<EmployeeEntity>> page(int page, int pageSize, String name) {
        return Response.ok(employeeService.page(page, pageSize, name));
    }

    @GetMapping("/{id}")
    public Response<EmployeeEntity> getEntityById(@PathVariable long id) {
        return Response.ok(employeeService.getEntityById(id));
    }


    // 添加
    @PostMapping()
    public Response<Boolean> save(@Validated(InsertGroup.class) @RequestBody EmployeeRequest employeeRequest) {
        return Response.ok(employeeService.save(employeeRequest));
    }

    // 编辑
    @PutMapping
    public Response<Boolean> update(@RequestBody EmployeeRequest employeeRequest) {
        return Response.ok(employeeService.update(employeeRequest));
    }

    // 删除
    // 删除
    @DeleteMapping("/delete")
    public Response<Boolean> delete(@RequestBody List<Long> ids){
        return Response.ok(employeeService.deleteByIds(ids));
    }
}
