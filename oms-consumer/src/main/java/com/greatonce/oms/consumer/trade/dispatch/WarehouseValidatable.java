package com.greatonce.oms.consumer.trade.dispatch;

import com.greatonce.oms.consumer.trade.dispatch.wrapper.DispatchOrderWrapper;
import com.greatonce.oms.domain.base.StockDispatchWarehouse;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/24
 */
public interface WarehouseValidatable {

  /**
   * 校验仓库是否可以支持此配货单.
   */
  boolean validate(DispatchContext context, DispatchOrderWrapper dispatchOrder,
      StockDispatchWarehouse stockDispatchWarehouse);
}
