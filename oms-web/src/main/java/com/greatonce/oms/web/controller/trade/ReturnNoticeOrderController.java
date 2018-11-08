package com.greatonce.oms.web.controller.trade;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.trade.ReturnNoticeOrderService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.enums.trade.ReturnNoticeOrderStatus;
import com.greatonce.oms.domain.trade.ReturnNoticeOrder;
import com.greatonce.oms.query.trade.ReturnNoticeOrderQuery;
import com.greatonce.oms.web.controller.PageListController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangc on 2017/12/28.
 */
@RestController
@RequestMapping(value = "/trade/return/notice")
@CrossOrigin
public class ReturnNoticeOrderController implements
    PageListController<ReturnNoticeOrder, ReturnNoticeOrderQuery> {

  @Autowired
  ReturnNoticeOrderService returnNoticeOrderService;

  @Override
  public BizService<ReturnNoticeOrder, ReturnNoticeOrderQuery> getBizService() {
    return returnNoticeOrderService;
  }


  @PutMapping(path = "/{returnNoticeOrderId}/cancel")
  public void cancel(@PathVariable("returnNoticeOrderId") Long returnNoticeOrderId,
      @RequestBody VersionBO bo) {
    ReturnNoticeOrder returnNoticeOrder = returnNoticeOrderService.getByKey(returnNoticeOrderId);
    returnNoticeOrderService.cancel(returnNoticeOrder, bo);
  }

  @PutMapping(path = "/{returnNoticeOrderId}/noticeWms")
  public void noticeWms(@PathVariable("returnNoticeOrderId") Long returnNoticeOrderId,
      @RequestBody VersionBO bo) {
    ReturnNoticeOrder returnNoticeOrder = returnNoticeOrderService.getByKey(returnNoticeOrderId);
    returnNoticeOrderService.noticeWms(returnNoticeOrder, bo);
    if (returnNoticeOrder.getStatus() == ReturnNoticeOrderStatus.NOTICE_FAILED) {
      throw new OmsException("通知WMS失败！");
    }
  }

  @GetMapping("exportNoticeOrder/{fileName}")
  public void exportNoticeOrder(@PathVariable("fileName") String fileName,
      ReturnNoticeOrderQuery query) {
    returnNoticeOrderService.exportNoticeOrder(fileName, query);
  }

  @PutMapping(path = "/{id}/reposting")
  public void reposting(@PathVariable("id") Long id) {
    ReturnNoticeOrder returnNoticeOrder = returnNoticeOrderService.getByKey(id);
    returnNoticeOrderService.reposting(returnNoticeOrder);
  }
}
