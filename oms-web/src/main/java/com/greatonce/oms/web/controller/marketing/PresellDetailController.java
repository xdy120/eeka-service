package com.greatonce.oms.web.controller.marketing;

import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.biz.marketing.PresellDetailService;
import com.greatonce.oms.domain.marketing.Presell;
import com.greatonce.oms.domain.marketing.PresellDetail;
import com.greatonce.oms.query.marketing.PresellDetailQuery;
import com.greatonce.oms.web.controller.DetailPageCommonController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangc on 2017/12/5.
 */
@RestController
@RequestMapping(value = "/marketing/presell/{id}/detail")
@CrossOrigin
public class PresellDetailController implements
    DetailPageCommonController<Presell, PresellDetail, PresellDetailQuery> {

  @Autowired
  private PresellDetailService presellDetailService;

  @Override
  public DetailService<Presell, PresellDetail, PresellDetailQuery> getBizService() {
    return presellDetailService;
  }

}
