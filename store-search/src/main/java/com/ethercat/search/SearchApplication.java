package com.ethercat.search;

/**
 * @program: b2c-store
 * @description: 搜索服务的启动类
 * @author: Ethercat
 * @create: 2022-11-17 21:29
 **/


//排除自动导入数据库配置,否者出现为配置连接池信息异常

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.ethercat.clients.ProductClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        DruidDataSourceAutoConfigure.class ,
        HibernateJpaAutoConfiguration.class})
@EnableFeignClients(clients = {ProductClient.class})
public class SearchApplication {
    public static void main(String[] args) {
        System.out.println("SearchApplication.main-----");
        SpringApplication.run(SearchApplication.class,args);
        System.out.println("SearchApplication.main+++++");
    }
}
