package com.ljd.Food_Delivery.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
//        metaObject.setValue("createTime",LocalDateTime.now());
        this.fillStrategy(metaObject, "createUser", BaseContext.getCurrentId());
        // 插入的时候 同时更新时间
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 识别不到更新时间
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
//        metaObject.setValue("updateTime",LocalDateTime.now());
//        this.setFieldValByName("updateTime",LocalDateTime.now(),metaObject);
        // 排除方法未调用 为什么可以识别到id呢
        this.fillStrategy(metaObject, "updateUser", BaseContext.getCurrentId());
    }


}
