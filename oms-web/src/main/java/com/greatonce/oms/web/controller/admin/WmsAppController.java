package com.greatonce.oms.web.controller.admin;

import com.greatonce.oms.biz.admin.WmsAppService;
import com.greatonce.oms.domain.admin.WmsApp;
import com.greatonce.oms.query.admin.WmsAppQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by wangc on 2017/11/2.
 */
@RestController
@RequestMapping("/admin/wmsapp")
@CrossOrigin
public class WmsAppController {

  @Autowired
  WmsAppService wmsAppService;

  @RequestMapping(method = RequestMethod.GET)
  public List<WmsApp> list() {
    WmsAppQuery filter = new WmsAppQuery() {{
      setEnable(true);
    }};
    return wmsAppService.list(filter);
  }
}
