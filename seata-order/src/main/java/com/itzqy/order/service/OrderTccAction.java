package com.itzqy.order.service;

import com.itzyq.model.entity.OrderInfoEntity;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @author zyq
 * @description 订单接口
 * @date 2021/2/20 14:40
 **/
@LocalTCC
public interface OrderTccAction {

    @TwoPhaseBusinessAction(name="orderAction",commitMethod = "commit",rollbackMethod = "rollback")
    boolean createOrder(BusinessActionContext businessActionContext,
                        @BusinessActionContextParameter(paramName = "orderInfoEntity") OrderInfoEntity orderInfoEntity);
    boolean commit(BusinessActionContext businessActionContext);
    boolean rollback(BusinessActionContext businessActionContext);
}
