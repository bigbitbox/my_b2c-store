package com.ethercat.category.service;

import com.ethercat.param.ProductHotParam;
import com.ethercat.utils.R;

public interface CategoryService {

    R byName(String categoryName);

    /**
     * 根据传入的热门类别名称集合返回类别对应的id集合
     * @param productHotParam
     * @return
     */
    R hotsCategory(ProductHotParam productHotParam);
}
