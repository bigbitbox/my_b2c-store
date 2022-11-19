package com.ethercat.param;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @program: b2c-store
 * @description: 类别商品展示
 * @author: Ethercat
 * @create: 2022-11-17 15:07
 **/

@Data
public class ProductIdsParam extends PageParam{

    @NotNull
    private List<Integer> categoryID;

}
