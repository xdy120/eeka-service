package com.greatonce.oms.biz.trade;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.domain.trade.RefundApplyOrder;
import com.greatonce.oms.query.trade.RefundApplyOrderQuery;

import java.util.List;

/**
 * RefundApplyOrder <br/>
 * 售后申请单
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public interface RefundApplyOrderService extends BizService<RefundApplyOrder, RefundApplyOrderQuery> {
    RefundApplyOrder getByMallRefundId(Long storeId, String mallRefundId);

    /**
     * @param expressNo
     * @return
     *  根据快递单号找到 售后申请单
     */
    List<RefundApplyOrder> getRefundApplyOrderByExpressNo(String expressNo);


    void matchAbnormalApply(RefundApplyOrder refundApplyOrder);
}