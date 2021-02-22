package com.itzqy.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itzyq.model.entity.OrderDetailEntity;
import com.itzqy.order.mapper.OrderDetailMapper;
import com.itzqy.order.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zyq
 * @since 2021-02-02
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetailEntity> implements OrderDetailService {

}
