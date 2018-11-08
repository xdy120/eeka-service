package com.greatonce.oms.web.controller.base;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.base.ExpressStrategyService;
import com.greatonce.oms.domain.base.ExpressStrategy;
import com.greatonce.oms.query.base.ExpressStrategyQuery;
import com.greatonce.oms.web.controller.FullListController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author buer
 * @version 2018-05-12 9:09
 */
@RestController
@RequestMapping("/base/setting/express")
@CrossOrigin
public class ExpressStrategyController implements
    FullListController<ExpressStrategy, ExpressStrategyQuery> {

  @Autowired
  ExpressStrategyService expressStrategyService;

  @Override
  public BizService<ExpressStrategy, ExpressStrategyQuery> getBizService() {
    return expressStrategyService;
  }
}
