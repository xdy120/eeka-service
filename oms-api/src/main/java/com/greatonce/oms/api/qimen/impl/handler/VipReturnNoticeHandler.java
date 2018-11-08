package com.greatonce.oms.api.qimen.impl.handler;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.bridge.wms.qimen.request.OmsEntryOrderConfirmRequest;
import com.greatonce.oms.bridge.wms.qimen.request.OmsOrderProcessReportRequest;
import com.greatonce.oms.biz.vip.VipReturnNoticeDetailService;
import com.greatonce.oms.biz.vip.VipReturnNoticeService;
import com.greatonce.oms.bo.trade.WmsAutoInBO;
import com.greatonce.oms.domain.enums.OrderType;
import com.greatonce.oms.domain.vip.VipReturnNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 唯品退货通知单.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-06-27
 */
@Component
public class VipReturnNoticeHandler extends AbstractInOrderHandler implements StockInHandler {

  @Autowired
  private VipReturnNoticeService vipReturnNoticeService;
  @Autowired
  private VipReturnNoticeDetailService vipReturnNoticeDetailService;

  @Override
  public OrderType[] supports() {
    return new OrderType[]{OrderType.VIP_RETURN_NOTICE_ORDER};
  }

  @Override
  public void reportOrderProcess(OmsOrderProcessReportRequest request) {
    VipReturnNotice vipReturnNotice = vipReturnNoticeService.getByCode(request.getOrderCode());
    log(vipReturnNoticeService.getBizLogger(), vipReturnNotice.getVipReturnNoticeId(), request);
  }

  @Override
  public void confirm(OmsEntryOrderConfirmRequest request) {
    VipReturnNotice vipReturnNotice = vipReturnNoticeService.getByCode(request.getOrderCode());
    Assert.notNull(vipReturnNotice, "单据未找到:" + request.getOrderCode());
    WmsAutoInBO bo = parseRequest(request);
    bo.setVersion(vipReturnNotice.getVersion());
    vipReturnNoticeService.wmsAutoIn(vipReturnNotice, bo);
  }
}
