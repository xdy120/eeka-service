package com.greatonce.oms.custom.eeka.qimen.custom.request;

import com.greatonce.oms.bridge.wms.qimen.request.OmsQimenCustomRequest;
import java.util.List;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/21
 */
public class EekaVipReturnScanRequest extends OmsQimenCustomRequest {

  private String operator;

  private Long vipReturnId;

  private List<ScanDetail> details;

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public Long getVipReturnId() {
    return vipReturnId;
  }

  public void setVipReturnId(Long vipReturnId) {
    this.vipReturnId = vipReturnId;
  }

  public List<ScanDetail> getDetails() {
    return details;
  }

  public void setDetails(List<ScanDetail> details) {
    this.details = details;
  }

  public static class ScanDetail {

    private String vipReturnDetailId;
    private String boxNo;
    private String skuCode;
    private String scanQuantity;

    public String getVipReturnDetailId() {
      return vipReturnDetailId;
    }

    public void setVipReturnDetailId(String vipReturnDetailId) {
      this.vipReturnDetailId = vipReturnDetailId;
    }

    public String getBoxNo() {
      return boxNo;
    }

    public void setBoxNo(String boxNo) {
      this.boxNo = boxNo;
    }

    public String getSkuCode() {
      return skuCode;
    }

    public void setSkuCode(String skuCode) {
      this.skuCode = skuCode;
    }

    public String getScanQuantity() {
      return scanQuantity;
    }

    public void setScanQuantity(String scanQuantity) {
      this.scanQuantity = scanQuantity;
    }

    @Override
    public String toString() {
      return "skus{" +
          ", vipReturnDetailId='" + vipReturnDetailId + '\'' +
          ", boxNo=" + boxNo +
          ", skuCode=" + skuCode +
          ", scanQuantity=" + scanQuantity +
          '}';
    }
  }
}
