package com.greatonce.oms.api.qimen;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.admin.WmsAppService;
import com.greatonce.oms.bridge.wms.qimen.QimenStockOutConfirmStrategy;
import com.greatonce.oms.bridge.wms.qimen.request.OmsStockOutConfirmRequest;
import com.greatonce.oms.domain.admin.WmsApp;
import com.qimen.api.response.StockoutConfirmResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 出库单确认.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/23
 */
@RestController
public class QimenStockOutConfirmController extends QimenController {

  @Autowired
  private WmsAppService wmsAppService;
  @Autowired
  private QimenAdapterFactory qimenAdapterFactory;


  /**
   * 出库单确认.
   */
  @PostMapping(params = "method=stockout.confirm")
  public StockoutConfirmResponse confirm(@RequestBody OmsStockOutConfirmRequest request,
      @Param("customerId") String customerId) {
    WmsApp wmsApp = wmsAppService.getByCustomerId(customerId);
    Assert.notNull(wmsApp, "未找到对应的接口配置：" + customerId);
    final QimenStockOutConfirmStrategy adapter = qimenAdapterFactory
        .getStockOutConfirmAdapter(wmsApp.getWmsType());
    Assert.notNull(wmsApp, "未找到对应的出库单确认接口实现：" + wmsApp.getWmsAppName());
    return adapter.confirm(request);
  }
}
