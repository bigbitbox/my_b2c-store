package com.ethercat.admin.service;

import com.ethercat.param.PageParam;
import com.ethercat.pojo.Category;
import com.ethercat.utils.R;

public interface CategoryService {

    R pageList(PageParam pageParam);

    R save(Category category);

    R remove(Integer categoryId);

    R update(Category category);
}
