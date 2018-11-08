package com.greatonce.oms.web.controller.purchase;

import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.biz.purchase.PurchaseReturnOrderDetailService;
import com.greatonce.oms.domain.purchase.PurchaseReturnOrder;
import com.greatonce.oms.domain.purchase.PurchaseReturnOrderDetail;
import com.greatonce.oms.query.purchase.PurchaseReturnOrderDetailQuery;
import com.greatonce.oms.web.controller.DetailPageCommonController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by wangc on 2017/11/14.
 */
@RestController
@RequestMapping("purchase/return/{id}/detail")
@CrossOrigin
public class PurchaseReturnOrderDetailController implements
    DetailPageCommonController<PurchaseReturnOrder, PurchaseReturnOrderDetail, PurchaseReturnOrderDetailQuery> {

  @Resource
  PurchaseReturnOrderDetailService purchaseReturnOrderDetailService;

  @Override
  public DetailService getBizService() {
    return purchaseReturnOrderDetailService;
  }
}
