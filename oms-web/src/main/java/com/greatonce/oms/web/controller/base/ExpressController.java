package com.greatonce.oms.web.controller.base;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.base.ExpressService;
import com.greatonce.oms.domain.base.Express;
import com.greatonce.oms.query.base.ExpressQuery;
import com.greatonce.oms.web.controller.EnableController;
import com.greatonce.oms.web.controller.PageListController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base/express")
@CrossOrigin
public class ExpressController implements PageListController<Express, ExpressQuery>,
    EnableController<Express, ExpressQuery> {

  @Autowired
  private ExpressService expressService;

  @Override
  public BizService<Express, ExpressQuery> getBizService() {
    return expressService;
  }
}
