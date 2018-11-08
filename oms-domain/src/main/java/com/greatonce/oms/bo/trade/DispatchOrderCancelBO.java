package com.greatonce.oms.bo.trade;

import com.greatonce.oms.bo.VersionBO;

/**
 * @author buer
 * @version 2018-01-08 17:25
 */
public class DispatchOrderCancelBO extends VersionBO {

  private String reason;

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }
}
