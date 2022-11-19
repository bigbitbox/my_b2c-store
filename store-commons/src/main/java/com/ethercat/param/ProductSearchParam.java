package com.ethercat.param;

import lombok.Data;

/**
 * @program: b2c-store
 * @description: 搜索关键字和分页参数集合
 * @author: Ethercat
 * @create: 2022-11-18 22:48
 **/
@Data
public class ProductSearchParam extends PageParam{
    private String search;
}
