package com.greatonce.oms.api.qimen;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.admin.WmsAppService;
import com.greatonce.oms.bridge.wms.qimen.QimenItemsSynchronizeStrategy;
import com.greatonce.oms.bridge.wms.qimen.request.OmsItemsSynchronizeRequest;
import com.greatonce.oms.domain.admin.WmsApp;
import com.qimen.api.response.ItemsSynchronizeResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * OMS-61，被动调用-商品资料同步 对应奇门接口：taobao.qimen.items.synchronize (商品同步接口) Created by wangcf on
 * 2018/3/15.
 */
@RestController
public class QimenItemsSynchronizeController extends QimenController {

  @Autowired
  private WmsAppService wmsAppService;
  @Autowired
  private QimenAdapterFactory qimenAdapterFactory;

  @PostMapping(params = "method=items.synchronize")
  public ItemsSynchronizeResponse confirm(@RequestBody OmsItemsSynchronizeRequest request,
      @Param("customerId") String customerId) {
    WmsApp wmsApp = wmsAppService.getByCustomerId(customerId);
    Assert.notNull(wmsApp, "未找到对应的接口配置：" + customerId);
    final QimenItemsSynchronizeStrategy adapter = qimenAdapterFactory
        .getItemsSynchronizeAdapter(wmsApp.getWmsType());
    Assert.notNull(wmsApp, "未找到对应的组合套装同步接口实现：" + wmsApp.getWmsAppName());
    return adapter.synchronize(request);
  }
}
