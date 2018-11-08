package com.greatonce.oms.biz.purchase;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.trade.WmsAutoInBO;
import com.greatonce.oms.domain.purchase.PurchaseOrder;
import com.greatonce.oms.query.purchase.PurchaseOrderQuery;

/**
 * PurchaseOrder <br/> 采购订单
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface PurchaseOrderService extends
    BizService<PurchaseOrder, PurchaseOrderQuery> {

  /**
   * 审核采购单
   */
  void audit(PurchaseOrder purchaseOrder, VersionBO bo);

  /**
   * 完结采购单
   */
  void finish(PurchaseOrder purchaseOrder, VersionBO bo);

  /**
   * 取消采购单
   */
  void invalid(PurchaseOrder purchaseOrder, VersionBO bo);

  /**
   * 生成采购通知单
   */
  void createNotice(PurchaseOrder purchaseOrder, VersionBO bo);

  /**
   * 通知单入库.
   */
  void wmsAutoIn(PurchaseOrder purchaseOrder, WmsAutoInBO wmsAutoInBO);
}