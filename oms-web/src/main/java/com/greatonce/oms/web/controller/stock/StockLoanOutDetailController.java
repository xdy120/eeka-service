package com.greatonce.oms.web.controller.stock;

import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.biz.stock.StockLoanOutDetailService;
import com.greatonce.oms.biz.stock.StockLoanOutService;
import com.greatonce.oms.domain.stock.StockLoanOut;
import com.greatonce.oms.domain.stock.StockLoanOutDetail;
import com.greatonce.oms.query.stock.StockLoanOutDetailQuery;
import com.greatonce.oms.web.controller.DetailPageCommonController;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangc on 2018/1/23.
 */
@RestController
@RequestMapping(value = "/stock/loanOut/{id}/detail")
@CrossOrigin
public class StockLoanOutDetailController implements
    DetailPageCommonController<StockLoanOut, StockLoanOutDetail, StockLoanOutDetailQuery> {

  @Resource
  StockLoanOutDetailService stockLoanOutDetailService;
  @Resource
  StockLoanOutService stockLoanOutService;

  @Override
  public DetailService<StockLoanOut, StockLoanOutDetail, StockLoanOutDetailQuery> getBizService() {
    return stockLoanOutDetailService;
  }
}

