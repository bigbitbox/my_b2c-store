package com.ethercat.param;

import com.ethercat.vo.CartVo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: b2c-store
 * @description: 订单接收参数的param
 * @author: Ethercat
 * @create: 2022-11-22 12:02
 **/

@Data
public class OrderParam implements Serializable {

    public static final Long serialVersionUID = 1L;

    @JsonProperty("user_id")
    private Integer userId;
    private List<CartVo> products;

}
