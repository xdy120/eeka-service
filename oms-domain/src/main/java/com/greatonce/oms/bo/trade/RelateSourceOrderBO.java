package com.greatonce.oms.bo.trade;

import com.greatonce.oms.domain.trade.ReturnOrder;
import com.greatonce.oms.domain.trade.SalesOrder;

public class RelateSourceOrderBO {

  private SalesOrder salesOrder;

  private ReturnOrder returnOrder;

  public SalesOrder getSalesOrder() {
    return salesOrder;
  }

  public void setSalesOrder(SalesOrder salesOrder) {
    this.salesOrder = salesOrder;
  }

  public ReturnOrder getReturnOrder() {
    return returnOrder;
  }

  public void setReturnOrder(ReturnOrder returnOrder) {
    this.returnOrder = returnOrder;
  }

}
