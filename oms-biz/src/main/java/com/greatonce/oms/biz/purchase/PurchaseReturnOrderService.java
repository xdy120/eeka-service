package com.greatonce.oms.biz.purchase;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.purchase.PurchaseReturnOrderCancelBO;
import com.greatonce.oms.bo.trade.WmsAutoOutBO;
import com.greatonce.oms.domain.purchase.PurchaseReturnOrder;
import com.greatonce.oms.query.purchase.PurchaseReturnOrderQuery;

/**
 * PurchaseReturnOrder <br/> 采购退货单
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface PurchaseReturnOrderService extends
    BizService<PurchaseReturnOrder, PurchaseReturnOrderQuery> {

  /**
   * 审核采购退货单
   */
  void audit(PurchaseReturnOrder purchaseReturnOrder, VersionBO versionBO);

  /**
   * 完结采购退货单
   */
  void finish(PurchaseReturnOrder purchaseReturnOrder, VersionBO bo);

  /**
   * 采购单退货单通知wms
   */
  void noticeWms(PurchaseReturnOrder purchaseReturnOrder, VersionBO bo);

  /**
   * 取消采购退货单
   */
  void cancel(PurchaseReturnOrder purchaseReturnOrder, PurchaseReturnOrderCancelBO bo);

  /**
   * 通过Code获取采购通知单
   */
  PurchaseReturnOrder getByCode(String orderCode);

  /**
   * 采购退货单出库
   */
  void wmsAutoOut(PurchaseReturnOrder purchaseReturnOrder, WmsAutoOutBO wmsAutoOutBO);

}