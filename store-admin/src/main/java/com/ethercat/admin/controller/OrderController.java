package com.ethercat.admin.controller;

import com.ethercat.admin.service.OrderService;
import com.ethercat.param.PageParam;
import com.ethercat.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: b2c-store
 * @description:
 * @author: Ethercat
 * @create: 2022-11-28 19:14
 **/

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("list")
    public R list(PageParam pageParam){
        return orderService.list(pageParam);
    }

}
