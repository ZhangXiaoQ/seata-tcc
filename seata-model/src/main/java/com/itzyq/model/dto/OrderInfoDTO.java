package com.itzyq.model.dto;

import com.itzyq.model.entity.OrderDetailEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author zyq
 * @since 2021-02-02
 */
@Data
public class OrderInfoDTO implements Serializable {

    //订单号
    private String orderNum;

    //商品库存
    private List<OrderDetailEntity> stockList;


}
