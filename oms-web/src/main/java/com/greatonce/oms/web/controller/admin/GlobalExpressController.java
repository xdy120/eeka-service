package com.greatonce.oms.web.controller.admin;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.admin.GlobalExpressService;
import com.greatonce.oms.domain.admin.GlobalExpress;
import com.greatonce.oms.query.admin.GlobalExpressQuery;
import com.greatonce.oms.web.controller.FullListController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangc on 2018/1/4.
 */
@RestController
@RequestMapping("/admin/globalExpress")
@CrossOrigin
public class GlobalExpressController implements
    FullListController<GlobalExpress, GlobalExpressQuery> {

  @Autowired
  private GlobalExpressService globalExpressService;

  @Override
  public BizService<GlobalExpress, GlobalExpressQuery> getBizService() {
    return globalExpressService;
  }

}

