package com.greatonce.oms.custom.eeka.consumer;

import com.greatonce.oms.biz.admin.WmsAppService;
import com.greatonce.oms.domain.admin.WmsApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * EEKA帮助类.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/20
 */
@Component
public class EekaHelper {

  @Value("${oms.consumer.custom.fms.customerId:c1521713662918}")
  private String customerId;
  @Autowired
  private WmsAppService wmsAppService;
  private WmsApp fms;

  /**
   * 获取fms的应用.
   */
  public WmsApp getFmsApp() {
    if (fms == null) {
      WmsApp eg = new WmsApp();
      eg.setCustomerId(customerId);
      fms = wmsAppService.getByExample(eg);
    }
    return fms;
  }
}
