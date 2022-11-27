package com.ethercat.admin;

import com.ethercat.clients.UserClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @program: b2c-store
 * @description:
 * @author: Ethercat
 * @create: 2022-11-26 15:47
 **/
@EnableCaching
@MapperScan(basePackages = "com.ethercat.admin.mapper")
@SpringBootApplication
@EnableFeignClients(clients = {UserClient.class})
public class AdminApplication {
    public static void main(String[] args){
        SpringApplication.run(AdminApplication.class,args);
    }
}
