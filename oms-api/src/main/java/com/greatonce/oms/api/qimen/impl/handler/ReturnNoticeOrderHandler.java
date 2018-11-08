package com.greatonce.oms.api.qimen.impl.handler;

import com.greatonce.oms.bridge.wms.qimen.request.OmsOrderProcessReportRequest;
import com.greatonce.oms.biz.trade.ReturnNoticeOrderService;
import com.greatonce.oms.domain.enums.OrderType;
import com.greatonce.oms.domain.trade.ReturnNoticeOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 退货通知单.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-07-07
 */
@Component
public class ReturnNoticeOrderHandler extends AbstractOrderHandler{

  @Autowired
  private ReturnNoticeOrderService returnNoticeOrderService;

  @Override
  public OrderType[] supports() {
    return new OrderType[]{OrderType.B2C_RETURN_NOTICE_ORDER};
  }

  @Override
  public void reportOrderProcess(OmsOrderProcessReportRequest request) {
    ReturnNoticeOrder returnNoticeOrder = returnNoticeOrderService
        .getByCode(request.getOrderCode());
    log(returnNoticeOrderService.getBizLogger(), returnNoticeOrder.getReturnNoticeOrderId(),
        request);
  }
}
