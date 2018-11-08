package com.greatonce.oms.biz.trade;

import com.greatonce.oms.biz.BatchBizService;
import com.greatonce.oms.domain.trade.SalesOrderInvoice;
import com.greatonce.oms.query.trade.SalesOrderInvoiceQuery;
import java.util.List;

/**
 * SalesOrderInvoice <br/>
 * 销售订单发票明细
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface SalesOrderInvoiceService extends
    BatchBizService<SalesOrderInvoice, SalesOrderInvoiceQuery> {

  List<SalesOrderInvoice> listBySalesOrderId(Long salesOrderId);
}