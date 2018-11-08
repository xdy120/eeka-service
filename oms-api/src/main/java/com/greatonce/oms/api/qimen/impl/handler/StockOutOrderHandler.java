package com.greatonce.oms.api.qimen.impl.handler;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.bridge.wms.qimen.request.OmsOrderProcessReportRequest;
import com.greatonce.oms.bridge.wms.qimen.request.OmsStockOutConfirmRequest;
import com.greatonce.oms.biz.stock.StockOutOrderService;
import com.greatonce.oms.bo.trade.WmsAutoOutBO;
import com.greatonce.oms.domain.enums.OrderType;
import com.greatonce.oms.domain.stock.StockOutOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 出库单.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-06-27
 */
@Component
public class StockOutOrderHandler extends AbstractOutOrderHandler implements StockOutHandler {

  @Autowired
  private StockOutOrderService stockOutOrderService;

  @Override
  public OrderType[] supports() {
    return new OrderType[]{OrderType.OUT_ORDER};
  }

  @Override
  public void reportOrderProcess(OmsOrderProcessReportRequest request) {
    StockOutOrder stockOutOrder = stockOutOrderService.getByCode(request.getOrderCode());
    log(stockOutOrderService.getBizLogger(), stockOutOrder.getStockOutOrderId(), request);
  }

  @Override
  public void confirm(OmsStockOutConfirmRequest request) {
    StockOutOrder stockOutOrder = stockOutOrderService.getByCode(request.getOrderCode());
    Assert.notNull(stockOutOrder, "单据未找到:" + request.getOrderCode());
    WmsAutoOutBO bo = parseRequest(request);
    bo.setVersion(stockOutOrder.getVersion());
    stockOutOrderService.wmsAutoOut(stockOutOrder, bo);
  }
}
