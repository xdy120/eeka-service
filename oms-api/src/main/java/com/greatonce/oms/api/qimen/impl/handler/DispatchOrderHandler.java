package com.greatonce.oms.api.qimen.impl.handler;

import com.greatonce.oms.bridge.wms.qimen.request.OmsOrderProcessReportRequest;
import com.greatonce.oms.biz.trade.DispatchOrderService;
import com.greatonce.oms.domain.enums.OrderType;
import com.greatonce.oms.domain.trade.DispatchOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 配货单.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-07-07
 */
@Component
public class DispatchOrderHandler extends AbstractOrderHandler {

  @Autowired
  private DispatchOrderService dispatchOrderService;

  @Override
  public OrderType[] supports() {
    return new OrderType[]{OrderType.B2C_DISPATCH_ORDER};
  }

  @Override
  public void reportOrderProcess(OmsOrderProcessReportRequest request) {
    DispatchOrder dispatchOrder = dispatchOrderService.getByCode(request.getOrderCode());
    log(dispatchOrderService.getBizLogger(), dispatchOrder.getDispatchOrderId(), request);
  }
}
