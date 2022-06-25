package com.ljd.Food_Delivery.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljd.Food_Delivery.domain.entity.SetmealEntity;
import com.ljd.Food_Delivery.dto.request.SetMealRequest;
import com.ljd.Food_Delivery.dto.response.SetMealResponse;

import java.util.List;

public interface SetmealService extends IService<SetmealEntity> {

    // 分页查询
    IPage<SetMealResponse> getByPage(int page, int pageSize, String name);
    // 回显
    SetMealResponse getEntityById(Long id);

    List<SetmealEntity> getList(SetMealRequest request);

    // 新增
    boolean save(SetMealRequest request);

    // 修改
    boolean update(SetMealRequest request);

    boolean updateStatus(int status, List<Long> ids);

    // 删除
    boolean deleteByIds(List<Long> ids);
}
