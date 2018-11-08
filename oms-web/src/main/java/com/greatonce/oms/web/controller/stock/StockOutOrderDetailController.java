package com.greatonce.oms.web.controller.stock;

import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.biz.stock.StockOutOrderDetailService;
import com.greatonce.oms.domain.stock.StockOutOrder;
import com.greatonce.oms.domain.stock.StockOutOrderDetail;
import com.greatonce.oms.query.stock.StockOutOrderDetailQuery;
import com.greatonce.oms.web.controller.DetailPageCommonController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by wangc on 2018/1/23.
 */
@RestController
@RequestMapping(value = "/stock/out/{id}/detail")
@CrossOrigin
public class StockOutOrderDetailController implements
    DetailPageCommonController<StockOutOrder, StockOutOrderDetail, StockOutOrderDetailQuery> {


  @Resource
  StockOutOrderDetailService stockOutOrderDetailService;

  @Override
  public DetailService<StockOutOrder, StockOutOrderDetail, StockOutOrderDetailQuery> getBizService() {
    return stockOutOrderDetailService;
  }

}
