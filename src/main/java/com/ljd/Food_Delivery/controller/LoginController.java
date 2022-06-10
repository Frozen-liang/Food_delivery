package com.ljd.Food_Delivery.controller;

import com.ljd.Food_Delivery.dto.Response;
import com.ljd.Food_Delivery.dto.request.EmployeeRequest;
import com.ljd.Food_Delivery.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/logins")
public class LoginController {

    private final EmployeeService employeeService;

    public LoginController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // 分页查询
    @GetMapping("/page")
    public Response<?> page(int page,int pageSize,String name){
        return Response.ok(employeeService.page(page,pageSize,name));
    }

    // 添加
    @PostMapping()
    public Response<Boolean> save(@RequestBody EmployeeRequest employeeRequest) {
        return Response.ok(employeeService.save(employeeRequest));
    }

    // 编辑
    @PutMapping
    public Response<Boolean> update(@RequestBody EmployeeRequest employeeRequest){
        return Response.ok(employeeService.update(employeeRequest));
    }

    // 登录
    @PostMapping("/login")
    public Response<Boolean> login(HttpServletRequest request,@RequestBody EmployeeRequest employeeRequest) {
        return Response.ok(employeeService.login(request,employeeRequest));
    }

    // 退出
    @PostMapping("/logout")
    public Response<Boolean> logout(HttpServletRequest request){
        return Response.ok(employeeService.logout(request));
    }

}
