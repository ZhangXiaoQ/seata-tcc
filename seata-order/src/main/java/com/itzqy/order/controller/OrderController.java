package com.itzqy.order.controller;

import com.itzqy.order.feign.StockService;
import com.itzyq.model.dto.OrderInfoDTO;
import com.itzqy.order.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: OrderController
 * @description: 订单类
 * @author: zyq
 * @date: 2021/2/2 11:02
 * @Version: 1.0
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderInfoService orderInfoService;


    @PostMapping("createOrder")
    public void createOrder(@RequestBody OrderInfoDTO orderInfoDTO){
        orderInfoService.createOrderInfo(orderInfoDTO);
    }


}
