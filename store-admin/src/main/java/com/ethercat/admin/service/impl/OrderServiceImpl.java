package com.ethercat.admin.service.impl;

import com.ethercat.admin.service.OrderService;
import com.ethercat.clients.OrderClient;
import com.ethercat.param.PageParam;
import com.ethercat.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: b2c-store
 * @description:
 * @author: Ethercat
 * @create: 2022-11-28 19:17
 **/

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderClient orderClient;

    @Override
    public R list(PageParam pageParam) {
        R r = orderClient.list(pageParam);
        log.info("OrderServiceImpl.list业务结束，结果：{}",r);
        return r;
    }
}
