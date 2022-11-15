package com.ethercat.statics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: b2c-store
 * @description: 静态资源的服务启动类
 * @author: Ethercat
 * @create: 2022-11-15 12:05
 **/

@SpringBootApplication
public class StaticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(StaticsApplication.class, args);
    }
}
