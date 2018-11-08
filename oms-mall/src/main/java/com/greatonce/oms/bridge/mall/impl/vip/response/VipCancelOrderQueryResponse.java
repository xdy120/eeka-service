package com.greatonce.oms.bridge.mall.impl.vip.response;

import com.greatonce.oms.bridge.mall.impl.vip.request.VipCancelOrderQueryRequest;
import com.greatonce.oms.bridge.mall.response.MallResponse;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author buer
 * @version 2017-08-24 15:05
 */
public class VipCancelOrderQueryResponse extends MallResponse<VipCancelOrderQueryRequest> {

  private boolean hasNext;
  private List<VipOrder> orders;

  public VipCancelOrderQueryResponse(VipCancelOrderQueryRequest request) {
    super(request);
  }

  public VipCancelOrderQueryResponse(VipCancelOrderQueryRequest request, boolean isSuccess,
      String result) {
    super(request, isSuccess, result);
  }

  public boolean isHasNext() {
    return hasNext;
  }

  public void setHasNext(boolean hasNext) {
    this.hasNext = hasNext;
  }

  public List<VipOrder> getOrders() {
    return orders;
  }

  public void setOrders(List<VipOrder> orders) {
    this.orders = orders;
  }

  public Integer getCount() {
    return orders == null ? 0 : orders.size();
  }

  public static class VipOrder {

    private String orderNo;
    private List<VipOrderDetail> details;

    public String getOrderNo() {
      return orderNo;
    }

    public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
    }

    public List<VipOrderDetail> getDetails() {
      return details;
    }

    public void setDetails(List<VipOrderDetail> details) {
      this.details = details;
    }
  }

  public static class VipOrderDetail {

    private String barcode;
    private Integer quantity;
    private LocalDateTime payTime;
    private String salesNo;
    private VipSalesSourceType salesSource;

    public String getBarcode() {
      return barcode;
    }

    public void setBarcode(String barcode) {
      this.barcode = barcode;
    }

    public Integer getQuantity() {
      return quantity;
    }

    public void setQuantity(Integer quantity) {
      this.quantity = quantity;
    }

    public LocalDateTime getPayTime() {
      return payTime;
    }

    public void setPayTime(LocalDateTime payTime) {
      this.payTime = payTime;
    }

    public String getSalesNo() {
      return salesNo;
    }

    public void setSalesNo(String salesNo) {
      this.salesNo = salesNo;
    }

    public VipSalesSourceType getSalesSource() {
      return salesSource;
    }

    public void setSalesSource(VipSalesSourceType salesSource) {
      this.salesSource = salesSource;
    }
  }
}
