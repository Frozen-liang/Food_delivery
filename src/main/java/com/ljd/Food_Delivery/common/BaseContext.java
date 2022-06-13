package com.ljd.Food_Delivery.common;

public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    // 获取当前线程用户id
    public static Long getCurrentId() {
        return threadLocal.get();
    }

    // 设置当前线程用户id
    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }
}