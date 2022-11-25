package com.ethercat.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ethercat.order.mapper.OrderMapper;
import com.ethercat.order.service.OrderService;
import com.ethercat.param.OrderParam;
import com.ethercat.pojo.Order;
import com.ethercat.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: b2c-store
 * @description:
 * @author: Ethercat
 * @create: 2022-11-22 15:26
 **/

@Service
@Slf4j
public class OrderServiceimpl extends ServiceImpl<OrderMapper, Order>  implements OrderService {

    /**
     * 进行订单数据保存业务
     *
     * @param orderParam
     * @return
     */
    @Override
    public R save(OrderParam orderParam) {

        //直接调用service提供的方法
        //baseMapper内置对象，调用mapper原有的方法


        return null;
    }
}
