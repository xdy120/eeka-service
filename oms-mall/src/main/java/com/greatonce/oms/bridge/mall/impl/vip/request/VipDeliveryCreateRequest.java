package com.greatonce.oms.bridge.mall.impl.vip.request;

import com.greatonce.oms.bridge.mall.request.MallRequest;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.enums.vip.VipJitType;
import java.time.LocalDateTime;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/5/18
 */
public class VipDeliveryCreateRequest extends MallRequest {

  public VipDeliveryCreateRequest(Store store) {
    super(store);
  }

  /**
   * 预计到货日期
   **/
  private String arrivalDate;
  /**
   * 预计到货时间
   **/
  private String arrivalTime;
  /**
   * 品牌编码
   **/
  private String brandCode;
  /**
   * 品牌名称
   **/
  private String brandName;
  /**
   * 承运商
   **/
  private String carrierName;
  /**
   * 承运商编码
   **/
  private String carrierCode;
  /**
   * 送货时间
   **/
  private String deliveryDate;
  /**
   * 送货时间
   **/
  private String deliveryTime;
  /**
   * 配送方式
   **/
  private String deliveryMethod;
  /**
   * 更新时间
   **/
  private LocalDateTime modifiedTime;
  /**
   * po编码
   **/
  private String poCode;
  /**
   * 唯品仓编码
   **/
  private String vipWarehouseCode;
  /**
   * 唯品仓名称
   **/
  private String vipWarehouseName;
  /**
   * 运单号
   **/
  private String waybillNumber;
  /**
   * jit类型
   */
  private VipJitType jitType;

  public String getArrivalDate() {
    return arrivalDate;
  }

  public void setArrivalDate(String arrivalDate) {
    this.arrivalDate = arrivalDate;
  }

  public String getArrivalTime() {
    return arrivalTime;
  }

  public void setArrivalTime(String arrivalTime) {
    this.arrivalTime = arrivalTime;
  }

  public String getBrandCode() {
    return brandCode;
  }

  public void setBrandCode(String brandCode) {
    this.brandCode = brandCode;
  }

  public String getBrandName() {
    return brandName;
  }

  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }

  public String getCarrierName() {
    return carrierName;
  }

  public void setCarrierName(String carrierName) {
    this.carrierName = carrierName;
  }

  public String getCarrierCode() {
    return carrierCode;
  }

  public void setCarrierCode(String carrierCode) {
    this.carrierCode = carrierCode;
  }

  public String getDeliveryDate() {
    return deliveryDate;
  }

  public void setDeliveryDate(String deliveryDate) {
    this.deliveryDate = deliveryDate;
  }

  public String getDeliveryTime() {
    return deliveryTime;
  }

  public void setDeliveryTime(String deliveryTime) {
    this.deliveryTime = deliveryTime;
  }

  public String getDeliveryMethod() {
    return deliveryMethod;
  }

  public void setDeliveryMethod(String deliveryMethod) {
    this.deliveryMethod = deliveryMethod;
  }

  public LocalDateTime getModifiedTime() {
    return modifiedTime;
  }

  public void setModifiedTime(LocalDateTime modifiedTime) {
    this.modifiedTime = modifiedTime;
  }

  public String getPoCode() {
    return poCode;
  }

  public void setPoCode(String poCode) {
    this.poCode = poCode;
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

  public String getWaybillNumber() {
    return waybillNumber;
  }

  public void setWaybillNumber(String waybillNumber) {
    this.waybillNumber = waybillNumber;
  }

  public VipJitType getJitType() {
    return jitType;
  }

  public void setJitType(VipJitType jitType) {
    this.jitType = jitType;
  }
}
