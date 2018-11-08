package com.greatonce.oms.bridge.mall.impl.vip.response;

import com.greatonce.oms.bridge.mall.impl.vip.request.VipPickOrderQueryRequest;
import com.greatonce.oms.bridge.mall.response.MallResponse;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author buer
 * @version 2017-08-24 15:20
 */
public class VipPickOrderQueryResponse extends MallResponse<VipPickOrderQueryRequest> {

  private boolean hasNext;
  private List<VipPickOrder> orders;

  public VipPickOrderQueryResponse(VipPickOrderQueryRequest request) {
    super(request);
  }

  public VipPickOrderQueryResponse(VipPickOrderQueryRequest request, boolean isSuccess,
      String result) {
    super(request, isSuccess, result);
  }

  public boolean isHasNext() {
    return hasNext;
  }

  public void setHasNext(boolean hasNext) {
    this.hasNext = hasNext;
  }

  public List<VipPickOrder> getOrders() {
    return orders;
  }

  public void setOrders(List<VipPickOrder> orders) {
    this.orders = orders;
  }

  public Integer getCount() {
    return orders == null ? 0 : orders.size();
  }

  public static class VipPickOrder {

    private String po;
    private String pickNo;
    private String warehouse;
    private LocalDateTime createTime;

    public String getPo() {
      return po;
    }

    public void setPo(String po) {
      this.po = po;
    }

    public String getPickNo() {
      return pickNo;
    }

    public void setPickNo(String pickNo) {
      this.pickNo = pickNo;
    }

    public String getWarehouse() {
      return warehouse;
    }

    public void setWarehouse(String warehouse) {
      this.warehouse = warehouse;
    }

    public LocalDateTime getCreateTime() {
      return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
      this.createTime = createTime;
    }
  }
}
