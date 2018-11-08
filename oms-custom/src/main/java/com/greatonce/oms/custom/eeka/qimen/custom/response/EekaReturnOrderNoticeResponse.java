package com.greatonce.oms.custom.eeka.qimen.custom.response;

import com.greatonce.oms.bridge.wms.qimen.response.OmsQimenCustomResponse;
import com.greatonce.oms.domain.trade.ReturnOrder;
import java.util.List;

public class EekaReturnOrderNoticeResponse extends OmsQimenCustomResponse {

  public EekaReturnOrderNoticeResponse(Long requestId) {
    super(requestId);
  }

  public EekaReturnOrderNoticeResponse(Long requestId,String message) {
    super(requestId,message);
  }

  private Integer orderQuantity;

  private Integer skuQuantity;

  private List<ReturnOrder> returnOrders;

  public Integer getOrderQuantity() {
    return orderQuantity;
  }

  public void setOrderQuantity(Integer orderQuantity) {
    this.orderQuantity = orderQuantity;
  }

  public Integer getSkuQuantity() {
    return skuQuantity;
  }

  public void setSkuQuantity(Integer skuQuantity) {
    this.skuQuantity = skuQuantity;
  }

  public List<ReturnOrder> getReturnOrders() {
    return returnOrders;
  }

  public void setReturnOrders(List<ReturnOrder> returnOrders) {
    this.returnOrders = returnOrders;
  }
}
