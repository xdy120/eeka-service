package com.greatonce.oms.web.controller.vip;

import com.greatonce.core.database.PageList;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.vip.VipScheduleService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.domain.vip.VipSchedule;
import com.greatonce.oms.query.vip.VipScheduleQuery;
import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.web.controller.ControllerUtil;
import com.greatonce.oms.web.controller.PageListController;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vip/schedule")
@CrossOrigin
public class VipScheduleController implements PageListController<VipSchedule, VipScheduleQuery> {

  @Autowired
  VipScheduleService vipScheduleService;

  @Autowired
  private StoreService storeService;
  @Autowired
  private ControllerUtil controllerUtil;

  @Override
  public BizService<VipSchedule, VipScheduleQuery> getBizService() {
    return vipScheduleService;
  }

  @GetMapping
  public PageList<VipSchedule> listPage(VipScheduleQuery filter, @RequestParam("page") int page,
      @RequestParam("pageSize") int pageSize) {
    controllerUtil.addUserStoreIds(filter, VipScheduleQuery::getStoreIds, filter::setStoreIds);
    return vipScheduleService.listPage(filter, page, pageSize);
  }

  @RequestMapping(path = "{id}/audit", method = RequestMethod.PUT)
  public void audit(@RequestBody VipSchedule t, @RequestBody VersionBO bo) {
    vipScheduleService.audit(t, bo);
  }

  @RequestMapping(path = "{id}/upload", method = RequestMethod.PUT)
  public void upload(@RequestBody VipSchedule t) {
    vipScheduleService.upload(t);
  }

  @RequestMapping(path = "{id}/end", method = RequestMethod.PUT)
  public void end(@RequestBody VipSchedule t) {
    vipScheduleService.end(t);
  }


}
