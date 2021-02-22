package com.itzqy.order.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.itzqy.order.mapper.OrderInfoMapper;
import com.itzqy.order.service.OrderTccAction;
import com.itzyq.model.entity.OrderInfoEntity;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @className: OrderTccActionImpl
 * @description: 订单tcc实现类
 * @author: zyq
 * @date: 2021/2/20 14:43
 * @Version: 1.0
 */
@Slf4j
@Component
@Transactional
public class OrderTccActionImpl implements OrderTccAction {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Override
    public boolean createOrder(BusinessActionContext businessActionContext, OrderInfoEntity orderInfoEntity) {
        orderInfoEntity.setStatus((byte) 1);
        orderInfoMapper.insert(orderInfoEntity);
        log.info("创建订单：tcc一阶段try成功");
        return true;
    }

    @Override
    public boolean commit(BusinessActionContext businessActionContext) {
        JSONObject jsonObject= (JSONObject) businessActionContext.getActionContext("orderInfoEntity");
        OrderInfoEntity order = new OrderInfoEntity();
        BeanUtil.copyProperties(jsonObject,order);
        order.setStatus((byte) 0);
        orderInfoMapper.updateById(order);
        log.info("创建订单：tcc二阶段commit成功");
        return true;
    }

    @Override
    public boolean rollback(BusinessActionContext businessActionContext) {
        JSONObject jsonObject= (JSONObject) businessActionContext.getActionContext("orderInfoEntity");
        OrderInfoEntity order = new OrderInfoEntity();
        BeanUtil.copyProperties(jsonObject,order);
        orderInfoMapper.deleteById(order);
        log.info("创建订单：tcc二阶段回滚成功");
        return true;
    }
}
