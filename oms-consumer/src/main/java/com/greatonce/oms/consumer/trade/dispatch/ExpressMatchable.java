package com.greatonce.oms.consumer.trade.dispatch;

import com.greatonce.oms.domain.base.StockDispatchWarehouse;
import com.greatonce.oms.domain.trade.DispatchOrder;

/**
 * 快递匹配器
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/20
 */
public interface ExpressMatchable {

  /**
   * 匹配快递
   */
  void matchExpress(DispatchContext context,
      DispatchOrder dispatchOrder, StockDispatchWarehouse warehouse);
}
