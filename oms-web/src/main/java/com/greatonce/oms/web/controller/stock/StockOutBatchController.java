package com.greatonce.oms.web.controller.stock;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.stock.StockOutBatchService;
import com.greatonce.oms.domain.stock.StockOutBatch;
import com.greatonce.oms.query.stock.StockOutBatchQuery;
import com.greatonce.oms.web.controller.PageListController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by wangc on 2018/2/8.
 */
@RestController
@RequestMapping(value = "/stock/out/detail")
@CrossOrigin
public class StockOutBatchController implements
    PageListController<StockOutBatch, StockOutBatchQuery> {

  @Resource
  StockOutBatchService stockOutBatchService;

  @Override
  public BizService<StockOutBatch, StockOutBatchQuery> getBizService() {
    return stockOutBatchService;
  }
}
