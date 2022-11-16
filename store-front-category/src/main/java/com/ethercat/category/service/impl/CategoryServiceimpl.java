package com.ethercat.category.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ethercat.category.mapper.CategoryMapper;
import com.ethercat.category.service.CategoryService;
import com.ethercat.pojo.Category;
import com.ethercat.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: b2c-store
 * @description:
 * @author: Ethercat
 * @create: 2022-11-16 20:00
 **/
@Service
@Slf4j
public class CategoryServiceimpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public R byName(String categoryName) {
        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.eq("category_name",categoryName);
        Category category = categoryMapper.selectOne(categoryQueryWrapper);
        if(category == null){
            log.info("CategoryServiceimpl.byName业务结束，结果：{类别查询失败}");
            return R.fail("类别查询失败");
        }
        log.info("CategoryServiceimpl.byName业务结束，结果：{类别查询成功}");
        return R.ok("类别查询成功",category);
    }
}
