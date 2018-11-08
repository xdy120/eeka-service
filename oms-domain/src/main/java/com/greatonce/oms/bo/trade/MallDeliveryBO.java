package com.greatonce.oms.bo.trade;

import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.domain.trade.DispatchOrderDetail;

/**
 * @Company: Shenzhen Greatonce Co Ltd
 * @Author: Lcc
 * @Version: 2018/3/21 16:14
 * @Notes:
 */
public class MallDeliveryBO extends VersionBO {

  private boolean expressNoUpdated;

  public boolean isExpressNoUpdated() {
    return expressNoUpdated;
  }

  public void setExpressNoUpdated(boolean expressNoUpdated) {
    this.expressNoUpdated = expressNoUpdated;
  }
}
