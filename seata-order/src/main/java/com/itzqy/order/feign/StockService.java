package com.itzqy.order.feign;

import com.itzyq.model.dto.OrderInfoDTO;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "stock-service")
public interface StockService {

    @RequestMapping("/stock/updateStockInfo")
    void updateStockInfo(@Param(value = "orderInfoDTO") OrderInfoDTO orderInfoDTO);
}
