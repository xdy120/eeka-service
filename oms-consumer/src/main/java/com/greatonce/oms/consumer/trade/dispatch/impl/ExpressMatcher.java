package com.greatonce.oms.consumer.trade.dispatch.impl;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.base.ExpressService;
import com.greatonce.oms.consumer.trade.dispatch.DispatchContext;
import com.greatonce.oms.consumer.trade.dispatch.DispatchOrderCondition;
import com.greatonce.oms.consumer.trade.dispatch.ExpressMatchable;
import com.greatonce.oms.domain.base.Express;
import com.greatonce.oms.domain.base.ExpressStrategyRule.StrategyExpress;
import com.greatonce.oms.domain.base.StockDispatchWarehouse;
import com.greatonce.oms.domain.trade.DispatchOrder;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.util.logging.DispatchLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 快递匹配器
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/20
 */
@Component
@DispatchOrderCondition
public class ExpressMatcher implements ExpressMatchable {

  private static final DispatchLogger LOGGER = OmsLoggerFactory.getDispatchLogger();
  @Autowired
  private ExpressValidator expressValidator;
  @Autowired
  private ExpressService expressService;

  /**
   * 匹配快递.
   */
  @Override
  public void matchExpress(DispatchContext context,
      DispatchOrder dispatchOrder, StockDispatchWarehouse warehouse) {
    SalesOrder mainSalesOrder = context.getMainSalesOrder();
    List<StrategyExpress> expresses = warehouse.getExpressStrategy().getRule().getExpresses();
    if (Assert.isEmpty(expresses)) {
      LOGGER.info(context.getSerialNo(), context.getStore(), mainSalesOrder.getSalesOrderCode(),
          mainSalesOrder.getTradeId(), "快递匹配失败，策略异常！{}仓库中没有快递",
          warehouse.getVirtualWarehouseName());
      return;
    }

    Express express;
    StrategyExpress strategyExpress;
    Iterator<StrategyExpress> iterator = expresses.iterator();
    while (iterator.hasNext()) {
      strategyExpress = iterator.next();
      express = expressService.getByKey(strategyExpress.getExpressId());
      if (!expressValidator.validate(context, dispatchOrder, warehouse, express)) {
        iterator.remove();
      }
    }
    //快递都不到如果有默认快递则选择默认快递，不进行校验
    if (Assert.isEmpty(expresses) && context.getDispatchStrategy().getDefaultExpressId() != null) {
      StrategyExpress defaultExpress = new StrategyExpress();
      defaultExpress.setExpressId(context.getDispatchStrategy().getDefaultExpressId());
      defaultExpress.setExpressName(context.getDispatchStrategy().getDefaultExpressName());
      defaultExpress.setPriorityNo(1);
      expresses.add(defaultExpress);
      LOGGER.info(context.getSerialNo(), context.getStore(), mainSalesOrder.getSalesOrderCode(),
          mainSalesOrder.getTradeId(), "{}仓库使用默认快递{}",
          warehouse.getVirtualWarehouseName(), defaultExpress.getExpressName());
    }
  }
}
