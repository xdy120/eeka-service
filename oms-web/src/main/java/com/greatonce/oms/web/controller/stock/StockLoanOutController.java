package com.greatonce.oms.web.controller.stock;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.stock.StockLoanOutService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.stock.StockLoanOutBO;
import com.greatonce.oms.bo.stock.StockLoanOutCancelBO;
import com.greatonce.oms.bo.stock.StockLoanOutVerificationBO;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.enums.stock.StockLoanOutStatus;
import com.greatonce.oms.domain.stock.StockLoanOut;
import com.greatonce.oms.query.stock.StockLoanOutQuery;
import com.greatonce.oms.web.controller.PageListController;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangc on 2018/1/22.
 */
@RestController
@RequestMapping(value = "/stock/loanOut")
@CrossOrigin
public class StockLoanOutController implements PageListController<StockLoanOut, StockLoanOutQuery> {

  @Resource
  StockLoanOutService stockLoanOutService;

  @Override
  public BizService<StockLoanOut, StockLoanOutQuery> getBizService() {
    return stockLoanOutService;
  }

  @PutMapping("/{id}/audit")
  public void audit(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    StockLoanOut stockLoanOut = stockLoanOutService.getByKey(id);
    stockLoanOutService.audit(stockLoanOut, bo);
  }

  @PutMapping("/{id}/cancel")
  public void cancel(@PathVariable("id") Long id, @RequestBody StockLoanOutCancelBO bo) {
    StockLoanOut stockLoanOut = stockLoanOutService.getByKey(id);
    validateVersion(bo, stockLoanOut);
    stockLoanOutService.cancel(stockLoanOut, bo);
  }

  @PutMapping(path = "{id}/noticeWms")
  public void noticeWms(@PathVariable("id") Long id) {
    StockLoanOut stockLoanOut = stockLoanOutService.getByKey(id);
    stockLoanOutService.noticeWms(stockLoanOut);
    if (stockLoanOut.getStatus() == StockLoanOutStatus.NOTICE_FAILED) {
      throw new OmsException("通知WMS失败！");
    }
  }

  @PutMapping(path = "{id}/verification")
  public void verification(@PathVariable("id") Long id,
      @RequestBody StockLoanOutVerificationBO bo) {
    StockLoanOut stockLoanOut = stockLoanOutService.getByKey(id);
    validateVersion(bo, stockLoanOut);
    stockLoanOutService.verificationDetail(stockLoanOut, bo);
  }

  @GetMapping(path = "/statistics")
  public PageList<StockLoanOutBO> statistics(StockLoanOutQuery stockLoanOutQuery,
      @RequestParam("page") int page,
      @RequestParam("pageSize") int pageSize) {
    return stockLoanOutService.listStatistics(stockLoanOutQuery, page, pageSize);
  }

  @PostMapping("/exportSku/{fileName}")
  public void exportLoan(@PathVariable("fileName") String fileName,
      @RequestBody StockLoanOutQuery query) {
    stockLoanOutService.exportLoan(query, fileName);
  }

}
