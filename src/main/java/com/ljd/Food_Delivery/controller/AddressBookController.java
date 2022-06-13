package com.ljd.Food_Delivery.controller;

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
    // 查询
    public Response<AddressBookResponse> getDefaultById(long id){
        return Response.ok(addressBookService.getDefaultById(id));
    }

    @GetMapping("/list")
    // 查询
    public Response<List<AddressBookResponse>> getAll(){
        return Response.ok(addressBookService.getAll());
    }

    @PostMapping
    // 添加
    public Response<Boolean> save(@RequestBody AddressBookRequest request){
        return Response.ok(addressBookService.save(request));
    }

    // 修改
    @PutMapping
    public Response<Boolean> update(@RequestBody AddressBookRequest request){
        return Response.ok(addressBookService.update(request));
    }

    // 删除
    @DeleteMapping("/delete")
    public Response<Boolean> delete(@RequestBody List<Long> ids){
        return Response.ok(addressBookService.deleteByIds(ids));
    }
}
