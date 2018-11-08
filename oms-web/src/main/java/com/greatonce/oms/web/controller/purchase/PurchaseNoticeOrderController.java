package com.greatonce.oms.web.controller.purchase;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.purchase.PurchaseNoticeOrderService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.purchase.PurchaseNoticeOrderCancelBO;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.enums.purchase.PurchaseNoticeStatus;
import com.greatonce.oms.domain.purchase.PurchaseNoticeOrder;
import com.greatonce.oms.query.purchase.PurchaseNoticeOrderQuery;
import com.greatonce.oms.web.controller.PageListController;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangc on 2017/11/14.
 */
@RestController
@RequestMapping("purchase/notice")
@CrossOrigin
public class PurchaseNoticeOrderController implements
    PageListController<PurchaseNoticeOrder, PurchaseNoticeOrderQuery> {

  @Resource
  PurchaseNoticeOrderService purchaseNoticeOrderService;

  @Override
  public BizService getBizService() {
    return purchaseNoticeOrderService;
  }

  @PutMapping(path = "/{id}/noticeWms")
  public void noticeWms(@PathVariable("id") Long purchaseNoticeId, @RequestBody VersionBO bo) {
    PurchaseNoticeOrder purchaseNoticeOrder = purchaseNoticeOrderService.getByKey(purchaseNoticeId);
    purchaseNoticeOrderService.noticeWms(purchaseNoticeOrder, bo);
    if (purchaseNoticeOrder.getStatus() == PurchaseNoticeStatus.NOTICE_FAILED) {
      throw new OmsException("通知WMS失败！");
    }
  }

  @PutMapping(path = "/{id}/cancel")
  public void cancel(@PathVariable("id") Long purchaseNoticeId,
      @RequestBody PurchaseNoticeOrderCancelBO bo) {
    PurchaseNoticeOrder purchaseNoticeOrder = purchaseNoticeOrderService.getByKey(purchaseNoticeId);
    validateVersion(bo, purchaseNoticeOrder);
    purchaseNoticeOrderService.cancel(purchaseNoticeOrder, bo);
  }
}
