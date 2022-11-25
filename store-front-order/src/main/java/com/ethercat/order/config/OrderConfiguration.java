package com.ethercat.order.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: b2c-store
 * @description: 订单配置类
 * @author: Ethercat
 * @create: 2022-11-22 11:46
 **/

@Configuration
public class OrderConfiguration {
    /**
     * mq序列化方式，选择json
     */

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

}
