package com.greatonce.oms.bo.stock;

import com.greatonce.oms.bo.VersionBO;

/**
 * 借出单取消BO.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-03
 */
public class StockOutOrderCancelBO extends VersionBO {

  private String reason;

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }
}
