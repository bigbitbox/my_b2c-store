package com.ethercat.order;

import com.ethercat.clients.ProductClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @program: b2c-store
 * @description:
 * @author: Ethercat
 * @create: 2022-11-22 11:42
 **/


@SpringBootApplication
@MapperScan(basePackages = "com.ethercat.order.mapper")
@EnableFeignClients(clients = {ProductClient.class})
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }
}
