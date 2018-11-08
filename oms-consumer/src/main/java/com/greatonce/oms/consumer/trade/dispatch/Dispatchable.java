package com.greatonce.oms.consumer.trade.dispatch;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/20
 */
public interface Dispatchable {

  /**
   * 配货
   */
  void dispatch(DispatchContext context);
}
