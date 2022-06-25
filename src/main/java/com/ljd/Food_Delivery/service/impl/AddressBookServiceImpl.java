package com.ljd.Food_Delivery.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljd.Food_Delivery.convert.AddressBookConverter;
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

    private final AddressBookConverter converter;

    public AddressBookServiceImpl(AddressBookConverter converter) {
        this.converter = converter;
    }

    @Override
    public AddressBookResponse getDefault() {
        LambdaQueryWrapper<AddressBookEntity> lqw = new LambdaQueryWrapper<>();
        //
        lqw.eq(AddressBookEntity::getIsDefault, 1);

        return converter.toAddressBookResponse(getOne(lqw));
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
        AddressBookEntity addressBookEntity = converter.toEntity(request);
        addressBookEntity.setUserId(1L);
        return save(addressBookEntity);
    }

    @Override
    public boolean update(AddressBookRequest request) {
        try {
            AddressBookEntity addressBookEntity = converter.toEntity(request);

            LambdaUpdateWrapper<AddressBookEntity> luw = new LambdaUpdateWrapper<>();
            // 找到当前用户
//            lqw.eq(AddressBookEntity::getUserId, 1);
            // 全部设置为0
            luw.set(AddressBookEntity::getIsDefault, 0);
            update(luw);

            // 设置默认地址
            addressBookEntity.setIsDefault(1);

            updateById(addressBookEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean deleteById(Long ids) {
        return removeById(ids);
    }
}
