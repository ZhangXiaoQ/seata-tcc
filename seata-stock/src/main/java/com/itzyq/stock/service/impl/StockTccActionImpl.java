package com.itzyq.stock.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itzyq.model.dto.OrderInfoDTO;
import com.itzyq.model.entity.OrderDetailEntity;
import com.itzyq.model.entity.StockInfoEntity;
import com.itzyq.stock.mapper.StockInfoMapper;
import com.itzyq.stock.service.StockTccAction;
import com.itzyq.stock.util.IdempotentUtil;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @className: StockTccActionImpl
 * @description: TODO
 * @author: zyq
 * @date: 2021/2/20 16:45
 * @Version: 1.0
 */
@Component
@Slf4j
@Transactional
public class StockTccActionImpl implements StockTccAction {

    @Autowired
    private StockInfoMapper stockInfoMapper;

    @Override
    public boolean reduceStock(BusinessActionContext businessActionContext, OrderInfoDTO orderInfoDTO) {
        if (Objects.nonNull(IdempotentUtil.getMarker(getClass(),businessActionContext.getXid()))){
            log.info("已执行过try阶段");
            return true;
        }
        List<OrderDetailEntity> stockList = orderInfoDTO.getStockList();
        stockList.stream().forEach(item->{
            StockInfoEntity stock = stockInfoMapper.selectOne(new LambdaQueryWrapper<StockInfoEntity>().eq(StockInfoEntity::getId, item.getStockId()));
            int quantity = item.getQuantity();
            stock.setQuantity(stock.getQuantity()-quantity);
            stock.setFrozen(stock.getFrozen()+quantity);
            stockInfoMapper.updateById(stock);
        });
        IdempotentUtil.addMarker(getClass(),businessActionContext.getXid(),"marker");
        log.info("减少库存：tcc一阶段try成功");
        return true;
    }

    @Override
    public boolean commit(BusinessActionContext businessActionContext) {
        if (Objects.isNull(IdempotentUtil.getMarker(getClass(),businessActionContext.getXid()))){
            log.info("已执行过commit阶段");
            return true;
        }

        JSONObject jsonObject = (JSONObject) businessActionContext.getActionContext("orderInfoDTO");
        OrderInfoDTO orderInfoDTO = new OrderInfoDTO();
        BeanUtil.copyProperties(jsonObject,orderInfoDTO);

        List<OrderDetailEntity> stockList = orderInfoDTO.getStockList();
        stockList.stream().forEach(item->{
            StockInfoEntity stock = stockInfoMapper.selectOne(new LambdaQueryWrapper<StockInfoEntity>().eq(StockInfoEntity::getId, item.getStockId()));
            int quantity = item.getQuantity();
            stock.setFrozen(stock.getFrozen()-quantity);
            stock.setSold(stock.getSold()+quantity);
            stockInfoMapper.updateById(stock);
        });
        log.info("减少库存：tcc二阶段commit成功");
        IdempotentUtil.removeMarker(getClass(),businessActionContext.getXid());
        return true;
    }

    @Override
    public boolean rollback(BusinessActionContext businessActionContext) {
        if (Objects.isNull(IdempotentUtil.getMarker(getClass(),businessActionContext.getXid()))){
            log.info("已执行过rollback阶段");
            return true;
        }

        JSONObject jsonObject = (JSONObject) businessActionContext.getActionContext("orderInfoDTO");
        OrderInfoDTO orderInfoDTO = new OrderInfoDTO();
        BeanUtil.copyProperties(jsonObject,orderInfoDTO);

        List<OrderDetailEntity> stockList = orderInfoDTO.getStockList();
        stockList.stream().forEach(item->{
            StockInfoEntity stock = stockInfoMapper.selectOne(new LambdaQueryWrapper<StockInfoEntity>().eq(StockInfoEntity::getId, item.getStockId()));
            int quantity = item.getQuantity();
            stock.setQuantity(stock.getQuantity()+quantity);
            stock.setFrozen(stock.getFrozen()-quantity);
            stockInfoMapper.updateById(stock);
        });
        log.info("减少库存：tcc二阶段回滚成功");
        IdempotentUtil.removeMarker(getClass(),businessActionContext.getXid());
        return true;
    }
}
