package com.ethercat.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ethercat.param.OrderParam;
import com.ethercat.pojo.Order;
import com.ethercat.utils.R;

/**
 * @program: b2c-store
 * @description:
 * @author: Ethercat
 * @create: 2022-11-22 15:24
 **/

public interface OrderService extends IService<Order> {

    /**
     * 进行订单数据保存业务
     * @param orderParam
     * @return
     */
    R save(OrderParam orderParam);
}