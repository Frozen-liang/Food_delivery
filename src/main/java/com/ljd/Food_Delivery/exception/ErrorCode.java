package com.ljd.Food_Delivery.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    EMPLOYEE_LOGIN_ERROR("1", "用户名或密码错误！"),
    EMPLOYEE_ADD_ERROR("2", "创建账号失败！"),
    EMPLOYEE_DELETE_ERROR("3", "删除账号失败！"),
    EMPLOYEE_ID_ERROR("4", "ID不存在！"),
    EMPLOYEE_UPDATE_ERROR("5", "更新账号失败！"),
    EMPLOYEE_PAGE_ERROR("6", "查询失败！"),
    EMPLOYEE_FORBIDDEN_ERROR("7", "该账户被禁用！"),

    CATEGORY_ADD_ERROR("11", "添加失败！"),
    CATEGORY_DELETE_ERROR("12", "删除失败！"),
    CATEGORY_ID_ERROR("13", "ID不存在！"),
    CATEGORY_UPDATE_ERROR("14", "更新失败！"),
    CATEGORY_PAGE_ERROR("15", "查询失败！"),

    DISH_PAGE_ERROR("21", "菜品查询失败！"),
    DISH_DELETE_ERROR("22", "菜品删除失败！"),
    DISH_SAVE_ERROR("23", "菜品保存失败！"),
    DISH_UPDATE_ERROR("25", "菜品更新失败！"),
    DISH_ID_ERROR("25", "菜品ID不存在！"),

    DISH_FLAVOR_DELETE_ERROR("31", "菜品口味删除失败！"),
    DISH_FLAVOR_SAVE_ERROR("32", "菜品口味保存失败！"),
    DISH_FLAVOR_UPDATE_ERROR("33", "菜品口味更新失败！"),
    DISH_FLAVOR_ID_ERROR("34", "菜品口味ID不存在！"),

    SHOPPING_CART_ID_ERROR("41", "购物车ID不存在！"),
    SHOPPING_CART_LIST_ERROR("42", "查询购物车错误！"),
    SHOPPING_CART_SAVE_ERROR("43", "购物车存储失败！"),
    SHOPPING_CART_DELETE_ERROR("44", "购物车删除失败！"),


    FILE_UPLOAD_ERROR("101", "文件上传失败！"),
    FILE_DOWNLOAD_ERROR("102", "文件下载失败！");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
