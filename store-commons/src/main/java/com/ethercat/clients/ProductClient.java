package com.ethercat.clients;

import com.ethercat.pojo.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @program: b2c-store
 * @description: 商品服务调用客户端
 * @author: Ethercat
 * @create: 2022-11-17 22:52
 **/

@FeignClient(value = "product-service")
public interface ProductClient {


    /**
     * 搜索服务调用，进行搜索数据库同步数据
     */
    @GetMapping("/product/list")
    List<Product> allList();
}
