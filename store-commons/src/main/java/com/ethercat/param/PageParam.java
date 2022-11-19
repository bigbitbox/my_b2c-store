package com.ethercat.param;

import lombok.Data;

/**
 * @program: b2c-store
 * @description: 分页属性
 * @author: Ethercat
 * @create: 2022-11-18 22:58
 **/
@Data
public class PageParam {
    private int currentPage = 1;
    private int pageSize = 15;
}
