package com.ethercat.search.service;

import com.ethercat.param.ProductSearchParam;
import com.ethercat.pojo.Product;
import com.ethercat.utils.R;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

/**
 * @program: b2c-store
 * @description:
 * @author: Ethercat
 * @create: 2022-11-18 23:30
 **/

public interface SearchService {
    /**
     * 根据关键字和分页进行数据库查询
     * @param productSearchParam
     * @return
     */
    R search(ProductSearchParam productSearchParam);

    /**
     * 商品同步： 插入更新
     * @param product
     * @return
     */
    R save(Product product) throws IOException;

    /**
     * 进行es库的商品删除
     * @param productId
     * @return
     */
    R remove(Integer productId) throws IOException;
}
