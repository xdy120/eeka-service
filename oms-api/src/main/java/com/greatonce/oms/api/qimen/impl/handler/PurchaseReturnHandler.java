package com.greatonce.oms.api.qimen.impl.handler;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.bridge.wms.qimen.request.OmsOrderProcessReportRequest;
import com.greatonce.oms.bridge.wms.qimen.request.OmsStockOutConfirmRequest;
import com.greatonce.oms.biz.purchase.PurchaseReturnOrderService;
import com.greatonce.oms.bo.trade.WmsAutoOutBO;
import com.greatonce.oms.domain.enums.OrderType;
import com.greatonce.oms.domain.purchase.PurchaseReturnOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 唯品配货单处理器.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-06-27
 */
@Component
public class PurchaseReturnHandler extends AbstractOutOrderHandler implements StockOutHandler {

  @Autowired
  private PurchaseReturnOrderService purchaseReturnOrderService;


  @Override
  public OrderType[] supports() {
    return new OrderType[]{OrderType.PURCHASE_RETURN_ORDER};
  }

  @Override
  public void reportOrderProcess(OmsOrderProcessReportRequest request) {
    PurchaseReturnOrder purchaseReturnOrder = purchaseReturnOrderService
        .getByCode(request.getOrderCode());
    log(purchaseReturnOrderService.getBizLogger(), purchaseReturnOrder.getPurchaseOrderId(),
        request);
  }

  @Override
  public void confirm(OmsStockOutConfirmRequest request) {
    PurchaseReturnOrder purchaseReturnOrder = purchaseReturnOrderService
        .getByCode(request.getOrderCode());
    Assert.notNull(purchaseReturnOrder, "单据未找到:" + request.getOrderCode());
    WmsAutoOutBO bo = parseRequest(request);
    bo.setVersion(purchaseReturnOrder.getVersion());
    purchaseReturnOrderService.wmsAutoOut(purchaseReturnOrder, bo);
  }
}
