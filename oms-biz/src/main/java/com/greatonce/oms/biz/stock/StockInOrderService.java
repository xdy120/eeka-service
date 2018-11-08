package com.greatonce.oms.biz.stock;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.stock.StockInOrderCancelBO;
import com.greatonce.oms.bo.trade.WmsAutoInBO;
import com.greatonce.oms.domain.stock.StockInOrder;
import com.greatonce.oms.query.stock.StockInOrderQuery;

/**
 * StockInOrder <br/> 入库单
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface StockInOrderService extends
    BizService<StockInOrder, StockInOrderQuery> {

  /**
   * 根据编码获取.
   */
  StockInOrder getByCode(String orderCode);

  void audit(StockInOrder stockInOrder, VersionBO bo);

  void cancel(StockInOrder stockInOrder, StockInOrderCancelBO bo);

  /**
   * 通知wms.
   */
  void noticeWms(StockInOrder stockInOrder, VersionBO bo);

  /**
   * WMS回传入库信息.
   */
  void wmsAutoIn(StockInOrder stockInOrder, WmsAutoInBO wmsAutoInBO);
}