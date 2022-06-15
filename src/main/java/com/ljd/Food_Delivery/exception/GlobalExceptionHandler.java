package com.ljd.Food_Delivery.exception;

import com.ljd.Food_Delivery.dto.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FoodException.class)
    public Response doBookException(FoodException foodException) {
        return Response.error("111", "操作失败");
    }

//    @ExceptionHandler(SystemException.class)
//    public Response<String> doSystemException(SystemException e) {
//        return new Response<>("666", "系统忙！请稍后...", null);
//    }

//    @ExceptionHandler(Exception.class)
//    public Response<String> doException(Exception e) {
//        return new Response<>("888", "服务器忙！请稍后...", null);
//    }
}
