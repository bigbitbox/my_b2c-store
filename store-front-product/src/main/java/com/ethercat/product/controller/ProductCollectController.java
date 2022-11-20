package com.ethercat.product.controller;

import com.ethercat.param.ProductCollectParam;
import com.ethercat.product.service.ProductService;
import com.ethercat.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: b2c-store
 * @description: 商品被收藏调用的controller
 * @author: Ethercat
 * @create: 2022-11-20 13:10
 **/

@RestController
@RequestMapping("product")
public class ProductCollectController {
    @Autowired
    ProductService productService;

    @PostMapping("collect/list")
    public R productIds(@RequestBody @Validated ProductCollectParam productCollectParam, BindingResult result){

        if (result.hasErrors()) {
            return R.ok("没有收藏数据！");
        }

        return productService.ids(productCollectParam.getProductIds());
    }
}
