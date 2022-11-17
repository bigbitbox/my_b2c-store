package com.ethercat.product.controller;

import com.ethercat.param.ProductHotParam;
import com.ethercat.param.ProductIdsParam;
import com.ethercat.param.ProductPromoParam;
import com.ethercat.product.service.ProductService;
import com.ethercat.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @program: b2c-store
 * @description:
 * @author: Ethercat
 * @create: 2022-11-16 23:35
 **/

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/promo")
    public R promo(@RequestBody @Validated ProductPromoParam productPromoParam, BindingResult result){
        if (result.hasErrors()){
            return  R.fail("数据查询失败！");
        }
        return productService.promo(productPromoParam.getCategoryName());
    }

    @PostMapping("hots")
    public R hots(@RequestBody @Validated ProductHotParam productHotParam, BindingResult result){
        if (result.hasErrors()){
            return  R.fail("数据查询失败！");
        }
        return productService.hots(productHotParam);
    }

    @PostMapping("category/list")
    public R clist(){

        return productService.clist();
    }

    @PostMapping("bycategory")
    public R byCategory(@RequestBody @Validated ProductIdsParam productIdsParam,BindingResult result){

        if (result.hasErrors()){
            return R.fail("类别商品查询失败！");
        }

        return productService.byCategory(productIdsParam);
    }

    @PostMapping("all")
    public R all(@RequestBody @Validated ProductIdsParam productIdsParam,BindingResult result){

        if (result.hasErrors()){
            return R.fail("类别商品查询失败！");
        }

        return productService.byCategory(productIdsParam);
    }

}
