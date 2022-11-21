package com.ethercat.product.controller;

import com.ethercat.param.ProductCollectParam;
import com.ethercat.param.ProductIdParam;
import com.ethercat.pojo.Product;
import com.ethercat.product.service.ProductService;
import com.ethercat.utils.R;
import com.rabbitmq.client.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: b2c-store
 * @description: 购物车调用商品服务的controller
 * @author: Ethercat
 * @create: 2022-11-20 21:31
 **/
@RestController
@RequestMapping("product")
public class ProductCartController {

    @Autowired
    ProductService productService;


    @PostMapping("cart/detail")
    public Product cdetail(@RequestBody @Validated ProductIdParam productIdParam, BindingResult result){
        if (result.hasErrors()) {
            return null;
        }

        R detail = productService.detail(productIdParam.getProductID());

        Product product = (Product) detail.getData();

        return product;
    }

    @PostMapping("cart/list")
    public List<Product> cartList(@RequestBody @Validated ProductCollectParam productCollectParam,BindingResult result){
        if (result.hasErrors()) {
            return new ArrayList<Product>();
        }

        return productService.cartList(productCollectParam.getProductIds());
    }


}
