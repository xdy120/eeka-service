package com.greatonce.oms.web.controller.purchase;

import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.biz.purchase.PurchaseOrderDetailService;
import com.greatonce.oms.domain.purchase.PurchaseOrder;
import com.greatonce.oms.domain.purchase.PurchaseOrderDetail;
import com.greatonce.oms.query.purchase.PurchaseOrderDetailQuery;
import com.greatonce.oms.web.controller.DetailPageCommonController;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangc on 2017/11/25.
 */
@RestController
@RequestMapping("purchase/order/{id}/detail")
@CrossOrigin
public class PurchaseOrderDetailController implements
    DetailPageCommonController<PurchaseOrder, PurchaseOrderDetail, PurchaseOrderDetailQuery> {

  @Resource
  PurchaseOrderDetailService purchaseOrderDetailService;

  @Override
  public DetailService getBizService() {
    return purchaseOrderDetailService;
  }

  @GetMapping(path = "/detailPrint")
  public List<PurchaseOrderDetail> listDetailPrint(@PathVariable("id") Long id) {
    PurchaseOrderDetailQuery query = new PurchaseOrderDetailQuery();
    query.setPurchaseOrderId(id);
    return purchaseOrderDetailService.listDetailPrint(query);
  }
}
