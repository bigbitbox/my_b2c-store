package com.ethercat.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: b2c-store
 * @description: id参数接收
 * @author: Ethercat
 * @create: 2022-11-17 20:19
 **/

@Data
public class ProductIdParam {

    @NotNull
    private Integer productID;
}
