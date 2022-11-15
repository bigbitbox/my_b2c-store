package com.ethercat.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: b2c-store
 * @description: 地址移除参数
 * @author: Ethercat
 * @create: 2022-11-15 08:56
 **/

@Data
public class AddressRemoveParam {
    @NotNull
    private Integer id;
}
