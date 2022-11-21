package com.ethercat.cart;
import com.ethercat.clients.ProductClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @program: b2c-store
 * @description:
 * @author: Ethercat
 * @create: 2022-11-20 20:29
 **/

@MapperScan(basePackages = "com.ethercat.cart.mapper")
@SpringBootApplication
@EnableFeignClients(clients = {ProductClient.class})
public class CartApplication {

    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class,args);
    }
}