package com.greatonce.oms.web.controller.purchase;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.purchase.PurchaseOrderService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.domain.purchase.PurchaseOrder;
import com.greatonce.oms.query.purchase.PurchaseOrderQuery;
import com.greatonce.oms.web.controller.PageListController;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangc on 2017/11/14.
 */
@RestController
@RequestMapping("purchase/order")
@CrossOrigin
public class PurchaseOrderController implements
    PageListController<PurchaseOrder, PurchaseOrderQuery> {

  @Resource
  PurchaseOrderService purchaseOrderService;

  @Override
  public BizService getBizService() {
    return purchaseOrderService;
  }

  @RequestMapping(path = "/{id}/audit", method = RequestMethod.PUT)
  public void audit(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    PurchaseOrder purchaseOrder = purchaseOrderService.getByKey(id);
    validateVersion(bo, purchaseOrder);
    purchaseOrderService.audit(purchaseOrder, bo);
  }

  @RequestMapping(path = "/{id}/finish", method = RequestMethod.PUT)
  public void end(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    PurchaseOrder purchaseOrder = purchaseOrderService.getByKey(id);
    validateVersion(bo, purchaseOrder);
    purchaseOrderService.finish(purchaseOrder, bo);
  }

  @RequestMapping(path = "/{id}/invalid", method = RequestMethod.PUT)
  public void close(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    PurchaseOrder purchaseOrder = purchaseOrderService.getByKey(id);
    validateVersion(bo, purchaseOrder);
    purchaseOrderService.invalid(purchaseOrder, bo);
  }

}
