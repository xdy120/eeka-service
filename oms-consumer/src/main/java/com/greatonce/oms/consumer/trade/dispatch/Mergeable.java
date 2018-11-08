package com.greatonce.oms.consumer.trade.dispatch;

import com.greatonce.oms.domain.trade.SalesOrder;

import java.util.List;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/20
 */
public interface Mergeable {

  /**
   * 获取可以合并的销售单
   *
   * @param salesOrder 主订单
   * @return 可以合并的订单列表（包括明细）
   */
  List<SalesOrder> listMergeableSalesOrder(SalesOrder salesOrder);
}
