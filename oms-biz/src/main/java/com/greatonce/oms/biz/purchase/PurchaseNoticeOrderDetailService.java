package com.greatonce.oms.biz.purchase;

import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.domain.purchase.PurchaseNoticeOrder;
import com.greatonce.oms.domain.purchase.PurchaseNoticeOrderDetail;
import com.greatonce.oms.query.purchase.PurchaseNoticeOrderDetailQuery;
import java.util.List;

/**
 * PurchaseNoticeOrderDetail <br/>
 * 采购入库通知单明细
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */
public interface PurchaseNoticeOrderDetailService extends DetailService<PurchaseNoticeOrder,PurchaseNoticeOrderDetail,PurchaseNoticeOrderDetailQuery> {

  List<PurchaseNoticeOrderDetail> listDetails(Long purchaseNoticeOrderId);
}