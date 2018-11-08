package com.greatonce.oms.bridge.mall.impl.vip.response;

import com.greatonce.oms.bridge.mall.impl.vip.request.VipPickOrderDetailQueryRequest;
import com.greatonce.oms.bridge.mall.response.MallResponse;

import java.util.List;

/**
 * @author buer
 * @version 2017-08-24 15:25
 */
public class VipPickOrderDetailQueryResponse extends MallResponse<VipPickOrderDetailQueryRequest> {

  private boolean hasNext;
  private List<VipPickOrderDetail> details;

  public VipPickOrderDetailQueryResponse(VipPickOrderDetailQueryRequest request) {
    super(request);
  }

  public VipPickOrderDetailQueryResponse(VipPickOrderDetailQueryRequest request, boolean isSuccess,
      String result) {
    super(request, isSuccess, result);
  }

  public boolean isHasNext() {
    return hasNext;
  }

  public void setHasNext(boolean hasNext) {
    this.hasNext = hasNext;
  }

  public List<VipPickOrderDetail> getDetails() {
    return details;
  }

  public void setDetails(List<VipPickOrderDetail> details) {
    this.details = details;
  }

  public static class VipPickOrderDetail {

    private String po;
    private String sn;
    private String barcode;
    private Integer quantity;
    private Integer noDeliveryQuantity;

    public String getPo() {
      return po;
    }

    public void setPo(String po) {
      this.po = po;
    }

    public String getSn() {
      return sn;
    }

    public void setSn(String sn) {
      this.sn = sn;
    }

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

    public Integer getNoDeliveryQuantity() {
      return noDeliveryQuantity;
    }

    public void setNoDeliveryQuantity(Integer noDeliveryQuantity) {
      this.noDeliveryQuantity = noDeliveryQuantity;
    }
  }

}
