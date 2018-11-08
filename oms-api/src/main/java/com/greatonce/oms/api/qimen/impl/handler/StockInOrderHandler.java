package com.greatonce.oms.api.qimen.impl.handler;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.bridge.wms.qimen.request.OmsEntryOrderConfirmRequest;
import com.greatonce.oms.bridge.wms.qimen.request.OmsOrderProcessReportRequest;
import com.greatonce.oms.biz.stock.StockInOrderService;
import com.greatonce.oms.bo.trade.WmsAutoInBO;
import com.greatonce.oms.domain.enums.OrderType;
import com.greatonce.oms.domain.stock.StockInOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 借调还入单入库
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-06-27
 */
@Component
public class StockInOrderHandler extends AbstractInOrderHandler implements StockInHandler {

  @Autowired
  private StockInOrderService stockInOrderService;


  @Override
  public OrderType[] supports() {
    return new OrderType[]{OrderType.IN_ORDER};
  }

  @Override
  public void reportOrderProcess(OmsOrderProcessReportRequest request) {
    StockInOrder stockInOrder = stockInOrderService.getByCode(request.getOrderCode());
    log(stockInOrderService.getBizLogger(), stockInOrder.getStockInOrderId(), request);
  }

  @Override
  public void confirm(OmsEntryOrderConfirmRequest request) {
    StockInOrder stockInOrder = stockInOrderService.getByCode(request.getOrderCode());
    Assert.notNull(stockInOrder, "单据未找到:" + request.getOrderCode());
    WmsAutoInBO bo = parseRequest(request);
    bo.setVersion(stockInOrder.getVersion());
    stockInOrderService.wmsAutoIn(stockInOrder, bo);
  }
}
