package com.greatonce.oms.bo.trade;

import com.greatonce.oms.bo.VersionBO;

/**
 * @author buer
 * @version 2018-01-08 15:45
 */
public class ManualDeliveryBO extends VersionBO {

  private Long expressId;
  private String expressName;
  private String expressNo;
  private String boxNo;
  private String outCode;
  private Long warehouseId;

  public Long getExpressId() {
    return expressId;
  }

  public void setExpressId(Long expressId) {
    this.expressId = expressId;
  }

  public String getExpressName() {
    return expressName;
  }

  public void setExpressName(String expressName) {
    this.expressName = expressName;
  }

  public String getExpressNo() {
    return expressNo;
  }

  public void setExpressNo(String expressNo) {
    this.expressNo = expressNo;
  }

  public String getBoxNo() {
    return boxNo;
  }

  public void setBoxNo(String boxNo) {
    this.boxNo = boxNo;
  }

  public String getOutCode() {
    return outCode;
  }

  public void setOutCode(String outCode) {
    this.outCode = outCode;
  }

  public Long getWarehouseId() {
    return warehouseId;
  }

  public void setWarehouseId(Long warehouseId) {
    this.warehouseId = warehouseId;
  }
}
