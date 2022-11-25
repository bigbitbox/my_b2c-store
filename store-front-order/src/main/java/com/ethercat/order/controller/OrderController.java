package com.ethercat.order.controller;

import com.ethercat.order.service.OrderService;
import com.ethercat.param.OrderParam;
import com.ethercat.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: b2c-store
 * @description: 订单
 * @author: Ethercat
 * @create: 2022-11-22 15:00
 **/


@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("save")
    public R save(@RequestBody OrderParam orderParam){
        return orderService.save(orderParam);
    }
}
