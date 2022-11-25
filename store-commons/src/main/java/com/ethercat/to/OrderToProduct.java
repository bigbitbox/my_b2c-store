package com.ethercat.to;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: b2c-store
 * @description: 订单发送商品服务的实体
 * @author: Ethercat
 * @create: 2022-11-22 12:05
 **/

@Data
public class OrderToProduct implements Serializable {

    public static final Long serialVersionUID = 1L;

    private Integer productId;
    private Integer num;
}
