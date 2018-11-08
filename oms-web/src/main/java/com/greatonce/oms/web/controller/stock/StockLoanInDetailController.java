package com.greatonce.oms.web.controller.stock;

import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.biz.stock.StockLoanInDetailService;
import com.greatonce.oms.domain.stock.StockLoanIn;
import com.greatonce.oms.domain.stock.StockLoanInDetail;
import com.greatonce.oms.query.stock.StockLoanInDetailQuery;
import com.greatonce.oms.web.controller.DetailPageCommonController;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangc on 2018/1/23.
 */
@RestController
@RequestMapping(value = "/stock/loanIn/{id}/detail")
@CrossOrigin
public class StockLoanInDetailController implements
    DetailPageCommonController<StockLoanIn, StockLoanInDetail, StockLoanInDetailQuery> {

  @Resource
  StockLoanInDetailService stockLoanInDetailService;

  @Override
  public DetailService<StockLoanIn, StockLoanInDetail, StockLoanInDetailQuery> getBizService() {
    return stockLoanInDetailService;
  }

}

