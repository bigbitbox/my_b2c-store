package com.ethercat.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @program: b2c-store
 * @description: 收藏调用商品传递参数
 * @author: Ethercat
 * @create: 2022-11-20 13:04
 **/

@Data
public class ProductCollectParam {

    @NotEmpty
    private List<Integer> productIds;
}
