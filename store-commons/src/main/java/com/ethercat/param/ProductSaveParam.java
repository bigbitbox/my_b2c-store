package com.ethercat.param;

import com.ethercat.pojo.Product;
import lombok.Data;

/**
 * @program: b2c-store
 * @description:
 * @author: Ethercat
 * @create: 2022-11-28 10:23
 **/

@Data
public class ProductSaveParam extends Product {


    /**
     * 保存商品详情的图片地址  图片之间使用+拼接
     */
    private String pictures;

}
