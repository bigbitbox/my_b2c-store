package com.ethercat.cart.listener;

import com.ethercat.cart.service.CartService;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: b2c-store
 * @description: 监听mq消息
 * @author: Ethercat
 * @create: 2022-11-26 00:53
 **/

@Component
public class CartRabbitMqListener {
    @Autowired
    private CartService cartService;


    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "clear.queue"),
                    exchange = @Exchange(value = "topic.ex"),
                    key = "clear.cart"
            )
    )
    public void clear(List<Integer> cartIds){
        cartService.clearIds(cartIds);
    }
}
