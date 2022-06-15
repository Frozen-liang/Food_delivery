package com.ljd.Food_Delivery.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljd.Food_Delivery.domain.entity.AddressBookEntity;
import com.ljd.Food_Delivery.domain.mapper.AddressBookMapper;
import com.ljd.Food_Delivery.dto.request.AddressBookRequest;
import com.ljd.Food_Delivery.dto.response.AddressBookResponse;
import com.ljd.Food_Delivery.exception.ErrorCode;
import com.ljd.Food_Delivery.exception.FoodException;
import com.ljd.Food_Delivery.service.AddressBookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBookEntity>
        implements AddressBookService {

    @Override
    public AddressBookResponse getDefaultById(long id) {
        try {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<AddressBookEntity> getAll() {
        try {
            LambdaQueryWrapper<AddressBookEntity> lqm = new LambdaQueryWrapper<>();
            lqm.orderByDesc(AddressBookEntity::getUpdateTime);
            return list(lqm);
        } catch (Exception e) {
            throw new FoodException(ErrorCode.CATEGORY_PAGE_ERROR);
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
    public boolean deleteById(long id) {
        return false;
    }
}
