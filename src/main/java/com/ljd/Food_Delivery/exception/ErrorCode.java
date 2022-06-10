package com.ljd.Food_Delivery.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    employee_LOGIN_ERROR("1", "用户名或密码错误！"),
    employee_ADD_ERROR("2", "创建账号失败！"),
    employee_DELETE_ERROR("3", "删除账号失败！"),
    employee_ID_ERROR("4", "ID不存在！"),
    employee_UPDATE_ERROR("5", "更新账号失败！"),
    employee_PAGE_ERROR("6", "查询失败！");
    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
