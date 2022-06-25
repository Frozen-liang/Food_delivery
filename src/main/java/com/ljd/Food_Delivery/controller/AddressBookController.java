package com.ljd.Food_Delivery.controller;

import com.ljd.Food_Delivery.domain.entity.AddressBookEntity;
import com.ljd.Food_Delivery.dto.Response;
import com.ljd.Food_Delivery.dto.request.AddressBookRequest;
import com.ljd.Food_Delivery.dto.response.AddressBookResponse;
import com.ljd.Food_Delivery.service.AddressBookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addressBook")
public class AddressBookController {

    private final AddressBookService addressBookService;

    public AddressBookController(AddressBookService addressBookService) {
        this.addressBookService = addressBookService;
    }

    @GetMapping("/default")
    // 下单查询默认地址
    public Response<AddressBookResponse> getDefault() {
        return Response.ok(addressBookService.getDefault());
    }

    @GetMapping("/{id}")
    // 修改时查询
    public Response<AddressBookEntity> getById(@PathVariable Long id) {
        return Response.ok(addressBookService.getById(id));
    }

    @GetMapping("/list")
    // 查询地址表单
    public Response<List<AddressBookEntity>> getAll() {
        return Response.ok(addressBookService.getAll());
    }

    @PostMapping
    // 添加地址
    public Response<Boolean> save(@RequestBody AddressBookRequest request) {
        return Response.ok(addressBookService.save(request));
    }

    // 设置默认地址
    @PutMapping("/default")
    public Response<Boolean> setDefault(@RequestBody AddressBookRequest request) {
        return Response.ok(addressBookService.update(request));
    }

//    // 修改
//    @PutMapping
//    public Response<Boolean> update(@RequestBody AddressBookRequest request) {
//        return Response.ok(addressBookService.update(request));
//    }

    // 删除地址
    @DeleteMapping
    public Response<Boolean> delete(Long ids) {
        return Response.ok(addressBookService.deleteById(ids));
    }
}
