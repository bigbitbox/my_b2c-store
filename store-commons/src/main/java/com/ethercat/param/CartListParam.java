package com.ethercat.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: b2c-store
 * @description: 购物车查询接收参数
 * @author: Ethercat
 * @create: 2022-11-21 13:24
 **/
@Data
public class CartListParam {
    @JsonProperty("user_id")
    @NotNull
    private Integer userId;
}
