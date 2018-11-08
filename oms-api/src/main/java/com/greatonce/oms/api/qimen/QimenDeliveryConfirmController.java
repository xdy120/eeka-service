package com.greatonce.oms.api.qimen;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.admin.WmsAppService;
import com.greatonce.oms.bridge.wms.qimen.QimenDeliveryConfirmStrategy;
import com.greatonce.oms.bridge.wms.qimen.request.OmsDeliveryOrderConfirmRequest;
import com.greatonce.oms.domain.admin.WmsApp;
import com.qimen.api.response.DeliveryorderConfirmResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * B2C发货确认
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/23
 */
@RestController
public class QimenDeliveryConfirmController extends QimenController {

  @Autowired
  private WmsAppService wmsAppService;
  @Autowired
  private QimenAdapterFactory qimenAdapterFactory;

  /**
   * 奇门发货处理接口.
   *
   * @param request 奇门发货确认请求
   */
  @PostMapping(params = "method=deliveryorder.confirm")
  public DeliveryorderConfirmResponse dispatchConfirm(
      @RequestBody OmsDeliveryOrderConfirmRequest request, @Param("customerId") String customerId) {
    WmsApp wmsApp = wmsAppService.getByCustomerId(customerId);
    Assert.notNull(wmsApp, "未找到对应的接口配置：" + customerId);
    final QimenDeliveryConfirmStrategy adapter = qimenAdapterFactory
        .getDeliveryConfirmAdapter(wmsApp.getWmsType());
    Assert.notNull(wmsApp, "未找到对应的发货单确认接口实现：" + wmsApp.getWmsAppName());
    return adapter.confirm(request);
  }
}
