package com.greatonce.oms.bo.trade;

/**
 * @author buer
 * @version 2018-01-06 10:31
 */
public class ManualDispatchBO extends DispatchBO<ManualDispatchDetailBO> {

  private Long virtualWarehouseId;
  private String virtualWarehouseName;
  private Long expressId;
  private String expressName;
  private String encryptContact;
  private String encryptMobile;
  private String encryptTelephone;

  public Long getVirtualWarehouseId() {
    return virtualWarehouseId;
  }

  public void setVirtualWarehouseId(Long virtualWarehouseId) {
    this.virtualWarehouseId = virtualWarehouseId;
  }

  public String getVirtualWarehouseName() {
    return virtualWarehouseName;
  }

  public void setVirtualWarehouseName(String virtualWarehouseName) {
    this.virtualWarehouseName = virtualWarehouseName;
  }

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

  public String getEncryptContact() {
    return encryptContact;
  }

  public void setEncryptContact(String encryptContact) {
    this.encryptContact = encryptContact;
  }

  public String getEncryptMobile() {
    return encryptMobile;
  }

  public void setEncryptMobile(String encryptMobile) {
    this.encryptMobile = encryptMobile;
  }

  public String getEncryptTelephone() {
    return encryptTelephone;
  }

  public void setEncryptTelephone(String encryptTelephone) {
    this.encryptTelephone = encryptTelephone;
  }
}
