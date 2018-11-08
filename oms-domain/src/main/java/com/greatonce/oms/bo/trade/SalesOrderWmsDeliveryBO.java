package com.greatonce.oms.bo.trade;

import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.domain.trade.DispatchOrder;
import com.greatonce.oms.domain.trade.DispatchOrderDelivery;
import com.greatonce.oms.domain.trade.DispatchOrderDetail;
import java.util.List;

/**
 * @author buer
 * @version 2018-01-10 16:15
 */
public class SalesOrderWmsDeliveryBO extends VersionBO {

  private DispatchOrder dispatchOrder;
  private List<DispatchOrderDetail> details;
  private List<DispatchOrderDelivery> deliveries;
  private boolean expressNoUpdated;

  public List<DispatchOrderDetail> getDetails() {
    return details;
  }

  public void setDetails(List<DispatchOrderDetail> details) {
    this.details = details;
  }

  public DispatchOrder getDispatchOrder() {
    return dispatchOrder;
  }

  public void setDispatchOrder(DispatchOrder dispatchOrder) {
    this.dispatchOrder = dispatchOrder;
  }

  public List<DispatchOrderDelivery> getDeliveries() {
    return deliveries;
  }

  public void setDeliveries(List<DispatchOrderDelivery> deliveries) {
    this.deliveries = deliveries;
  }

  public boolean isExpressNoUpdated() {
    return expressNoUpdated;
  }

  public void setExpressNoUpdated(boolean expressNoUpdated) {
    this.expressNoUpdated = expressNoUpdated;
  }
}
