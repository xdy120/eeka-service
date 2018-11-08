package com.greatonce.oms.bo.trade;

import com.greatonce.oms.domain.trade.DispatchOrderDetail;

/**
 * @author buer
 * @version 2018-01-08 17:27
 */
public class DispatchOrderCancelDetailBO extends DispatchOrderCancelBO {

  private DispatchOrderDetail detail;

  public DispatchOrderDetail getDetail() {
    return detail;
  }

  public void setDetail(DispatchOrderDetail detail) {
    this.detail = detail;
  }
}
