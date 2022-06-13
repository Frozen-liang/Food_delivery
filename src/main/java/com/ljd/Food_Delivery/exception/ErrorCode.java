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

    CATEGORY_ADD_ERROR("11", "添加菜品失败！"),
    CATEGORY_DELETE_ERROR("22", "删除菜品失败！"),
    CATEGORY_ID_ERROR("33", "菜品ID不存在！"),
    CATEGORY_UPDATE_ERROR("44", "更新菜品失败！"),
    CATEGORY_PAGE_ERROR("55", "菜品查询失败！"),

    FILE_UPLOAD_ERROR("101", "文件上传失败！"),
    FILE_DOWNLOAD_ERROR("102", "文件下载失败！");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
