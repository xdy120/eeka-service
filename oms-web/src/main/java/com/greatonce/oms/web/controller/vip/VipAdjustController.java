package com.greatonce.oms.web.controller.vip;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.vip.VipAdjustService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.domain.vip.VipAdjust;
import com.greatonce.oms.query.vip.VipAdjustQuery;
import com.greatonce.oms.web.controller.PageListController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vip/adjust")
@CrossOrigin
public class VipAdjustController implements PageListController<VipAdjust, VipAdjustQuery> {

  @Autowired
  VipAdjustService vipAdjustService;


  @Override
  public BizService<VipAdjust, VipAdjustQuery> getBizService() {
    return vipAdjustService;
  }

  @RequestMapping(path = "{id}/audit", method = RequestMethod.PUT)
  public void audit(@RequestBody VipAdjust v,@RequestBody VersionBO bo) {
    vipAdjustService.audit(v,bo);
  }

}
