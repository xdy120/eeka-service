package com.greatonce.oms.biz.trade;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.domain.trade.ExchangeApplyOrder;
import com.greatonce.oms.query.trade.ExchangeApplyOrderQuery;

import java.util.List;

/**
 * ExchangeApplyOrder <br/>
 * 换货申请单
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public interface ExchangeApplyOrderService extends BizService<ExchangeApplyOrder,ExchangeApplyOrderQuery>{

    void refuse(Long exchangeId, String reasonCode);

    void agree(Long exchangeId);

    /**
     * 异常换货申请单 就是没有sku 的信息,使用此方法自动匹配
     * @param applyOrder
     */
    void matchAbnormalApply(ExchangeApplyOrder applyOrder);

    /**
     * @param expressNo
     * @return
     *
     * 通过快递号 找到换货申请
     */
    List<ExchangeApplyOrder> getByExpressNo(String expressNo);

    /**
     * @param exchangeApplyId
     *
     * 释放申请占用
     */
    void deleteOccupancy(Long exchangeApplyId);

}