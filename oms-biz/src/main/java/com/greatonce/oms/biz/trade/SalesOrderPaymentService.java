package com.greatonce.oms.biz.trade;

import com.greatonce.oms.biz.BatchBizService;
import com.greatonce.oms.domain.trade.SalesOrderPayment;
import com.greatonce.oms.query.trade.SalesOrderPaymentQuery;
import java.util.List;

/**
 * SalesOrderPayment <br/>
 * 销售订单支付明细
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public interface SalesOrderPaymentService extends
    BatchBizService<SalesOrderPayment, SalesOrderPaymentQuery> {

  List<SalesOrderPayment> listBySalesOrderId(Long salesOrderId);
}