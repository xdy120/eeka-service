package com.greatonce.oms.biz.purchase;

import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.domain.purchase.PurchaseReturnOrder;
import com.greatonce.oms.domain.purchase.PurchaseReturnOrderDetail;
import com.greatonce.oms.query.purchase.PurchaseReturnOrderDetailQuery;
import com.greatonce.oms.query.purchase.PurchaseReturnOrderQuery;
import java.util.List;

/**
 * PurchaseReturnOrderDetail <br/> 采购退货明细
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface PurchaseReturnOrderDetailService extends
    DetailService<PurchaseReturnOrder, PurchaseReturnOrderDetail, PurchaseReturnOrderDetailQuery> {

  List<PurchaseReturnOrderDetail> listAvailable(Long purchaseReturnOrderId);

  List<PurchaseReturnOrderDetail> listSaleable(Long purchaseReturnOrderId);

  List<PurchaseReturnOrderDetail> listDetails(Long purchaseReturnOrderId);
}