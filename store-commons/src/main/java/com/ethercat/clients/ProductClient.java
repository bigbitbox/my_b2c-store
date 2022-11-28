package com.ethercat.clients;

import com.ethercat.param.ProductCollectParam;
import com.ethercat.param.ProductIdParam;
import com.ethercat.param.ProductSaveParam;
import com.ethercat.pojo.Product;
import com.ethercat.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    @PostMapping("/product/collect/list")
    R productIds(@RequestBody ProductCollectParam productCollectParam);

    @PostMapping("/product/cart/detail")
    Product productDetail(@RequestBody ProductIdParam productIdParam);

    @PostMapping("/product/cart/list")
    List<Product> cartList(@RequestBody ProductCollectParam productCollectParam);

    @PostMapping("/product/admin/count")
    Long adminCount(@RequestBody Integer categoryId);

    @PostMapping("/product/admin/save")
    R adminSave(@RequestBody ProductSaveParam productSaveParam);

    @PostMapping("/product/admin/update")
    R adminUpdate(@RequestBody Product product);
}
