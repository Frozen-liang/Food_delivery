package com.ljd.Food_Delivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
// Redis缓存开启
@EnableCaching
// 扫描mapper
//@MapperScan({"com.ljd.Food_Delivery.domain.mapper"})
//@EnableJpaAuditing
public class FoodDeliveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodDeliveryApplication.class, args);
    }

}
