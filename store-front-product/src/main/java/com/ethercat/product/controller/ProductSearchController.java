package com.ethercat.product.controller;

import com.ethercat.pojo.Product;
import com.ethercat.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: b2c-store
 * @description: 搜索服务调用的controller
 * @author: Ethercat
 * @create: 2022-11-17 22:48
 **/


@RestController
@RequestMapping("product")
public class ProductSearchController {

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public List<Product> allList(){

        return productService.allList();
    }
}
