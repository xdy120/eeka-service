package com.greatonce.oms.consumer.trade.dispatch;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/24
 */
public interface StockCalculable {

  /**
   * 申请库存
   */
  void take();

  /**
   * 释放库存
   */
  void release();
}
