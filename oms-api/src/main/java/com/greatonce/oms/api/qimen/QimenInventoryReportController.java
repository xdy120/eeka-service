package com.greatonce.oms.api.qimen;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.admin.WmsAppService;
import com.greatonce.oms.bridge.wms.qimen.QimenInventoryReportStrategy;
import com.greatonce.oms.bridge.wms.qimen.request.OmsInventoryReportRequest;
import com.greatonce.oms.domain.admin.WmsApp;
import com.qimen.api.response.InventoryReportResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 库存盘点接口.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 6/11/2018
 */
@RestController
public class QimenInventoryReportController extends QimenController {

  @Autowired
  private WmsAppService wmsAppService;
  @Autowired
  private QimenAdapterFactory qimenAdapterFactory;

  @PostMapping(params = "method=inventory.report")
  public InventoryReportResponse confirm(@RequestBody OmsInventoryReportRequest request,
      @Param("customerId") String customerId) {
    WmsApp wmsApp = wmsAppService.getByCustomerId(customerId);
    Assert.notNull(wmsApp, "未找到对应的接口配置：" + customerId);
    final QimenInventoryReportStrategy adapter = qimenAdapterFactory
        .getInventoryReportAdapter(wmsApp.getWmsType());
    Assert.notNull(wmsApp, "未找到对应的组合套装同步接口实现：" + wmsApp.getWmsAppName());
    return adapter.report(request);
  }
}
