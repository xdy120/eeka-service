package com.greatonce.oms.biz.trade;

import com.greatonce.oms.biz.BatchBizService;
import com.greatonce.oms.domain.trade.SalesOrderDiscount;
import com.greatonce.oms.query.trade.SalesOrderDiscountQuery;
import java.util.List;

/**
 * SalesOrderDiscount <br/>
 * 销售订单优惠明细
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface SalesOrderDiscountService extends
    BatchBizService<SalesOrderDiscount, SalesOrderDiscountQuery> {

  List<SalesOrderDiscount> listBySalesOrderId(Long salesOrderId);
}