package com.greatonce.oms.web.controller.purchase;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.purchase.PurchaseReturnOrderService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.purchase.PurchaseReturnOrderCancelBO;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.enums.purchase.PurchaseReturnStatus;
import com.greatonce.oms.domain.purchase.PurchaseReturnOrder;
import com.greatonce.oms.query.purchase.PurchaseReturnOrderQuery;
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
@RequestMapping("purchase/return")
@CrossOrigin
public class PurchaseReturnOrderController implements
    PageListController<PurchaseReturnOrder, PurchaseReturnOrderQuery> {

  @Resource
  PurchaseReturnOrderService purchaseReturnOrderService;

  @Override
  public BizService<PurchaseReturnOrder, PurchaseReturnOrderQuery> getBizService() {
    return purchaseReturnOrderService;
  }

  @PutMapping(path = "/{id}/audit")
  public void audit(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    PurchaseReturnOrder purchaseReturnOrder = purchaseReturnOrderService.getByKey(id);
    validateVersion(bo, purchaseReturnOrder);
    purchaseReturnOrderService.audit(purchaseReturnOrder, bo);
  }

  @PutMapping(path = "/{id}/finish")
  public void end(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    PurchaseReturnOrder purchaseReturnOrder = purchaseReturnOrderService.getByKey(id);
    validateVersion(bo, purchaseReturnOrder);
    purchaseReturnOrderService.finish(purchaseReturnOrder, bo);
  }

  @PutMapping(path = "/{id}/noticeWms")
  public void noticeWms(@PathVariable("id") Long purchaseReturnOrderId, @RequestBody VersionBO bo) {
    PurchaseReturnOrder purchaseReturnOrder = purchaseReturnOrderService
        .getByKey(purchaseReturnOrderId);
    purchaseReturnOrderService.noticeWms(purchaseReturnOrder, bo);
    if (purchaseReturnOrder.getStatus() == PurchaseReturnStatus.NOTICE_FAILED) {
      throw new OmsException("通知WMS失败！");
    }
  }

  @PutMapping(path = "/{id}/cancel")
  public void cancel(@PathVariable("id") Long purchaseReturnOrderId, @RequestBody PurchaseReturnOrderCancelBO bo) {
    PurchaseReturnOrder purchaseReturnOrder = purchaseReturnOrderService
        .getByKey(purchaseReturnOrderId);
    validateVersion(bo, purchaseReturnOrder);
    purchaseReturnOrderService.cancel(purchaseReturnOrder, bo);
  }
}
