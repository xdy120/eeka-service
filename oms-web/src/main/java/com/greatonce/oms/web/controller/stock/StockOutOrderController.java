package com.greatonce.oms.web.controller.stock;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.stock.StockOutOrderService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.stock.StockOutOrderCancelBO;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.enums.stock.StockOutOrderStatus;
import com.greatonce.oms.domain.stock.StockOutOrder;
import com.greatonce.oms.query.stock.StockOutOrderQuery;
import com.greatonce.oms.web.controller.PageListController;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangc on 2018/1/22.
 */
@RestController
@RequestMapping(value = "/stock/out")
@CrossOrigin
public class StockOutOrderController implements
    PageListController<StockOutOrder, StockOutOrderQuery> {

  @Resource
  StockOutOrderService stockOutOrderService;

  @Override
  public BizService<StockOutOrder, StockOutOrderQuery> getBizService() {
    return stockOutOrderService;
  }

  @PutMapping("/{id}/audit")
  public void audit(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    StockOutOrder outOrder = stockOutOrderService.getByKey(id);
    validateVersion(bo, outOrder);
    stockOutOrderService.audit(outOrder, bo);
  }

  @PutMapping("/{id}/cancel")
  public void invalid(@PathVariable("id") Long id, @RequestBody StockOutOrderCancelBO bo) {
    StockOutOrder outOrder = stockOutOrderService.getByKey(id);
    validateVersion(bo, outOrder);
    stockOutOrderService.cancel(outOrder, bo);
  }

  @PutMapping("/{id}/finish")
  public void end(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    StockOutOrder outOrder = stockOutOrderService.getByKey(id);
    validateVersion(bo, outOrder);
    stockOutOrderService.finish(outOrder, bo);
  }

  @PutMapping("{id}/noticeWms")
  public void noticeWms(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    StockOutOrder stockOutOrder = stockOutOrderService.getByKey(id);
    stockOutOrderService.noticeWms(stockOutOrder, bo);
    if (stockOutOrder.getStatus() == StockOutOrderStatus.NOTICE_FAILED) {
      throw new OmsException("通知WMS失败！");
    }
  }

  @GetMapping("/exportStockOut/{fileName}")
  public void exportStockOut(@PathVariable("fileName") String fileName,StockOutOrderQuery query){
    stockOutOrderService.exportStockOut(fileName,query);
  }
}
