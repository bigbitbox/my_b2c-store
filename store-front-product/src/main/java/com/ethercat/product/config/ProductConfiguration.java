package com.ethercat.product.config;

import com.ethercat.config.CacheConfiguration;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: b2c-store
 * @description: 商品模块的配置类
 * @author: Ethercat
 * @create: 2022-11-20 00:20
 **/
@Configuration
public class ProductConfiguration extends CacheConfiguration {

    /**
     * mq序列化方式，选择json
     */

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

}
