package com.greatonce.oms.consumer.trade.dispatch;

import com.greatonce.oms.consumer.trade.dispatch.wrapper.DispatchOrderWrapper;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/24
 */
public interface DispatchOrderBuildable {

  /**
   * 生成配货单
   */
  DispatchOrderWrapper build(DispatchContext context);
}
