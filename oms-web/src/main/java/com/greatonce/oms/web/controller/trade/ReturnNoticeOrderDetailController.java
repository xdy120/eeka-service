package com.greatonce.oms.web.controller.trade;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.trade.ReturnNoticeOrderDetailService;
import com.greatonce.oms.domain.trade.ReturnNoticeOrderDetail;
import com.greatonce.oms.query.trade.ReturnNoticeOrderDetailQuery;
import com.greatonce.oms.web.controller.PageListController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangc on 2017/12/28.
 */
@RestController
@RequestMapping(value = "/trade/return/notice/{id}/detail")
@CrossOrigin
public class ReturnNoticeOrderDetailController implements
    PageListController<ReturnNoticeOrderDetail, ReturnNoticeOrderDetailQuery> {

  @Autowired
  ReturnNoticeOrderDetailService returnNoticeOrderDetailService;

  @Override
  public BizService<ReturnNoticeOrderDetail, ReturnNoticeOrderDetailQuery> getBizService() {
    return returnNoticeOrderDetailService;
  }
}
