package com.greatonce.oms.web.controller.trade;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.trade.RefundApplyOrderService;
import com.greatonce.oms.domain.trade.RefundApplyOrder;
import com.greatonce.oms.domain.trade.ReturnSign;
import com.greatonce.oms.query.trade.RefundApplyOrderQuery;
import com.greatonce.oms.web.controller.ControllerUtil;
import com.greatonce.oms.web.controller.PageListController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by wangc on 2018/1/10.
 */
@RestController
@RequestMapping(value = "/trade/refund/apply")
@CrossOrigin
public class RefundApplyOrderController implements
    PageListController<RefundApplyOrder, RefundApplyOrderQuery> {

  @Resource
  RefundApplyOrderService refundApplyOrderService;
  @Autowired
  StoreService storeService;
  @Autowired
  private ControllerUtil controllerUtil;

  @Override
  public BizService<RefundApplyOrder, RefundApplyOrderQuery> getBizService() {
    return refundApplyOrderService;
  }

  @RequestMapping(path = "{id}/invalid", method = RequestMethod.PUT)
  public void invalid(@PathVariable("id") Long id, @RequestBody ReturnSign returnSign) {
    //afterSaleApplyService.invalid(returnSign);
  }

  @Override
  @GetMapping
  public PageList<RefundApplyOrder> listPage(RefundApplyOrderQuery filter,
      @RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
    controllerUtil.addUserStoreIds(filter, RefundApplyOrderQuery::getStoreIds, filter::setStoreIds);
    return refundApplyOrderService.listPage(filter, page, pageSize);
  }

  @PostMapping(path = "/{refundId}/matchRefund")
  public void matchAbnormalApply(@PathVariable("refundId") Long refundId,@RequestBody RefundApplyOrder refundApplyOrder){
    refundApplyOrderService.matchAbnormalApply(refundApplyOrder);
  }
}
