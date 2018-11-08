package com.greatonce.oms.web.controller.vip;

import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.biz.vip.VipAdjustDetailService;
import com.greatonce.oms.domain.vip.VipAdjust;
import com.greatonce.oms.domain.vip.VipAdjustDetail;
import com.greatonce.oms.query.vip.VipAdjustDetailQuery;
import com.greatonce.oms.web.controller.DetailPageCommonController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vip/adjust/{id}/detail")
@CrossOrigin
public class VipAdjustDetailController implements
    DetailPageCommonController<VipAdjust, VipAdjustDetail, VipAdjustDetailQuery> {

  @Autowired
  VipAdjustDetailService vipAdjustDetailService;


  @Override
  public DetailService<VipAdjust, VipAdjustDetail, VipAdjustDetailQuery> getBizService() {
    return vipAdjustDetailService;
  }


}
