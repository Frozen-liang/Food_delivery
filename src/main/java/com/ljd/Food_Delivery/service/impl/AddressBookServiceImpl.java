package com.ljd.Food_Delivery.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljd.Food_Delivery.domain.entity.AddressBookEntity;
import com.ljd.Food_Delivery.domain.mapper.AddressBookMapper;
import com.ljd.Food_Delivery.dto.request.AddressBookRequest;
import com.ljd.Food_Delivery.dto.response.AddressBookResponse;
import com.ljd.Food_Delivery.service.AddressBookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper,AddressBookEntity>
        implements AddressBookService {

    @Override
    public AddressBookResponse getDefaultById(long id) {
        try {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<AddressBookResponse> getAll() {
        try {
            return
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean save(AddressBookRequest request) {
        return false;
    }

    @Override
    public boolean update(AddressBookRequest request) {
        return false;
    }

    @Override
    public boolean deleteByIds(List<Long> ids) {
        return false;
    }
}
