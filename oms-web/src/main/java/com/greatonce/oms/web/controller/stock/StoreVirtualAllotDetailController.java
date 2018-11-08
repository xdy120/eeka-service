package com.greatonce.oms.web.controller.stock;


import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.biz.stock.StockVirtualAllotDetailService;
import com.greatonce.oms.domain.stock.StockVirtualAllot;
import com.greatonce.oms.domain.stock.StockVirtualAllotDetail;
import com.greatonce.oms.query.stock.StockVirtualAllotDetailQuery;
import com.greatonce.oms.web.controller.DetailPageCommonController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by wangc on 2018/1/17.
 */
@RestController
@RequestMapping(value = "/stock/allot/virtual/{id}/detail")
@CrossOrigin
public class StoreVirtualAllotDetailController implements
    DetailPageCommonController<StockVirtualAllot, StockVirtualAllotDetail, StockVirtualAllotDetailQuery> {

  @Resource
  StockVirtualAllotDetailService stockVirtualAllotDetailService;

  @Override
  public DetailService<StockVirtualAllot, StockVirtualAllotDetail, StockVirtualAllotDetailQuery> getBizService() {
    return stockVirtualAllotDetailService;
  }
}
