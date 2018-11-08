package com.greatonce.oms.consumer.trade.dispatch;

import com.greatonce.oms.domain.trade.SalesOrder;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/24
 */
public interface Validatable {

  /**
   * 订单校验
   *
   * @param salesOrder 订单
   * @param context
   * @return 是否可以配货
   */
  boolean validate(SalesOrder salesOrder,
      DispatchContext context);
}
