package com.ljd.Food_Delivery.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljd.Food_Delivery.domain.entity.AddressBookEntity;
import com.ljd.Food_Delivery.dto.request.AddressBookRequest;
import com.ljd.Food_Delivery.dto.response.AddressBookResponse;

import java.util.List;

public interface AddressBookService extends IService<AddressBookEntity> {
    // 查询
    AddressBookResponse getDefault();

    List<AddressBookEntity> getAll();

    // 新增
    boolean save(AddressBookRequest request);

    // 修改默认地址
    boolean update(AddressBookRequest request);

    // 删除
    boolean deleteById(Long ids);
}
