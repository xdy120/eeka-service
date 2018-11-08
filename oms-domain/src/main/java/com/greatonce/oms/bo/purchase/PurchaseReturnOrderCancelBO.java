package com.greatonce.oms.bo.purchase;

import com.greatonce.oms.bo.VersionBO;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-22
 */
public class PurchaseReturnOrderCancelBO extends VersionBO {

  private String reason;

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }
}
