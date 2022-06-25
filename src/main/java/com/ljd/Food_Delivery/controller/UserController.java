package com.ljd.Food_Delivery.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ljd.Food_Delivery.domain.entity.UserEntity;
import com.ljd.Food_Delivery.dto.Response;
import com.ljd.Food_Delivery.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/page")
    public Response<IPage<UserEntity>> getPage(int page, int pageSize) {
        return Response.ok(userService.getByPage(page,pageSize));
    }

    @PostMapping("/loginout")
    public Response<Boolean> logout(HttpServletRequest request) {
        return Response.ok(userService.logout(request));
    }
}