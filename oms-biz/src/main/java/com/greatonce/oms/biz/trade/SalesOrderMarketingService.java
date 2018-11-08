package com.greatonce.oms.biz.trade;

import com.greatonce.oms.biz.BatchBizService;
import com.greatonce.oms.domain.trade.SalesOrderMarketing;
import com.greatonce.oms.query.trade.SalesOrderMarketingQuery;
import java.util.List;

/**
 * SalesOrderMarketing <br/>
 * 销售订单活动表
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface SalesOrderMarketingService extends
    BatchBizService<SalesOrderMarketing, SalesOrderMarketingQuery> {

  List<SalesOrderMarketing> listBySalesOrderId(Long salesOrderId);
}