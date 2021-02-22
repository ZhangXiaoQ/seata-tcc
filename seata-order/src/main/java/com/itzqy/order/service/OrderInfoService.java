package com.itzqy.order.service;

import com.itzyq.model.dto.OrderInfoDTO;
import com.itzyq.model.entity.OrderInfoEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zyq
 * @since 2021-02-02
 */
public interface OrderInfoService extends IService<OrderInfoEntity> {

    void createOrderInfo(OrderInfoDTO orderInfoDTO);
}
