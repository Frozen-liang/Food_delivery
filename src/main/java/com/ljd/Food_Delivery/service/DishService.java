package com.ljd.Food_Delivery.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljd.Food_Delivery.domain.entity.DishEntity;
import com.ljd.Food_Delivery.dto.request.DishRequest;
import com.ljd.Food_Delivery.dto.response.DishResponse;

import java.util.List;

public interface DishService extends IService<DishEntity> {
    // 分页查询
    IPage<DishResponse> getByPage(int page, int pageSize, String name);

    List<DishResponse> getList(DishRequest request);

    DishResponse getEntityById(Long id);

    // 新增
    boolean save(DishRequest request);

    // 修改
    boolean update(DishRequest request);

    boolean updateStatus(int status, List<Long> ids);


    // 删除
    boolean deleteByIds(List<Long> ids);
}
