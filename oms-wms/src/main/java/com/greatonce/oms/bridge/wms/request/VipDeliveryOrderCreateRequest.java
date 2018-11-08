package com.greatonce.oms.bridge.wms.request;

import com.greatonce.oms.domain.base.Warehouse;

/**
 * 唯品会发货单创建请求.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-03
 */
public class VipDeliveryOrderCreateRequest extends StockOutOrderCreateRequest {

  private String vipArrivalTime;
  private String vipPickingCode;
  private String vipStorageNo;
  private String vipDeliveryMethod;
  private String vipWarehouseCode;
  private String vipWarehouseName;
  private String vipCarrierName;

  public VipDeliveryOrderCreateRequest(Warehouse warehouse) {
    super(warehouse);
  }

  public String getVipArrivalTime() {
    return vipArrivalTime;
  }

  public void setVipArrivalTime(String vipArrivalTime) {
    this.vipArrivalTime = vipArrivalTime;
  }

  public String getVipPickingCode() {
    return vipPickingCode;
  }

  public void setVipPickingCode(String vipPickingCode) {
    this.vipPickingCode = vipPickingCode;
  }

  public String getVipStorageNo() {
    return vipStorageNo;
  }

  public void setVipStorageNo(String vipStorageNo) {
    this.vipStorageNo = vipStorageNo;
  }

  public String getVipDeliveryMethod() {
    return vipDeliveryMethod;
  }

  public void setVipDeliveryMethod(String vipDeliveryMethod) {
    this.vipDeliveryMethod = vipDeliveryMethod;
  }

  public String getVipWarehouseCode() {
    return vipWarehouseCode;
  }

  public void setVipWarehouseCode(String vipWarehouseCode) {
    this.vipWarehouseCode = vipWarehouseCode;
  }

  public String getVipWarehouseName() {
    return vipWarehouseName;
  }

  public void setVipWarehouseName(String vipWarehouseName) {
    this.vipWarehouseName = vipWarehouseName;
  }

  public String getVipCarrierName() {
    return vipCarrierName;
  }

  public void setVipCarrierName(String vipCarrierName) {
    this.vipCarrierName = vipCarrierName;
  }
}
