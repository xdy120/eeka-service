package com.greatonce.oms.biz.stock;

import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.stock.StockOutOrderCancelBO;
import com.greatonce.oms.bo.trade.WmsAutoOutBO;
import com.greatonce.oms.domain.stock.StockOutOrder;
import com.greatonce.oms.query.stock.StockOutOrderQuery;

/**
 * StockOutOrder <br/> 出库单
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface StockOutOrderService extends
    com.greatonce.oms.biz.BizService<StockOutOrder, StockOutOrderQuery> {

  /**
   * 根据编码获取.
   */
  StockOutOrder getByCode(String orderCode);

  /**
   * 审核.
   */
  void audit(StockOutOrder stockOutOrder, VersionBO versionBO);

  /**
   * 作废.
   */
  void cancel(StockOutOrder stockOutOrder, StockOutOrderCancelBO bo);

  /**
   * 完结.
   */
  void finish(StockOutOrder stockOutOrder, VersionBO bo);

  /**
   * 通知WMS.
   */
  void noticeWms(StockOutOrder stockOutOrder, VersionBO bo);


  /**
   * WMS回传入库信息.
   */
  void wmsAutoOut(StockOutOrder stockOutOrder, WmsAutoOutBO wmsAutoOutBO);

  /**
   * 导出
   */
  void exportStockOut(String fileName, StockOutOrderQuery query);
}