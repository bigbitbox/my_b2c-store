package com.ethercat.category.service;

import com.ethercat.param.PageParam;
import com.ethercat.param.ProductHotParam;
import com.ethercat.pojo.Category;
import com.ethercat.utils.R;

public interface CategoryService {

    R byName(String categoryName);

    /**
     * 根据传入的热门类别名称集合返回类别对应的id集合
     * @param productHotParam
     * @return
     */
    R hotsCategory(ProductHotParam productHotParam);

    R list();

    /**f
     * 分页查询
     * @param pageParam
     * @return
     */
    R listPage(PageParam pageParam);

    /**添加类别
     * 如果类别名称存在则不添加
     * @param category
     * @return
     */

    R adminSave(Category category);


    R adminRemove(Integer categoryId);
}
