package com.ethercat.product.service;

import com.ethercat.param.ProductHotParam;
import com.ethercat.utils.R;

public interface ProductService {
    R promo(String categoryName);

    /**
     * 多类别商品热门查询 根据类别名称集合！之多查询7条
     * @param productHotParam
     * @return
     */
    R hots(ProductHotParam productHotParam);
}
