package com.greatonce.oms.biz.purchase;

import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.domain.purchase.PurchaseOrder;
import com.greatonce.oms.domain.purchase.PurchaseOrderDetail;
import com.greatonce.oms.query.purchase.PurchaseOrderDetailQuery;
import java.util.List;

/**
 * PurchaseOrderDetail <br/> 采购订单明细
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface PurchaseOrderDetailService extends
    DetailService<PurchaseOrder, PurchaseOrderDetail, PurchaseOrderDetailQuery> {

  /**
   * 打印查询
   */
  List<PurchaseOrderDetail> listDetailPrint(PurchaseOrderDetailQuery query);

  List<PurchaseOrderDetail> listDetails(Long purchaseOrderId);
}