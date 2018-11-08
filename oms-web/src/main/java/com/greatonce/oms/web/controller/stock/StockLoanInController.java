package com.greatonce.oms.web.controller.stock;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.stock.StockLoanInService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.stock.StockLoanInCancelBO;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.enums.stock.StockLoanInStatus;
import com.greatonce.oms.domain.stock.StockLoanIn;
import com.greatonce.oms.query.stock.StockLoanInQuery;
import com.greatonce.oms.web.controller.PageListController;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangc on 2018/1/22.
 */
@RestController
@RequestMapping(value = "stock/loanIn")
@CrossOrigin
public class StockLoanInController implements PageListController<StockLoanIn, StockLoanInQuery> {

  @Resource
  StockLoanInService stockLoanInService;

  @Override
  public BizService<StockLoanIn, StockLoanInQuery> getBizService() {
    return stockLoanInService;
  }

  @PutMapping("/{id}/audit")
  public void audit(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    StockLoanIn stockLoanIn = stockLoanInService.getByKey(id);
    validateVersion(bo, stockLoanIn);
    stockLoanInService.audit(stockLoanIn, bo);
  }

  @PutMapping("/{id}/cancel")
  public void cancel(@PathVariable("id") Long id, @RequestBody StockLoanInCancelBO bo) {
    StockLoanIn stockLoanIn = stockLoanInService.getByKey(id);
    validateVersion(bo, stockLoanIn);
    stockLoanInService.cancel(stockLoanIn, bo);
  }

  @PutMapping("{id}/noticeWms")
  public void noticeWms(@PathVariable("id") Long id) {
    StockLoanIn stockLoanIn = stockLoanInService.getByKey(id);
    stockLoanInService.noticeWms(stockLoanIn);
    if (stockLoanIn.getStatus() == StockLoanInStatus.NOTICE_FAILED) {
      throw new OmsException("通知WMS失败！");
    }
  }

}
