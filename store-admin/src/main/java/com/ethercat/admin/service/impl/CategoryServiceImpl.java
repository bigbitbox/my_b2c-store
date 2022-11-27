package com.ethercat.admin.service.impl;

import com.ethercat.admin.service.CategoryService;
import com.ethercat.clients.CategoryClient;
import com.ethercat.param.PageParam;
import com.ethercat.pojo.Category;
import com.ethercat.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @program: b2c-store
 * @description:
 * @author: Ethercat
 * @create: 2022-11-27 20:22
 **/

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryClient categoryClient;

    @Cacheable(value = "list.category" , key = "#pageParam.currentPage"+"-"+"#pageParam.pageSize")
    @Override
    public R pageList(PageParam pageParam) {

        R r = categoryClient.adminList(pageParam);
        log.info("CategoryServiceImpl.pageList业务结束，结果：{}",r);
        return r;
    }

    @CacheEvict(value = "list.category", allEntries = true)
    @Override
    public R save(Category category) {
        R r = categoryClient.adminSave(category);
        log.info("CategoryServiceImpl.save业务结束，结果：{}",r);
        return r;
    }

    @CacheEvict(value = "list.category", allEntries = true)
    @Override
    public R remove(Integer categoryId) {
        R r = categoryClient.adminRemove(categoryId);

        log.info("CategoryServiceImpl.remove业务结束，结果：{}",r);

        return r;
    }

    @CacheEvict(value = "list.category", allEntries = true)
    @Override
    public R update(Category category) {
        R r = categoryClient.adminUpdate(category);
        log.info("CategoryServiceImpl.update业务结束，结果：{}",r);
        return r;
    }
}
