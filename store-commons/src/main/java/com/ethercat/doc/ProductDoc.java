package com.ethercat.doc;

import com.ethercat.pojo.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: b2c-store
 * @description: 用来存储商品搜索数据的实体类
 * @author: Ethercat
 * @create: 2022-11-18 11:36
 **/

@Data
@NoArgsConstructor
public class ProductDoc extends Product {

    /**
     * 用于模糊查询字段,由商品名,标题和描述组成
     */
    private String all;

    public ProductDoc(Product product) {
        super(product.getProductId(),product.getProductName(),
                product.getCategoryId(),product.getProductTitle(),
                product.getProductIntro(),product.getProductPicture(),
                product.getProductPrice(),product.getProductSellingPrice(),
                product.getProductNum(),product.getProductSales());
        this.all = product.getProductName()+product.getProductTitle()+product.getProductIntro();
    }
}

