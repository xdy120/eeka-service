package com.greatonce.oms.web.controller.vip;

import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.biz.vip.VipReturnNoticeDetailService;
import com.greatonce.oms.domain.vip.VipReturnNotice;
import com.greatonce.oms.domain.vip.VipReturnNoticeDetail;
import com.greatonce.oms.query.vip.VipReturnNoticeDetailQuery;
import com.greatonce.oms.web.controller.DetailPageCommonController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vip/return/notice/{id}/detail")
@CrossOrigin
public class VipReturnNoticeDetailController implements
    DetailPageCommonController<VipReturnNotice, VipReturnNoticeDetail, VipReturnNoticeDetailQuery> {

  @Autowired
  private VipReturnNoticeDetailService vipReturnNoticeDetailService;


  @Override
  public DetailService<VipReturnNotice, VipReturnNoticeDetail, VipReturnNoticeDetailQuery> getBizService() {
    return vipReturnNoticeDetailService;
  }


}
