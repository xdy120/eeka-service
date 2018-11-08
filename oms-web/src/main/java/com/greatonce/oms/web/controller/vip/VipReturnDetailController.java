package com.greatonce.oms.web.controller.vip;

import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.biz.vip.VipReturnDetailService;
import com.greatonce.oms.biz.vip.VipReturnService;
import com.greatonce.oms.domain.vip.VipReturn;
import com.greatonce.oms.domain.vip.VipReturnDetail;
import com.greatonce.oms.query.vip.VipReturnDetailQuery;
import com.greatonce.oms.web.controller.DetailPageCommonController;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vip/return/{id}/detail")
@CrossOrigin
public class VipReturnDetailController implements
    DetailPageCommonController<VipReturn, VipReturnDetail, VipReturnDetailQuery> {


  @Autowired
  VipReturnDetailService vipReturnDetailService;

  @Autowired
  VipReturnService vipReturnService;

  @Override
  public DetailService<VipReturn, VipReturnDetail, VipReturnDetailQuery> getBizService() {
    return vipReturnDetailService;
  }

  @GetMapping("/details")
  public List<VipReturnDetail> list(VipReturnDetailQuery filter) {
    return getBizService().list(filter);
  }
}
