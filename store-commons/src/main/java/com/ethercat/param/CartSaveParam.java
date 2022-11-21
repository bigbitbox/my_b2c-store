package com.ethercat.param;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: b2c-store
 * @description: 购物车添加参数接收
 * @author: Ethercat
 * @create: 2022-11-20 21:49
 **/
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartSaveParam {

    @JsonProperty("product_id")
    @NotNull
    private Integer productId;
    @JsonProperty("user_id")
    @NotNull
    private Integer userId;

}
