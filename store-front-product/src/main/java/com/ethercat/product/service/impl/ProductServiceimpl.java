package com.ethercat.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ethercat.clients.CategoryClient;
import com.ethercat.pojo.Category;
import com.ethercat.pojo.Product;
import com.ethercat.product.mapper.ProductMapper;
import com.ethercat.product.service.ProductService;
import com.ethercat.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: b2c-store
 * @description:
 * @author: Ethercat
 * @create: 2022-11-16 23:55
 **/
@Service
@Slf4j
public class ProductServiceimpl implements ProductService {

    @Autowired
    private CategoryClient categoryClient;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public R promo(String categoryName) {

        R r = categoryClient.byName(categoryName);

        if (r.getCode().equals(R.FAIL_CODE)){
            log.info("ProductServiceimpl.promo业务结束，结果：{类别查询失败}");
            return r;
        }

        Category category = (Category) r.getData();

        Integer categoryId = category.getCategoryId();

        //封装查询参数

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id",categoryId);
        queryWrapper.orderByDesc("product_sales");

        IPage<Product> page = new Page<>();

        page = productMapper.selectPage(page, queryWrapper);

        List<Product> productList = page.getRecords();

        log.info("ProductServiceimpl.promo业务结束，结果：{}",productList);

        return R.ok("数据查询成功",productList);

    }
}
