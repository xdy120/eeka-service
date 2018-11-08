package com.greatonce.oms.web.controller.vip;

import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.biz.vip.VipScheduleDetailService;
import com.greatonce.oms.domain.vip.VipSchedule;
import com.greatonce.oms.domain.vip.VipScheduleDetail;
import com.greatonce.oms.query.vip.VipScheduleDetailQuery;
import com.greatonce.oms.web.controller.DetailPageCommonController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/vip/schedule/{id}/detail")
@CrossOrigin
public class VipScheduleDetailController implements
    DetailPageCommonController<VipSchedule, VipScheduleDetail, VipScheduleDetailQuery> {

  @Resource
  VipScheduleDetailService vipScheduleDetailService;

  @Override
  public DetailService<VipSchedule, VipScheduleDetail, VipScheduleDetailQuery> getBizService() {
    return vipScheduleDetailService;
  }


}
