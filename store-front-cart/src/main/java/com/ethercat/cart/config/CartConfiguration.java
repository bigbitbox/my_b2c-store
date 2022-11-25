package com.ethercat.cart.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: b2c-store
 * @description: 购物车配置
 * @author: Ethercat
 * @create: 2022-11-26 00:52
 **/
@Configuration
public class CartConfiguration {
    /**
     * mq序列化方式，选择json
     */

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

}
