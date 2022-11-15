package com.ethercat.carousel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: b2c-store
 * @description:
 * @author: Ethercat
 * @create: 2022-11-15 20:51
 **/

@MapperScan(basePackages = "com.ethercat.carousel.mapper")
@SpringBootApplication
public class CarouselApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarouselApplication.class,args);
    }
}
