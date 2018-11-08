package com.greatonce.oms.biz.stock;

import com.greatonce.oms.biz.BatchBizService;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.domain.stock.StockTransit;
import com.greatonce.oms.query.stock.StockTransitQuery;

/**
 * StockTransit <br/>
 * 在途库存
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */
public interface StockTransitService extends BatchBizService<StockTransit,StockTransitQuery> {

  void deleteByPurchaseId(Long purchaseOrderId);
}