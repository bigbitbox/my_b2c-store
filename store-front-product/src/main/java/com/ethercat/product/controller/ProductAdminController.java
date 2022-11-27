package com.ethercat.product.controller;

import com.ethercat.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: b2c-store
 * @description:
 * @author: Ethercat
 * @create: 2022-11-27 22:33
 **/


@RestController
@RequestMapping("product")
public class ProductAdminController {

    @Autowired
    private ProductService productService;

    @PostMapping("/admin/count")
    public long adminCount(@RequestBody Integer categoryId){
        return productService.adminCount(categoryId);
    }
}
