package com.ljd.Food_Delivery.controller;

import com.ljd.Food_Delivery.domain.entity.ShoppingCartEntity;
import com.ljd.Food_Delivery.dto.Response;
import com.ljd.Food_Delivery.dto.request.ShoppingCartRequest;
import com.ljd.Food_Delivery.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 购物车
 */
@Slf4j
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/list")
    public Response<List<ShoppingCartEntity>> getList() {
        return Response.ok(shoppingCartService.getList());
    }

    @PostMapping("/add")
    public Response<Boolean> add(@RequestBody ShoppingCartRequest request) {
        return Response.ok(shoppingCartService.add(request));
    }
}