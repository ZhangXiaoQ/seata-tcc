package com.itzyq.stock.controller;

import com.itzyq.model.dto.OrderInfoDTO;
import com.itzyq.stock.service.StockInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: StockController
 * @description: TODO
 * @author: zyq
 * @date: 2021/2/2 11:02
 * @Version: 1.0
 */
@RestController
@RequestMapping("stock")
public class StockController {

    @Autowired
    private StockInfoService stockInfoService;

    @PostMapping("updateStockInfo")
    public void updateStockInfo(@RequestBody OrderInfoDTO orderInfoDTO){
        stockInfoService.updateStockInfo(orderInfoDTO);
    }
}
