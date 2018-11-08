package com.greatonce.oms.web.controller.base;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.base.StockUploadStrategyService;
import com.greatonce.oms.domain.base.StockUploadStrategy;
import com.greatonce.oms.query.base.StockUploadStrategyQuery;
import com.greatonce.oms.web.controller.FullListController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base/setting/stock/upload")
@CrossOrigin
public class StockUploadStrategyController implements
    FullListController<StockUploadStrategy, StockUploadStrategyQuery> {

  @Autowired
  StockUploadStrategyService stockUploadStrategyService;

  @Override
  public BizService<StockUploadStrategy, StockUploadStrategyQuery> getBizService() {
    return stockUploadStrategyService;
  }
}
