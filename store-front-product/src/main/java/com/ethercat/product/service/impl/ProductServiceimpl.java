package com.ethercat.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ethercat.clients.CategoryClient;
import com.ethercat.param.ProductHotParam;
import com.ethercat.pojo.Product;
import com.ethercat.product.mapper.ProductMapper;
import com.ethercat.product.service.ProductService;
import com.ethercat.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
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
    public R hots(ProductHotParam productHotParam) {
        //1.调用类别
        R r = categoryClient.hots(productHotParam);

        if ( r.getCode().equals(R.FAIL_CODE) ){
            log.info("ProductServiceimpl.hots业务结束，结果：{}",r.getMsg());
            return r;
        }

        List<Object> ids = (List<Object>) r.getData();

        QueryWrapper<Product> queryWrapper  = new QueryWrapper<>();
        queryWrapper.in("category_id",ids);
        queryWrapper.orderByDesc("product_sales");

        IPage<Product> page = new Page<>(1,7);

        page = productMapper.selectPage(page,queryWrapper);

        List<Product> records = page.getRecords();

        R ok = R.ok("多类别热门商品查询成功", records);

        log.info("ProductServiceimpl.hots业务结束，结果：{}",ok);

        return ok;
    }

    @Override
    public R promo(String categoryName) {

        R r = categoryClient.byName(categoryName);

        if (r.getCode().equals(R.FAIL_CODE)){
            log.info("ProductServiceimpl.promo业务结束，结果：{类别查询失败}");
            return r;
        }


        // 类别服务中 data = category --- feign {json}  --- product服务 LinkedHashMap jackson

//        Category category = (Category) r.getData();
        LinkedHashMap<String,Object> map = (LinkedHashMap<String, Object>) r.getData();

        Integer categoryId = (Integer) map.get("categoryId");

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
