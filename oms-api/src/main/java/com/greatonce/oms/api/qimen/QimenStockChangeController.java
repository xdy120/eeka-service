package com.greatonce.oms.api.qimen;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.admin.WmsAppService;
import com.greatonce.oms.bridge.wms.qimen.QimenStockChangeReportStrategy;
import com.greatonce.oms.bridge.wms.qimen.request.OmsStockChangeReportRequest;
import com.greatonce.oms.domain.admin.WmsApp;
import com.qimen.api.response.StockchangeReportResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 库存同步接口.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 6/11/2018
 */
@RestController
public class QimenStockChangeController extends QimenController {

  @Autowired
  private WmsAppService wmsAppService;
  @Autowired
  private QimenAdapterFactory qimenAdapterFactory;

  @PostMapping(params = "method=stockchange.report")
  public StockchangeReportResponse confirm(@RequestBody OmsStockChangeReportRequest request,
      @Param("customerId") String customerId) {
    WmsApp wmsApp = wmsAppService.getByCustomerId(customerId);
    Assert.notNull(wmsApp, "未找到对应的接口配置：" + customerId);
    final QimenStockChangeReportStrategy adapter = qimenAdapterFactory
        .getStockChangeReportAdapter(wmsApp.getWmsType());
    Assert.notNull(wmsApp, "未找到对应的库存异动接口实现：" + wmsApp.getWmsAppName());
    return adapter.report(request);
  }
}
