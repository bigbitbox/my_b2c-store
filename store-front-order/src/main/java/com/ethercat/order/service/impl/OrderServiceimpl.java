package com.ethercat.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ethercat.order.mapper.OrderMapper;
import com.ethercat.order.service.OrderService;
import com.ethercat.param.OrderParam;
import com.ethercat.pojo.Order;
import com.ethercat.to.OrderToProduct;
import com.ethercat.utils.R;
import com.ethercat.vo.CartVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: b2c-store
 * @description:
 * @author: Ethercat
 * @create: 2022-11-22 15:26
 **/

@Service
@Slf4j
public class OrderServiceimpl extends ServiceImpl<OrderMapper, Order>  implements OrderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * 进行订单数据保存业务
     *
     * @param orderParam
     * @return
     */
    @Transactional
    @Override
    public R save(OrderParam orderParam) {

        //直接调用service提供的方法
        //baseMapper内置对象，调用mapper原有的方法

        //准备数据
        List<Integer> cartIds = new ArrayList<>();
        List<OrderToProduct> orderToProducts = new ArrayList<>();
        List<Order> orderList   = new ArrayList<>();


        //生成数据
        Integer userId = orderParam.getUserId();
        long orderId = System.currentTimeMillis();

        for (CartVo product : orderParam.getProducts()) {
            cartIds.add(product.getId());
            OrderToProduct orderToProduct = new OrderToProduct();
            orderToProduct.setNum(product.getNum());
            orderToProduct.setProductId(product.getProductID());
            orderToProducts.add(orderToProduct);

            Order order = new Order();
            order.setOrderId(orderId);
            order.setOrderTime(orderId);
            order.setUserId(userId);
            order.setProductId(product.getProductID());
            order.setProductNum(product.getNum());
            order.setProductPrice(product.getPrice());
            orderList.add(order);

        }

        //订单数据批量保存
        saveBatch(orderList);

        //发送购物车消息
        rabbitTemplate.convertAndSend("topic.ex","clear.cart",cartIds);
        //发送商品服务消息
        rabbitTemplate.convertAndSend("topic.ex","sub.number",orderToProducts);


        return null;
    }
}
