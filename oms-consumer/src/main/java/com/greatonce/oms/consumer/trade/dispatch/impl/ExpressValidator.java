package com.greatonce.oms.consumer.trade.dispatch.impl;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.base.ExpressRegionService;
import com.greatonce.oms.consumer.trade.dispatch.DispatchContext;
import com.greatonce.oms.consumer.trade.dispatch.DispatchOrderCondition;
import com.greatonce.oms.consumer.trade.dispatch.ExpressValidatable;
import com.greatonce.oms.domain.base.Express;
import com.greatonce.oms.domain.base.ExpressRegion;
import com.greatonce.oms.domain.base.StockDispatchWarehouse;
import com.greatonce.oms.domain.enums.CodType;
import com.greatonce.oms.domain.trade.DispatchOrder;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.util.logging.DispatchLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/24
 */
@Component
@DispatchOrderCondition
public class ExpressValidator implements ExpressValidatable {

  private static final DispatchLogger LOGGER = OmsLoggerFactory.getDispatchLogger();
  @Autowired
  private ExpressRegionService expressRegionService;

  /**
   * 根据快递信息进行快递校验.
   *
   * 校验逻辑：
   * 1.校验快递的存在性。
   * 2.校验快递是否启用。
   * 3.如果配货单为到付则校验到付。
   * 4.校验快递范围。
   */
  @Override
  public boolean validate(DispatchContext context,
      DispatchOrder dispatchOrder, StockDispatchWarehouse warehouse, Express express) {
    SalesOrder mainSalesOrder = context.getMainSalesOrder();
    if (express == null) {
      LOGGER.info(context.getSerialNo(), context.getStore(), mainSalesOrder.getSalesOrderCode(),
          mainSalesOrder.getTradeId(), "{}仓库的快递校验失败，配货策略中快递不存在！",
          warehouse.getVirtualWarehouseName());
      return false;
    }
    if (!Assert.isTrue(express.isEnable())) {
      LOGGER.info(context.getSerialNo(), context.getStore(), mainSalesOrder.getSalesOrderCode(),
          mainSalesOrder.getTradeId(), "{}仓库的快递{}校验失败，快递未启用！",
          warehouse.getVirtualWarehouseName(), express.getExpressName());
      return false;
    }
    if (Assert.isTrue(dispatchOrder.isCod()) && express.getCodType() == CodType.NO_COD) {
      LOGGER.info(context.getSerialNo(), context.getStore(), mainSalesOrder.getSalesOrderCode(),
          mainSalesOrder.getTradeId(), "{}仓库的快递{}校验失败，此配货单为到付单，快递{}不支持到付！",
          warehouse.getVirtualWarehouseName(), express.getExpressName());
      return false;
    }
    if (Assert.isTrue(express.isCheckRegion())) {
      Long districtId = dispatchOrder.getDistrictId();
      ExpressRegion region = expressRegionService
          .getByRegionIdAndExpressId(districtId, express.getExpressId());
      if (region == null) {
        LOGGER.info(context.getSerialNo(), context.getStore(), mainSalesOrder.getSalesOrderCode(),
            mainSalesOrder.getTradeId(), "{}仓库的快递{}校验失败，地址不在快递配送范围内！",
            warehouse.getVirtualWarehouseName(), express.getExpressName());
        return false;
      }
    }
    return true;
  }
}
