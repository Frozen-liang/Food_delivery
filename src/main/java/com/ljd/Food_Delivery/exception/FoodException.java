package com.ljd.Food_Delivery.exception;

public class FoodException extends RuntimeException {
    //??
    private static final long serialVersionUID = 1L;

    private final String code;

    public FoodException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public FoodException(ErrorCode errorCode, Throwable e) {
        super(errorCode.getMessage(), e);
        this.code = errorCode.getCode();
    }

    public String getCode() {
        return code;
    }
}
