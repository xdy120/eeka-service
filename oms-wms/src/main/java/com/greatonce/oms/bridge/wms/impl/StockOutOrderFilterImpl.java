package com.greatonce.oms.bridge.wms.impl;

import com.greatonce.oms.bridge.wms.StockOutOrderFilter;
import com.greatonce.oms.bridge.wms.request.StockOutOrderCreateRequest;
import com.greatonce.oms.domain.enums.WmsType;
import com.greatonce.oms.domain.purchase.PurchaseReturnOrder;
import com.greatonce.oms.domain.stock.StockLoanOut;
import com.greatonce.oms.domain.stock.StockOutOrder;
import com.greatonce.oms.domain.vip.VipDispatch;
import org.springframework.stereotype.Component;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-18
 */
@Component
public class StockOutOrderFilterImpl implements StockOutOrderFilter {

  @Override
  public void execute(StockOutOrderCreateRequest request, StockOutOrder stockOutOrder) {

  }

  @Override
  public void execute(StockOutOrderCreateRequest request, StockLoanOut stockLoanOut) {

  }

  @Override
  public void execute(StockOutOrderCreateRequest request, PurchaseReturnOrder purchaseReturnOrder) {

  }

  @Override
  public void execute(StockOutOrderCreateRequest request, VipDispatch vipDispatch) {

  }

  @Override
  public WmsType[] supports() {
    return new WmsType[]{WmsType.QIMEN};
  }
}
