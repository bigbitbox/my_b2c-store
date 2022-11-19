package com.ethercat.search.service;

import com.ethercat.param.ProductSearchParam;
import com.ethercat.utils.R;

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
}
