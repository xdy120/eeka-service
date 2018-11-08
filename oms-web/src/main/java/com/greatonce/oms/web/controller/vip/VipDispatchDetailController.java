package com.greatonce.oms.web.controller.vip;

import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.biz.vip.VipDispatchDetailService;
import com.greatonce.oms.biz.vip.VipDispatchService;
import com.greatonce.oms.domain.vip.VipDispatch;
import com.greatonce.oms.domain.vip.VipDispatchDetail;
import com.greatonce.oms.query.vip.VipDispatchDetailQuery;
import com.greatonce.oms.web.controller.DetailPageCommonController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vip/dispatch/{id}/detail")
@CrossOrigin
public class VipDispatchDetailController implements
    DetailPageCommonController<VipDispatch, VipDispatchDetail, VipDispatchDetailQuery> {

  @Autowired
  VipDispatchService vipDispatchService;

  @Autowired
  VipDispatchDetailService vipDispatchDetailService;

  @Override
  public DetailService<VipDispatch, VipDispatchDetail, VipDispatchDetailQuery> getBizService() {
    return vipDispatchDetailService;
  }

  @Override
  @PostMapping("{id}")
  public VipDispatch insert(@PathVariable("id") Long id, @RequestBody VipDispatch vipDispatch) {
    VipDispatch dispatch = vipDispatchService.getByKey(id);
    dispatch.setDetails(vipDispatch.getDetails());
    getBizService().createDetail(dispatch);
    vipDispatch.setVersion(vipDispatch.getVersion() + 1);
    return vipDispatch;
  }
}
