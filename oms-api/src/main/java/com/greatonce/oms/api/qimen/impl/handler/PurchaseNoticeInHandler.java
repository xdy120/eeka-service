package com.greatonce.oms.api.qimen.impl.handler;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.bridge.wms.qimen.request.OmsEntryOrderConfirmRequest;
import com.greatonce.oms.bridge.wms.qimen.request.OmsOrderProcessReportRequest;
import com.greatonce.oms.biz.purchase.PurchaseNoticeOrderService;
import com.greatonce.oms.bo.trade.WmsAutoInBO;
import com.greatonce.oms.domain.enums.OrderType;
import com.greatonce.oms.domain.purchase.PurchaseNoticeOrder;
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
public class PurchaseNoticeInHandler extends AbstractInOrderHandler implements StockInHandler {

  @Autowired
  private PurchaseNoticeOrderService purchaseNoticeOrderService;

  @Override
  public OrderType[] supports() {
    return new OrderType[]{OrderType.PURCHASE_NOTICE_ORDER};
  }

  @Override
  public void reportOrderProcess(OmsOrderProcessReportRequest request) {
    PurchaseNoticeOrder purchaseNoticeOrder = purchaseNoticeOrderService
        .getByCode(request.getOrderCode());
    log(purchaseNoticeOrderService.getBizLogger(), purchaseNoticeOrder.getPurchaseNoticeOrderId(),
        request);
  }

  @Override
  public void confirm(OmsEntryOrderConfirmRequest request) {
    PurchaseNoticeOrder purchaseNoticeOrder = purchaseNoticeOrderService
        .getByCode(request.getOrderCode());
    Assert.notNull(purchaseNoticeOrder, "单据未找到:" + request.getOrderCode());
    Assert.notNull(request.getOrderLines(), "入库明细不能为空");
    WmsAutoInBO bo = parseRequest(request);
    bo.setVersion(purchaseNoticeOrder.getVersion());
    purchaseNoticeOrderService.wmsAutoIn(purchaseNoticeOrder, bo);
  }
}
