package com.greatonce.oms.web.controller.stock;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.stock.StockInBatchService;
import com.greatonce.oms.domain.stock.StockInBatch;
import com.greatonce.oms.query.stock.StockInBatchQuery;
import com.greatonce.oms.web.controller.PageListController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by wangc on 2018/2/8.
 */

@RestController
@RequestMapping(value = "/stock/in/detail")
@CrossOrigin
public class StockInBatchController implements PageListController<StockInBatch, StockInBatchQuery> {

  @Resource
  StockInBatchService stockInBatchService;

  @Override
  public BizService<StockInBatch, StockInBatchQuery> getBizService() {
    return stockInBatchService;
  }
}
