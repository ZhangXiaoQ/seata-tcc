package com.itzyq.stock.service;

import com.itzyq.model.dto.OrderInfoDTO;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @className: StockTccAction
 * @description: 库存Tcc接口
 * @author: zyq
 * @date: 2021/2/20 16:41
 * @Version: 1.0
 */
@LocalTCC
public interface StockTccAction {

    @TwoPhaseBusinessAction(name = "stockAction",commitMethod = "commit",rollbackMethod = "rollback")
    boolean reduceStock(BusinessActionContext businessActionContext,
                        @BusinessActionContextParameter(paramName = "orderInfoDTO") OrderInfoDTO orderInfoDTO);
    boolean commit(BusinessActionContext businessActionContext);
    boolean rollback(BusinessActionContext businessActionContext);
}
