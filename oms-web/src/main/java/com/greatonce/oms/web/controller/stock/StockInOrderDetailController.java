package com.greatonce.oms.web.controller.stock;

import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.biz.stock.StockInOrderDetailService;
import com.greatonce.oms.domain.stock.StockInOrder;
import com.greatonce.oms.domain.stock.StockInOrderDetail;
import com.greatonce.oms.query.stock.StockInOrderDetailQuery;
import com.greatonce.oms.web.controller.DetailPageCommonController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by wangc on 2018/1/23.
 */
@RestController
@RequestMapping(value = "/stock/in/{id}/detail")
@CrossOrigin
public class StockInOrderDetailController implements
    DetailPageCommonController<StockInOrder, StockInOrderDetail, StockInOrderDetailQuery> {

  @Resource
  StockInOrderDetailService stockInOrderDetailService;

  @Override
  public DetailService<StockInOrder, StockInOrderDetail, StockInOrderDetailQuery> getBizService() {
    return stockInOrderDetailService;
  }

}

