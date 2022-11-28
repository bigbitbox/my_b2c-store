package com.ethercat.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ethercat.clients.*;
import com.ethercat.param.*;
import com.ethercat.pojo.Category;
import com.ethercat.pojo.Picture;
import com.ethercat.pojo.Product;
import com.ethercat.product.mapper.PictureMapper;
import com.ethercat.product.mapper.ProductMapper;
import com.ethercat.product.service.ProductService;
import com.ethercat.to.OrderToProduct;
import com.ethercat.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @program: b2c-store
 * @description:
 * @author: Ethercat
 * @create: 2022-11-16 23:55
 **/
@Service
@Slf4j
public class ProductServiceimpl extends ServiceImpl<ProductMapper,Product> implements ProductService {

    @Autowired
    PictureMapper pictureMapper;

    @Autowired
    private CategoryClient categoryClient;

    @Autowired
    private OrderClient orderClient;

    @Autowired
    private CartClient cartClient;

    @Autowired
    private CollectClient collectClient;

    @Autowired
    private SearchClient searchClient;


    @Autowired
    private ProductMapper productMapper;

    @Cacheable(value = "list.product", key = "#productHotParam.categoryName")
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

    @Cacheable(value = "list.product", key = "#categoryName", cacheManager = "cacheManagerHour")
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
     * 通用性业务，
     * 传入了类别id，根据id查询并分页
     * 没有传入类别id，查询全部出
     *
     * @param productIdsParam
     * @return
     */
    @Cacheable(value = "list.product", key = "#productIdsParam.categoryID+'-'+#productIdsParam.currentPage+'-'+#productIdsParam.pageSize")
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
    @Cacheable(value = "product", key = "#productID")
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
    @Cacheable(value = "picture",key = "#productID")
    @Override
    public R pictures(Integer productID) {

        QueryWrapper<Picture> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id",productID);

        List<Picture> pictures = pictureMapper.selectList(queryWrapper);

        R ok = R.ok(pictures);

        log.info("ProductServiceimpl.pictures业务结束，结果：{}",ok);

        return ok;
    }

    /**
     * 搜索服务调用，获取全部商品数据进行同步
     *
     * @return 商品数据集合
     */
    @Cacheable(value = "list.category",key = "#root.methodName", cacheManager = "cacheManagerDay")
    @Override
    public List<Product> allList() {
        List<Product> products = productMapper.selectList(null);

        log.info("ProductServiceimpl.allList业务结束，结果：{}",products.size());

        return products;
    }

    /**
     * 搜索业务，需要调用搜索服务
     *
     * @param productSearchParam
     * @return
     */
    @Override
    public R search(ProductSearchParam productSearchParam) {
        R r = searchClient.search(productSearchParam);
        log.info("ProductServiceimpl.search业务结束，结果：{}",r);
        return r;
    }

    /**
     * 根据商品id集合查询商品信息
     *  __用于查询收藏的商品
     * @param productIds
     * @return
     */
    @Cacheable(value = "list.product",key = "#productIds")
    @Override
    public R ids(List<Integer> productIds) {

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("product_id",productIds);

        List<Product> productList = productMapper.selectList(queryWrapper);

        R ok = R.ok("id商品集合查询成功！", productList);

        log.info("ProductServiceimpl.ids业务结束，结果：{}",ok);
        return ok;
    }

    /**
     * 根据商品id查询商品id集合
     *
     * @param productIds
     * @return
     */
    @Override
    public List<Product> cartList(List<Integer> productIds) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("product_id",productIds);

        List<Product> productList = productMapper.selectList(queryWrapper);
        log.info("ProductServiceimpl.cartList业务结束，结果：{}",productList);
        return productList;
    }

    /**
     * 修改库存和增加销售量
     *
     * @param orderToProducts
     */
    @Override
    public void subNumber(List<OrderToProduct> orderToProducts) {

        //将集合转成map
        Map<Integer, OrderToProduct> map = orderToProducts.stream().collect(Collectors.toMap(OrderToProduct::getProductId, v -> v));

        //获取商品id集合
        Set<Integer> productIds = map.keySet();
        //查询集合对应的商品信息
        List<Product> productList = productMapper.selectBatchIds(productIds);
        //修改商品信息
        for (Product product : productList) {
            Integer num = map.get(product.getProductId()).getNum();
            product.setProductNum(product.getProductNum() - num);
            product.setProductSales(product.getProductSales()+num);
        }
        //批量更新
        this.updateBatchById(productList);
        log.info("ProductServiceimpl.subNumber业务结束，结果：库存和销售量的修改完毕");
    }

    /**
     * 类别对应的商品数量查询
     *
     * @param categoryId
     * @return
     */
    @Override
    public Long adminCount(Integer categoryId) {

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id",categoryId);

        Long aLong = baseMapper.selectCount(queryWrapper);

        log.info("ProductServiceimpl.adminCount业务结束，结果：{}",aLong);
        return aLong;
    }

    @CacheEvict(value = "list.product", allEntries = true)
    @Override
    public R adminSave(ProductSaveParam productSaveParam) {
        Product product = new Product();
        BeanUtils.copyProperties(productSaveParam,product);

        int insert = productMapper.insert(product);
        log.info("ProductServiceimpl.adminSave业务结束，结果：{}",insert);

        String pictures = productSaveParam.getPictures();
        
        if (!StringUtils.isEmpty(pictures)){
            // 截取特殊字符串的时候 // [] 包含 $ + * | ?
            String[] urls = pictures.split("\\+");
            for (String url : urls) {
                Picture picture = new Picture();
                picture.setProductId(product.getProductId());
                picture.setProductPicture(url);
                pictureMapper.insert(picture);//插入商品的图片
            }
        }

        //同步搜索服务的数据
        searchClient.saveOrUpdate(product);
        return R.ok("数据保存成功");
    }

    @Override
    public R adminUpdate(Product product) {

        productMapper.updateById(product);

        searchClient.saveOrUpdate(product);


        return null;
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "product.list",allEntries = true),
                    @CacheEvict(value = "product",key = "#productId")
            }
    )
    @Override
    public R adminRemove(Integer productId) {

        R r = cartClient.removeCheck(productId);

        if ("004".equals(r.getCode())) {
            log.info("ProductServiceimpl.adminRemove业务结束，结果：{}",r.getMsg());
            return r;
        }

        r = orderClient.removeCheck(productId);

        if ("004".equals(r.getCode())) {
            log.info("ProductServiceimpl.adminRemove业务结束，结果：{}",r.getMsg());
            return r;
        }

        //删除商品
        productMapper.deleteById(productId);

        //删除商品图片
        QueryWrapper<Picture> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id",productId);
        pictureMapper.delete(queryWrapper);

        //删除收藏中和本商品相关的
        collectClient.remove(productId);

        //同步数据


        return R.ok("商品删除成功！");
    }
}
