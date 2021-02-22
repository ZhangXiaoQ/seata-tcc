package com.itzyq.stock.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itzyq.model.dto.OrderInfoDTO;
import com.itzyq.model.entity.StockInfoEntity;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zyq
 * @since 2021-02-02
 */
public interface StockInfoService extends IService<StockInfoEntity> {

    void updateStockInfo(OrderInfoDTO orderInfoDTO);
}
