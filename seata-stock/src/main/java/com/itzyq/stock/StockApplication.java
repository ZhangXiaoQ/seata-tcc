package com.itzyq.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @className: StockApplication
 * @description: stock启动类
 * @author: zyq
 * @date: 2021/2/2 9:40
 * @Version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class StockApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockApplication.class,args);
    }
}
