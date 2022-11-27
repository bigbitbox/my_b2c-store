package com.ethercat.admin.controller;

import com.ethercat.admin.service.ProductService;
import com.ethercat.param.ProductSearchParam;
import com.ethercat.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: b2c-store
 * @description:
 * @author: Ethercat
 * @create: 2022-11-28 00:09
 **/


@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("list")
    public R adminList(ProductSearchParam productSearchParam){
        return productService.search(productSearchParam);

    }
}
