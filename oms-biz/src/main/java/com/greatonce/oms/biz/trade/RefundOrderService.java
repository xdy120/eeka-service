package com.greatonce.oms.biz.trade;

import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.domain.trade.RefundOrder;
import com.greatonce.oms.query.trade.RefundOrderQuery;

/**
 * RefundOrder <br/>
 * 退款单
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */
public interface RefundOrderService extends
    com.greatonce.oms.biz.BizService<RefundOrder, RefundOrderQuery> {
    void invalid(RefundOrder refundOrder,VersionBO bo);

    void review(RefundOrder refundOrder,VersionBO bo);

    void audit(RefundOrder refundOrder,VersionBO bo);
}