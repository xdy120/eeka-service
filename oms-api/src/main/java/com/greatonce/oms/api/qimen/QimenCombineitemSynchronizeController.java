package com.greatonce.oms.api.qimen;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.admin.WmsAppService;
import com.greatonce.oms.bridge.wms.qimen.QimenCombineitemSynchronizeStrategy;
import com.greatonce.oms.bridge.wms.qimen.request.OmsCombineitemSynchronizeRequest;
import com.greatonce.oms.domain.admin.WmsApp;
import com.qimen.api.response.CombineitemSynchronizeResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * OMS-62，被动调用-套装商品资料同步 对应奇门接口：taobao.qimen.combineitem.synchronize (组合商品接口) Created by wangcf on
 * 2018/3/15.
 */
@RestController
public class QimenCombineitemSynchronizeController extends QimenController {

  @Autowired
  private WmsAppService wmsAppService;
  @Autowired
  private QimenAdapterFactory qimenAdapterFactory;

  @PostMapping(params = "method=combineitem.synchronize")
  public CombineitemSynchronizeResponse confirm(
      @RequestBody OmsCombineitemSynchronizeRequest request,
      @Param("customerId") String customerId) {
    WmsApp wmsApp = wmsAppService.getByCustomerId(customerId);
    Assert.notNull(wmsApp, "未找到对应的接口配置：" + customerId);
    final QimenCombineitemSynchronizeStrategy adapter = qimenAdapterFactory
        .getCombineitemSynchronizeAdapter(wmsApp.getWmsType());
    Assert.notNull(wmsApp, "未找到对应的组合套装同步接口实现：" + wmsApp.getWmsAppName());
    return adapter.synchronize(request);
  }
}
