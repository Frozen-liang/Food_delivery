package com.ljd.Food_Delivery.controller;

import com.ljd.Food_Delivery.dto.Response;
import com.ljd.Food_Delivery.dto.request.EmployeeRequest;
import com.ljd.Food_Delivery.service.LoginService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/logins")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    // 登录
    @PostMapping("/login")
    public Response<Boolean> login(HttpServletRequest request, @RequestBody EmployeeRequest employeeRequest) {
        return Response.ok(loginService.login(request, employeeRequest));
    }

    // 退出
    @PostMapping("/logout")
    public Response<Boolean> logout(HttpServletRequest request) {
        return Response.ok(loginService.logout(request));
    }

}
