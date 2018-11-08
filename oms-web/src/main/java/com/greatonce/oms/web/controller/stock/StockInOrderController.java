package com.greatonce.oms.web.controller.stock;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.stock.StockInOrderService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.stock.StockInOrderCancelBO;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.enums.stock.StockInOrderStatus;
import com.greatonce.oms.domain.stock.StockInOrder;
import com.greatonce.oms.query.stock.StockInOrderQuery;
import com.greatonce.oms.web.controller.PageListController;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangc on 2018/1/22.
 */
@RestController
@RequestMapping(value = "/stock/in")
@CrossOrigin
public class StockInOrderController implements PageListController<StockInOrder, StockInOrderQuery> {

  @Resource
  StockInOrderService stockInOrderService;

  @Override
  public BizService<StockInOrder, StockInOrderQuery> getBizService() {
    return stockInOrderService;
  }

  @PutMapping("/{id}/audit")
  public void audit(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    StockInOrder stockInOrder = stockInOrderService.getByKey(id);
    validateVersion(bo, stockInOrder);
    stockInOrderService.audit(stockInOrder, bo);
  }

  @PutMapping("/{id}/cancel")
  public void invalid(@PathVariable("id") Long id, @RequestBody StockInOrderCancelBO bo) {
    StockInOrder stockInOrder = stockInOrderService.getByKey(id);
    validateVersion(bo, stockInOrder);
    stockInOrderService.cancel(stockInOrder, bo);
  }

  @PutMapping("{id}/noticeWms")
  public void noticeWms(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    StockInOrder stockInOrder = stockInOrderService.getByKey(id);
    stockInOrderService.noticeWms(stockInOrder, bo);
    if (stockInOrder.getStatus() == StockInOrderStatus.NOTICE_FAILED) {
      throw new OmsException("通知WMS失败！");
    }
  }
}
