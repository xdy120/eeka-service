package com.greatonce.oms.bridge.wms.impl;

import com.greatonce.oms.bridge.wms.StockInOrderFilter;
import com.greatonce.oms.bridge.wms.request.StockInOrderCreateRequest;
import com.greatonce.oms.domain.enums.WmsType;
import com.greatonce.oms.domain.purchase.PurchaseNoticeOrder;
import com.greatonce.oms.domain.stock.StockInOrder;
import com.greatonce.oms.domain.stock.StockLoanIn;
import com.greatonce.oms.domain.vip.VipReturnNotice;
import org.springframework.stereotype.Component;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-18
 */
@Component
public class StockInOrderFilterImpl implements StockInOrderFilter {

  @Override
  public void execute(StockInOrderCreateRequest request, StockInOrder stockInOrder) {

  }

  @Override
  public void execute(StockInOrderCreateRequest request, StockLoanIn stockLoanIn) {

  }

  @Override
  public void execute(StockInOrderCreateRequest request, PurchaseNoticeOrder purchaseNoticeOrder) {

  }

  @Override
  public void execute(StockInOrderCreateRequest request, VipReturnNotice vipReturnNotice) {

  }

  @Override
  public WmsType[] supports() {
    return new WmsType[]{WmsType.QIMEN};
  }
}
