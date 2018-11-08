package com.greatonce.oms.consumer.trade.dispatch.impl;

import com.greatonce.oms.biz.trade.SalesOrderDetailService;
import com.greatonce.oms.biz.trade.SalesOrderService;
import com.greatonce.oms.consumer.trade.dispatch.DispatchOrderCondition;
import com.greatonce.oms.consumer.trade.dispatch.Mergeable;
import com.greatonce.oms.domain.trade.SalesOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 合单处理器
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/21
 */
@Component
@DispatchOrderCondition
public class Merger implements Mergeable {

  @Autowired
  private SalesOrderService salesOrderService;
  @Autowired
  private SalesOrderDetailService salesOrderDetailService;

  /**
   * 列出可合订单.
   */
  @Override
  public List<SalesOrder> listMergeableSalesOrder(SalesOrder salesOrder) {
    if (!salesOrder.getSub().isCod()) {
      List<Long> ids = salesOrderService.listMergeOrderId(salesOrder.getMergeFlag());
      if (ids.size() > 1) {
        List<SalesOrder> orders = new ArrayList<>(ids.size() - 1);
        for (Long id : ids) {
          if (!salesOrder.getSalesOrderId().equals(id)) {
            SalesOrder order = salesOrderService.getByKey(id);
            order.setDetails(salesOrderDetailService.listDispatchableBySalesOrderId(id));
            orders.add(order);
          }
        }
        return orders;
      }
    }
    return null;
  }
}
