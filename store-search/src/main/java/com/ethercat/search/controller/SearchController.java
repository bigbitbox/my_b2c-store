package com.ethercat.search.controller;

import com.ethercat.param.ProductSearchParam;
import com.ethercat.pojo.Product;
import com.ethercat.search.service.SearchService;
import com.ethercat.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @program: b2c-store
 * @description:
 * @author: Ethercat
 * @create: 2022-11-18 23:22
 **/

@RestController
@RequestMapping("search")
public class SearchController {
    @Autowired
    private SearchService searchService;

    @PostMapping("product")
    public R searchProduct(@RequestBody ProductSearchParam productSearchParam){
        return searchService.search(productSearchParam);
    }

    /**
     * 同步调用，进行商品插入，覆盖更新
     * @param product
     * @return
     */
    @PostMapping("save")
    public R saveProduct(@RequestBody Product product) throws IOException {
        return searchService.save(product);
    }

    @PostMapping("remove")
    public R removeProduct(@RequestBody Integer productId) throws IOException {
        return searchService.remove(productId);
    }

}
