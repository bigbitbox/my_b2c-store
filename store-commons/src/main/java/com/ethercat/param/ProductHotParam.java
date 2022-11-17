package com.ethercat.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @program: b2c-store
 * @description: 热门商品参数接收对象
 * @author: Ethercat
 * @create: 2022-11-17 10:33
 **/

@Data
public class ProductHotParam {

    @NotEmpty
    private List<String> categoryName;
}
