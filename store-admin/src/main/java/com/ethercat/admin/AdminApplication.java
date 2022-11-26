package com.ethercat.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @program: b2c-store
 * @description:
 * @author: Ethercat
 * @create: 2022-11-26 15:47
 **/
@EnableCaching
@MapperScan(basePackages = "com.ethercat.admin.mapper")
@SpringBootApplication
public class AdminApplication {
    public static void main(String[] args){
        SpringApplication.run(AdminApplication.class,args);
    }
}
