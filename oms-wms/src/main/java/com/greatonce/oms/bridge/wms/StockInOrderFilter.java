package com.greatonce.oms.bridge.wms;

import com.greatonce.oms.bridge.wms.request.StockInOrderCreateRequest;
import com.greatonce.oms.domain.purchase.PurchaseNoticeOrder;
import com.greatonce.oms.domain.stock.StockInOrder;
import com.greatonce.oms.domain.stock.StockLoanIn;
import com.greatonce.oms.domain.vip.VipReturnNotice;

/**
 * 退货通知单过滤器.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-18
 */
public interface StockInOrderFilter extends WmsFilter {

  void execute(StockInOrderCreateRequest request, StockInOrder stockInOrder);

  void execute(StockInOrderCreateRequest request, StockLoanIn stockLoanIn);

  void execute(StockInOrderCreateRequest request, PurchaseNoticeOrder purchaseNoticeOrder);

  void execute(StockInOrderCreateRequest request, VipReturnNotice vipReturnNotice);
}
