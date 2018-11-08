package com.greatonce.oms.bridge.mall.impl.vip.request;

import com.greatonce.oms.bridge.mall.request.MallRequest;
import com.greatonce.oms.domain.base.Store;
import java.util.List;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/5/18
 */
public class VipDeliveryImportRequest extends MallRequest {

  public VipDeliveryImportRequest(Store store) {
    super(store);
  }

  private String storageNo;
  private String poCode;
  private List<VipDeliveryDetail> details;

  public String getStorageNo() {
    return storageNo;
  }

  public void setStorageNo(String storageNo) {
    this.storageNo = storageNo;
  }

  public String getPoCode() {
    return poCode;
  }

  public void setPoCode(String poCode) {
    this.poCode = poCode;
  }

  public List<VipDeliveryDetail> getDetails() {
    return details;
  }

  public void setDetails(List<VipDeliveryDetail> details) {
    this.details = details;
  }

  public static class VipDeliveryDetail {

    private String skuCode;
    private String vipSkuCode;
    private String boxNo;
    private String pickNo;
    private Integer quantity;
    private String poCode;

    public String getSkuCode() {
      return skuCode;
    }

    public void setSkuCode(String skuCode) {
      this.skuCode = skuCode;
    }

    public String getVipSkuCode() {
      return vipSkuCode;
    }

    public void setVipSkuCode(String vipSkuCode) {
      this.vipSkuCode = vipSkuCode;
    }

    public String getBoxNo() {
      return boxNo;
    }

    public void setBoxNo(String boxNo) {
      this.boxNo = boxNo;
    }

    public String getPickNo() {
      return pickNo;
    }

    public void setPickNo(String pickNo) {
      this.pickNo = pickNo;
    }

    public Integer getQuantity() {
      return quantity;
    }

    public void setQuantity(Integer quantity) {
      this.quantity = quantity;
    }

    public String getPoCode() {
      return poCode;
    }

    public void setPoCode(String poCode) {
      this.poCode = poCode;
    }
  }
}
