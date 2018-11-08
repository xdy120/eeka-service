package com.greatonce.oms.web.controller.trade;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.trade.ReturnOrderOutDetailService;
import com.greatonce.oms.domain.trade.ReturnOrderOutDetail;
import com.greatonce.oms.query.trade.ReturnOrderOutDetailQuery;
import com.greatonce.oms.web.controller.FullListController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangc on 2018/1/19.
 */
@RestController
@RequestMapping(value = "/trade/return/{id}/out/detail")
@CrossOrigin
public class ReturnOrderOutDetailController implements
    FullListController<ReturnOrderOutDetail, ReturnOrderOutDetailQuery> {

  @Autowired
  ReturnOrderOutDetailService returnOrderOutDetailService;

  @Override
  public BizService<ReturnOrderOutDetail, ReturnOrderOutDetailQuery> getBizService() {
    return returnOrderOutDetailService;
  }

  @PostMapping(path = "/{id}/replace")
  public int replaceOutDetail(@PathVariable("id") Long id,
      @RequestBody ReturnOrderOutDetail detail) {
    return returnOrderOutDetailService.replaceOutDetail(detail);
  }

}
