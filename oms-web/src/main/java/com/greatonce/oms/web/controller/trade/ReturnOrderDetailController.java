package com.greatonce.oms.web.controller.trade;

import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.biz.trade.ReturnOrderDetailService;
import com.greatonce.oms.domain.trade.ReturnOrder;
import com.greatonce.oms.domain.trade.ReturnOrderDetail;
import com.greatonce.oms.query.trade.ReturnOrderDetailQuery;
import com.greatonce.oms.web.controller.DetailFullCommonController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author buer
 * @version 2017-12-08 9:46
 */
@RestController
@RequestMapping(value = "/trade/return/{id}/detail")
@CrossOrigin
public class ReturnOrderDetailController implements
    DetailFullCommonController<ReturnOrder, ReturnOrderDetail, ReturnOrderDetailQuery> {

  @Autowired
  ReturnOrderDetailService returnOrderDetailService;

  @Override
  public DetailService<ReturnOrder, ReturnOrderDetail, ReturnOrderDetailQuery> getBizService() {
    return returnOrderDetailService;
  }
}
