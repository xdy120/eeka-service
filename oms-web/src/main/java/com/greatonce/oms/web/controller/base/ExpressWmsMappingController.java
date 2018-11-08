package com.greatonce.oms.web.controller.base;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.base.ExpressWmsMappingService;
import com.greatonce.oms.domain.base.ExpressWmsMapping;
import com.greatonce.oms.query.base.ExpressWmsMappingQuery;
import com.greatonce.oms.web.controller.PageListController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base/express/wms/mapping")
@CrossOrigin
public class ExpressWmsMappingController implements
    PageListController<ExpressWmsMapping, ExpressWmsMappingQuery> {

  @Autowired
  ExpressWmsMappingService expressWmsMappingService;

  @Override
  public BizService<ExpressWmsMapping, ExpressWmsMappingQuery> getBizService() {
    return expressWmsMappingService;
  }
}
