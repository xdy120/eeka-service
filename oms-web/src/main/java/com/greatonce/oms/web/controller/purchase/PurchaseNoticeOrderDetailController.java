package com.greatonce.oms.web.controller.purchase;

import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.biz.purchase.PurchaseNoticeOrderDetailService;
import com.greatonce.oms.domain.purchase.PurchaseNoticeOrder;
import com.greatonce.oms.domain.purchase.PurchaseNoticeOrderDetail;
import com.greatonce.oms.query.purchase.PurchaseNoticeOrderDetailQuery;
import com.greatonce.oms.web.controller.DetailPageCommonController;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangc on 2017/11/14.
 */
@RestController
@RequestMapping("purchase/notice/{id}/detail")
@CrossOrigin
public class PurchaseNoticeOrderDetailController implements
    DetailPageCommonController<PurchaseNoticeOrder, PurchaseNoticeOrderDetail, PurchaseNoticeOrderDetailQuery> {

  @Resource
  PurchaseNoticeOrderDetailService purchaseNoticeOrderDetailService;

  @Override
  public DetailService<PurchaseNoticeOrder, PurchaseNoticeOrderDetail, PurchaseNoticeOrderDetailQuery> getBizService() {
    return purchaseNoticeOrderDetailService;
  }
}
