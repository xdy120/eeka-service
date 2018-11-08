package com.greatonce.oms.bo.trade;

import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.domain.trade.SalesOrderDetail;

/**
 * @author buer
 * @version 2018-01-06 10:03
 */
public class SalesOrderDetailBO extends VersionBO {

  private SalesOrderDetail detail;

  public SalesOrderDetail getDetail() {
    return detail;
  }

  public void setDetail(SalesOrderDetail detail) {
    this.detail = detail;
  }
}
