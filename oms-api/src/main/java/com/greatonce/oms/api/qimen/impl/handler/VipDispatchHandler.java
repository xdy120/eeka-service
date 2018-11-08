package com.greatonce.oms.api.qimen.impl.handler;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.bridge.wms.qimen.request.OmsOrderProcessReportRequest;
import com.greatonce.oms.bridge.wms.qimen.request.OmsStockOutConfirmRequest;
import com.greatonce.oms.biz.vip.VipDispatchService;
import com.greatonce.oms.bo.trade.WmsAutoOutBO;
import com.greatonce.oms.domain.enums.OrderType;
import com.greatonce.oms.domain.vip.VipDispatch;
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
public class VipDispatchHandler extends AbstractOutOrderHandler implements StockOutHandler {

  @Autowired
  private VipDispatchService vipDispatchService;


  @Override
  public OrderType[] supports() {
    return new OrderType[]{OrderType.VIP_DISPATCH_ORDER};
  }

  @Override
  public void reportOrderProcess(OmsOrderProcessReportRequest request) {
    VipDispatch vipDispatch = vipDispatchService.getByCode(request.getOrderCode());
    log(vipDispatchService.getBizLogger(), vipDispatch.getVipDispatchId(), request);
  }

  @Override
  public void confirm(OmsStockOutConfirmRequest request) {
    VipDispatch vipDispatch = vipDispatchService.getByCode(request.getOrderCode());
    Assert.notNull(vipDispatch, "单据未找到:" + request.getOrderCode());
    WmsAutoOutBO bo = parseRequest(request);
    bo.setVersion(vipDispatch.getVersion());
    vipDispatchService.wmsAutoDelivery(vipDispatch, bo);
  }
}
