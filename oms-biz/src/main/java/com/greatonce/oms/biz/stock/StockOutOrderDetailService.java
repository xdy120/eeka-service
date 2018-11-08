package com.greatonce.oms.biz.stock;

import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.domain.stock.StockOutOrder;
import com.greatonce.oms.domain.stock.StockOutOrderDetail;
import com.greatonce.oms.query.stock.StockOutOrderDetailQuery;
import java.util.List;

/**
 * StockOutOrderDetail <br/> 出库单明细
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface StockOutOrderDetailService extends
    DetailService<StockOutOrder, StockOutOrderDetail, StockOutOrderDetailQuery> {

  /**
   * 查询可用库存.
   */
  List<StockOutOrderDetail> listAvailable(Long stockOutOrderId);

  /**
   * 查询可销库存.
   */
  List<StockOutOrderDetail> listSaleable(Long stockOutOrderId);

  /**
   * 获取明细.
   */
  List<StockOutOrderDetail> listDetails(Long stockOutOrderId);
}