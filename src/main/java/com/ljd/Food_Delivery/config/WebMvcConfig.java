package com.ljd.Food_Delivery.config;

import com.ljd.Food_Delivery.intercepter.AdminInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebMvcConfig implements WebMvcConfigurer {

    private final AdminInterceptor adminInterceptor;

    public WebMvcConfig(AdminInterceptor adminInterceptor) {
        this.adminInterceptor = adminInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截器 并拦截指定目录 参数为可变参数
        registry.addInterceptor(adminInterceptor).addPathPatterns("/book/*");
    }
}
