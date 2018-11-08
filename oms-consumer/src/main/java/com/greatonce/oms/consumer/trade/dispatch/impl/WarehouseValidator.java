package com.greatonce.oms.consumer.trade.dispatch.impl;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.base.WarehouseRegionService;
import com.greatonce.oms.biz.trade.SalesOrderDetailService;
import com.greatonce.oms.consumer.trade.dispatch.DispatchContext;
import com.greatonce.oms.consumer.trade.dispatch.DispatchOrderCondition;
import com.greatonce.oms.consumer.trade.dispatch.WarehouseValidatable;
import com.greatonce.oms.consumer.trade.dispatch.wrapper.DispatchOrderDetailWrapper;
import com.greatonce.oms.consumer.trade.dispatch.wrapper.DispatchOrderWrapper;
import com.greatonce.oms.domain.base.StockDispatchWarehouse;
import com.greatonce.oms.domain.enums.PrepackageType;
import com.greatonce.oms.domain.enums.WarehouseDispatchType;
import com.greatonce.oms.domain.trade.DispatchOrderDetail;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import com.greatonce.oms.util.logging.DispatchLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 仓库范围校验
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/24
 */
@Component
@DispatchOrderCondition
public class WarehouseValidator implements WarehouseValidatable {

  private static final DispatchLogger LOGGER = OmsLoggerFactory.getDispatchLogger();
  @Autowired
  private WarehouseRegionService warehouseRegionService;
  @Autowired
  private SalesOrderDetailService salesOrderDetailService;
  @Autowired
  private ExpressMatcher expressMatcher;

  /**
   * 校验仓库. 逻辑：
   * 1.校验库存配货策略仓库配货类型，如果是一单一货，则配货单只能有一条明细
   * 2.支持预包装的仓库，要求配货单只包含一件预包装明细
   * 3.配货单第一次校验，如果订单本身设置了推荐快递，则校验此推荐快递
   * 4.策略仓库所有快递都校验失败，则仓库校验失败
   */
  @Override
  public boolean validate(DispatchContext context, DispatchOrderWrapper dispatchOrder,
      StockDispatchWarehouse stockDispatchWarehouse) {
    SalesOrder mainSalesOrder = context.getMainSalesOrder();

    if (stockDispatchWarehouse.getDispatchType() == WarehouseDispatchType.SINGLE) {
      if (!singleWarehouseCheck(dispatchOrder)) {
        LOGGER.info(context.getSerialNo(), context.getStore(), mainSalesOrder.getSalesOrderCode(),
            mainSalesOrder.getTradeId(),
            "仓库校验失败，原因：{}仓库配货类型为一单一货，但配货单非一单一货",
            stockDispatchWarehouse.getVirtualWarehouseName());
        return false;
      }
    }
    //预包装校验
    if (stockDispatchWarehouse.getPrepackageType() != PrepackageType.ALL) {
      if (!prepackageCheck(dispatchOrder)) {
        LOGGER.info(context.getSerialNo(), context.getStore(), mainSalesOrder.getSalesOrderCode(),
            mainSalesOrder.getTradeId(),
            "仓库校验失败，原因：{}仓库只支持预包装发货",
            stockDispatchWarehouse.getVirtualWarehouseName());
        return false;
      }
    }
    //TODO 范围校验&日志
    // warehouseRegionService.validate(warehouseId, dispatchOrder.getCountryId(), dispatchOrder.getProvinceId(), dispatchOrder.getCityId(), dispatchOrder.getDistrictId())

    if (context.isHasDesignatedExpress()) {
      if (stockDispatchWarehouse.getExpressStrategy().getRule().getExpresses().stream()
          .noneMatch(x -> x.getExpressId().equals(dispatchOrder.getSuggestExpressId()))
          && (context.getDispatchStrategy().getDefaultExpressId() == null || !context
          .getDispatchStrategy().getDefaultExpressId()
          .equals(dispatchOrder.getSuggestExpressId()))) {
        LOGGER.info(context.getSerialNo(), context.getStore(), mainSalesOrder.getSalesOrderCode(),
            mainSalesOrder.getTradeId(), "仓库校验失败，原因：{}仓库快递策略中不包含指定快递{}",
            stockDispatchWarehouse.getVirtualWarehouseName(),
            dispatchOrder.getSuggestExpressName());
        return false;
      }
    } else {
      //快递范围校验
      expressMatcher.matchExpress(context, dispatchOrder, stockDispatchWarehouse);
      if (Assert.isEmpty(stockDispatchWarehouse.getExpressStrategy().getRule().getExpresses())) {
        LOGGER.info(context.getSerialNo(), context.getStore(), mainSalesOrder.getSalesOrderCode(),
            mainSalesOrder.getTradeId(), "仓库校验失败，原因：{}仓库快递全部校验不通过",
            stockDispatchWarehouse.getVirtualWarehouseName());
        return false;
      }
    }
    return true;
  }

  /**
   * 仓库一单一货校验.
   * 1.明细只有1条，判断明细的数量
   * 2.明细大于1条，判断是否只有一套套装
   */
  private boolean singleWarehouseCheck(DispatchOrderWrapper dispatchOrder) {
    if (dispatchOrder.getDetails().size() == 1) {
      DispatchOrderDetail detail = dispatchOrder.getDetails().get(0);
      return detail.getNoticeQuantity() == 1;
    } else {
      List<Long> originIds = new ArrayList<>(dispatchOrder.getDetails().size());
      for (DispatchOrderDetail dispatchOrderDetail : dispatchOrder.getDetails()) {
        DispatchOrderDetailWrapper detail = (DispatchOrderDetailWrapper) dispatchOrderDetail;
        SalesOrderDetail salesOrderDetail = detail.getSalesOrderDetail();
        if (salesOrderDetail.getOriginalDetailid() == null) {
          return false;
        }
        if (!originIds.contains(salesOrderDetail.getOriginalDetailid())) {
          originIds.add(salesOrderDetail.getOriginalDetailid());
        }
      }
      if (originIds.size() > 1) {
        return false;
      }
      SalesOrderDetail detail = salesOrderDetailService.getByKey(originIds.get(0));
      return detail.getQuantity() == 1;
    }
  }

  /**
   * 预包装校验
   */
  private boolean prepackageCheck(DispatchOrderWrapper dispatchOrder) {
    for (DispatchOrderDetail dispatchOrderDetail : dispatchOrder.getDetails()) {
      DispatchOrderDetailWrapper detail = (DispatchOrderDetailWrapper) dispatchOrderDetail;
      SalesOrderDetail salesOrderDetail = detail.getSalesOrderDetail();
      if (!Assert.isTrue(salesOrderDetail.isPrepackage())) {
        return false;
      }
    }
    return true;
  }
}
