package com.greatonce.oms.bridge.wms;

import com.greatonce.oms.bridge.wms.request.StockOutOrderCreateRequest;
import com.greatonce.oms.domain.purchase.PurchaseReturnOrder;
import com.greatonce.oms.domain.stock.StockLoanOut;
import com.greatonce.oms.domain.stock.StockOutOrder;
import com.greatonce.oms.domain.vip.VipDispatch;

/**
 * 退货通知单过滤器.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-18
 */
public interface StockOutOrderFilter extends WmsFilter {

  void execute(StockOutOrderCreateRequest request, StockOutOrder stockOutOrder);

  void execute(StockOutOrderCreateRequest request, StockLoanOut stockLoanOut);

  void execute(StockOutOrderCreateRequest request, PurchaseReturnOrder purchaseReturnOrder);

  void execute(StockOutOrderCreateRequest request, VipDispatch vipDispatch);
}
