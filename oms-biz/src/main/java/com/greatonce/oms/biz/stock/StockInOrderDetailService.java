package com.greatonce.oms.biz.stock;

import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.domain.stock.StockInOrder;
import com.greatonce.oms.domain.stock.StockInOrderDetail;
import com.greatonce.oms.query.stock.StockInOrderDetailQuery;
import java.util.List;

/**
 * StockInOrderDetail <br/>
 * 入库单明细
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */
public interface StockInOrderDetailService extends DetailService<StockInOrder,StockInOrderDetail,StockInOrderDetailQuery> {

  List<StockInOrderDetail> listDetails(Long stockInOrderId);
}