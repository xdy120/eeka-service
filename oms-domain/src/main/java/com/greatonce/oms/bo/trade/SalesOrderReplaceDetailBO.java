package com.greatonce.oms.bo.trade;

import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.domain.trade.SalesOrderDetail;

/**
 * @author buer
 * @version 2017-12-27 19:19
 */
public class SalesOrderReplaceDetailBO extends VersionBO {

  private SalesOrderDetail sourceDetail;
  private SalesOrderDetail targetDetail;

  public SalesOrderDetail getSourceDetail() {
    return sourceDetail;
  }

  public void setSourceDetail(SalesOrderDetail sourceDetail) {
    this.sourceDetail = sourceDetail;
  }

  public SalesOrderDetail getTargetDetail() {
    return targetDetail;
  }

  public void setTargetDetail(SalesOrderDetail targetDetail) {
    this.targetDetail = targetDetail;
  }
}
