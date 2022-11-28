package com.ethercat.admin.service.impl;

import com.ethercat.admin.service.ProductService;
import com.ethercat.clients.ProductClient;
import com.ethercat.clients.SearchClient;
import com.ethercat.param.ProductSaveParam;
import com.ethercat.param.ProductSearchParam;
import com.ethercat.pojo.Product;
import com.ethercat.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: b2c-store
 * @description:
 * @author: Ethercat
 * @create: 2022-11-28 00:13
 **/

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private SearchClient searchClient;

    @Autowired
    private ProductClient productClient;


    @Override
    public R search(ProductSearchParam productSearchParam) {
        R search = searchClient.search(productSearchParam);
        log.info("ProductServiceImpl.search业务结束，结果：{}",search);

        return search;
    }

    @Override
    public R save(ProductSaveParam productSaveParam) {
        R r = productClient.adminSave(productSaveParam);
        log.info("ProductServiceImpl.save业务结束，结果：{}",r);
        return r;
    }

    @Override
    public R update(Product product) {
        R r = productClient.adminUpdate(product);
        log.info("ProductServiceImpl.update业务结束，结果：{}",r);
        return r;
    }
}
