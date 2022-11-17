package com.ethercat.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ethercat.clients.CategoryClient;
import com.ethercat.param.ProductHotParam;
import com.ethercat.param.ProductIdParam;
import com.ethercat.param.ProductIdsParam;
import com.ethercat.pojo.Category;
import com.ethercat.pojo.Picture;
import com.ethercat.pojo.Product;
import com.ethercat.product.mapper.PictureMapper;
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
    PictureMapper pictureMapper;

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

        Integer categoryId = (Integer) map.get("category_id");

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


    @Override
    public R clist() {

        R r = categoryClient.list();
        log.info("ProductServiceimpl.clist业务结束，结果：{}",r);

        return r;
    }

    /**
     * 按照类别查询
     *
     * @param productIdsParam
     * @return
     */
    @Override
    public R byCategory(ProductIdsParam productIdsParam) {


        List<Integer> categoryID = productIdsParam.getCategoryID();

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();

        if (!categoryID.isEmpty()){
            queryWrapper.in("category_id",categoryID);
        }

        IPage<Product> page = new Page<>(productIdsParam.getCurrentPage(),productIdsParam.getPageSize());

        page = productMapper.selectPage(page, queryWrapper);

        R ok = R.ok("查询成功", page.getRecords(),page.getTotal());

        return ok;
    }

    /**
     * 根据商品id查询商品信息
     *
     * @param productID
     * @return
     */
    @Override
    public R detail(Integer productID) {

        Product product = productMapper.selectById(productID);

        R ok = R.ok(product);
        log.info("ProductServiceimpl.detail业务结束，结果：{}",ok);

        return ok;
    }

    /**
     * 查询商品对应的图片集合
     *
     * @param productID
     * @return
     */
    @Override
    public R pictures(Integer productID) {

        QueryWrapper<Picture> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id",productID);

        List<Picture> pictures = pictureMapper.selectList(queryWrapper);

        R ok = R.ok(pictures);

        log.info("ProductServiceimpl.pictures业务结束，结果：{}",ok);

        return ok;
    }
}
