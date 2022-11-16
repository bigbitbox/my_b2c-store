package com.ethercat.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: b2c-store
 * @description: 类别热门商品参数接收
 * @author: Ethercat
 * @create: 2022-11-16 19:46
 **/

@Data
public class ProductPromoParam {
    @NotNull
    private String categoryName;
}
