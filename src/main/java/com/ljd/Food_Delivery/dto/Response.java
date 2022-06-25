package com.ljd.Food_Delivery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> implements Serializable { // 表示这个类型可以被序列化 缓存到Redis

    private String code;
    private String msg;
    // 数据为任何的类型 集合 对象等
    private T data;

    public static ResponseBuilder ok(){
        return Response.builder().code("200").msg("success");
    }

    // 方法嵌套调用 调用上面的
    public static <T> Response<T> ok(T data){
        return ok().data(data).build();
    }

    public static Response error(String code, String message) {
        return ok().code(code).msg(message).build();
    }
}
