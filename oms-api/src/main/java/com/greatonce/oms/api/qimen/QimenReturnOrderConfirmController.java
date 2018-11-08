package com.greatonce.oms.api.qimen;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.admin.WmsAppService;
import com.greatonce.oms.bridge.wms.qimen.QimenReturnOrderConfirmStrategy;
import com.greatonce.oms.bridge.wms.qimen.request.OmsReturnOrderConfirmRequest;
import com.greatonce.oms.domain.admin.WmsApp;
import com.qimen.api.response.ReturnorderConfirmResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * B2C退货入库确认.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/23
 */
@RestController
public class QimenReturnOrderConfirmController extends QimenController {

  @Autowired
  private WmsAppService wmsAppService;
  @Autowired
  private QimenAdapterFactory qimenAdapterFactory;

  @PostMapping(params = "method=returnorder.confirm")
  public ReturnorderConfirmResponse confirm(@RequestBody OmsReturnOrderConfirmRequest request,
      @Param("customerId") String customerId) {
    WmsApp wmsApp = wmsAppService.getByCustomerId(customerId);
    Assert.notNull(wmsApp, "未找到对应的接口配置：" + customerId);
    final QimenReturnOrderConfirmStrategy adapter = qimenAdapterFactory
        .getReturnOrderConfirmAdapter(wmsApp.getWmsType());
    Assert.notNull(wmsApp, "未找到对应的退货单确认接口实现：" + wmsApp.getWmsAppName());
    return adapter.confirm(request);
  }
}
