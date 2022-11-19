package com.ethercat.product.service;

import com.ethercat.param.ProductHotParam;
import com.ethercat.param.ProductIdParam;
import com.ethercat.param.ProductIdsParam;
import com.ethercat.param.ProductSearchParam;
import com.ethercat.pojo.Product;
import com.ethercat.utils.R;

import java.util.List;

public interface ProductService {
    R promo(String categoryName);

    /**
     * 多类别商品热门查询 根据类别名称集合！之多查询7条
     * @param productHotParam
     * @return
     */
    R hots(ProductHotParam productHotParam);

    /**
     * 查询类别的集合
     * @return
     */
    R clist();

    /**
     * 按照类别查询
     * 通用型服务：
     *  传入了类别id，根据id查询并分页
     *  如果没有传入类别的id。
     * @param productIdsParam
     * @return
     */
    R byCategory(ProductIdsParam productIdsParam);


    /**根据商品id查询商品信息
     *
     * @param productID
     * @return
     */
    R detail(Integer productID);

    /**
     * 查询商品对应的图片集合
     * @param productID
     * @return
     */
    R pictures(Integer productID);

    /**
     * 搜索服务调用，获取全部商品数据进行同步
     * @return 商品数据集合
     */
    List<Product> allList();

    /**搜索业务，需要调用搜索服务
     *
     * @param productSearchParam
     * @return
     */
    R search(ProductSearchParam productSearchParam);
}
