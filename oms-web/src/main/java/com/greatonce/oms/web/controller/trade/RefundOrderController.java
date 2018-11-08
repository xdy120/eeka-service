package com.greatonce.oms.web.controller.trade;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.trade.RefundOrderService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.domain.trade.RefundOrder;
import com.greatonce.oms.query.trade.RefundOrderQuery;
import com.greatonce.oms.web.controller.PageListController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author buer
 * @version 2017-12-08 9:45
 */
@RestController
@RequestMapping(value = "/trade/refund")
@CrossOrigin
public class RefundOrderController implements PageListController<RefundOrder, RefundOrderQuery> {

  @Autowired
  RefundOrderService refundOrderService;

  @Override
  public BizService<RefundOrder, RefundOrderQuery> getBizService() {
    return refundOrderService;
  }

  @RequestMapping(path = "/{id}/audit", method = RequestMethod.PUT)
  public void audit(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    RefundOrder refundOrder = refundOrderService.getByKey(id);
    refundOrderService.audit(refundOrder,bo);
  }

  @RequestMapping(path = "/{id}/invalid", method = RequestMethod.PUT)
  public void invalid(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    RefundOrder refundOrder = refundOrderService.getByKey(id);
    refundOrderService.invalid(refundOrder,bo);
  }

  @RequestMapping(path = "/{id}/review", method = RequestMethod.PUT)
  public void review(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    RefundOrder refundOrder = refundOrderService.getByKey(id);
    refundOrderService.review(refundOrder,bo);
  }
}
