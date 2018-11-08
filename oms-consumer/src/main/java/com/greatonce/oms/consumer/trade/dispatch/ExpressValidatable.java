package com.greatonce.oms.consumer.trade.dispatch;

import com.greatonce.oms.domain.base.Express;
import com.greatonce.oms.domain.base.StockDispatchWarehouse;
import com.greatonce.oms.domain.trade.DispatchOrder;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/24
 */
public interface ExpressValidatable {

  /**
   * 验证快递区域是否支持.
   */
  boolean validate(DispatchContext context, DispatchOrder dispatchOrder,
      StockDispatchWarehouse warehouse, Express express);
}
