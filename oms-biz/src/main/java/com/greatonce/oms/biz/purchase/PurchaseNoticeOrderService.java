package com.greatonce.oms.biz.purchase;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.purchase.PurchaseNoticeOrderCancelBO;
import com.greatonce.oms.bo.trade.WmsAutoInBO;
import com.greatonce.oms.domain.purchase.PurchaseNoticeOrder;
import com.greatonce.oms.query.purchase.PurchaseNoticeOrderQuery;

/**
 * PurchaseNoticeOrder <br/> 采购入库通知单
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface PurchaseNoticeOrderService extends
    BizService<PurchaseNoticeOrder, PurchaseNoticeOrderQuery> {

  /**
   * 采购单通知wms.
   */
  void noticeWms(PurchaseNoticeOrder purchaseNoticeOrder, VersionBO bo);

  /**
   * 取消采购通知单.
   */
  void cancel(PurchaseNoticeOrder purchaseNoticeOrder, PurchaseNoticeOrderCancelBO bo);

  /**
   * 通过Code获取采购通知单.
   */
  PurchaseNoticeOrder getByCode(String orderCode);

  /**
   * 入库回传.
   */
  void wmsAutoIn(PurchaseNoticeOrder purchaseNoticeOrder, WmsAutoInBO bo);
}