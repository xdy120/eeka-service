package com.greatonce.oms.web.controller.admin;

import com.greatonce.oms.biz.admin.MallAppService;
import com.greatonce.oms.domain.admin.MallApp;
import com.greatonce.oms.domain.enums.MallType;
import com.greatonce.oms.query.admin.MallAppQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wangc on 2017/11/2.
 */
@RestController
@RequestMapping("/admin/mallapp")
@CrossOrigin
public class MallAppController {

  @Autowired
  MallAppService mallAppService;

  @RequestMapping(method = RequestMethod.GET, path = "/{mallType}")
  public List<MallApp> list(@PathVariable("mallType") MallType mallType) {
    MallAppQuery filter = new MallAppQuery() {{
      setMallType(mallType);
      setEnable(true);
    }};
    return mallAppService.list(filter);
  }
}
