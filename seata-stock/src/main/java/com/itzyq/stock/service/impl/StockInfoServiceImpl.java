package com.itzyq.stock.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itzyq.model.dto.OrderInfoDTO;
import com.itzyq.model.entity.OrderDetailEntity;
import com.itzyq.model.entity.StockInfoEntity;
import com.itzyq.stock.mapper.StockInfoMapper;
import com.itzyq.stock.service.StockInfoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zyq
 * @since 2021-02-02
 */
@Service
public class StockInfoServiceImpl extends ServiceImpl<StockInfoMapper, StockInfoEntity> implements StockInfoService {

//    @Autowired
//    private StockInfoMapper stockInfoMapper;
    @Autowired
    private StockTccActionImpl stockTccActionImpl;

    @Override
    @Transactional
    public void updateStockInfo(OrderInfoDTO orderInfoDTO) {
        stockTccActionImpl.reduceStock(null,orderInfoDTO);
        /*List<OrderDetailEntity> stockList = orderInfoDTO.getStockList();
        stockList.stream().forEach(item->{
            StockInfoEntity stockInfoEntity = stockInfoMapper.selectOne(new LambdaQueryWrapper<StockInfoEntity>().eq(StockInfoEntity::getId, item.getStockId()));
            stockInfoEntity.setQuantity(stockInfoEntity.getQuantity() - item.getQuantity());
            int resu = stockInfoMapper.updateById(stockInfoEntity);
//            if(resu==1){
//                throw new RuntimeException();
//            }
        });*/
    }
}
