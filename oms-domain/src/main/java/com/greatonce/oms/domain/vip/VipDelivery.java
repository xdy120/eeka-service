package com.greatonce.oms.domain.vip;

import com.greatonce.oms.domain.VersionDO;
import com.greatonce.oms.domain.enums.vip.VipDeliveryStatus;
import com.greatonce.oms.domain.enums.vip.VipJitType;
import java.time.LocalDateTime;

/**
 * 唯品发货单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class VipDelivery extends VersionDO {
  /**
   * 预计到货日期.
   */
  private String arrivalDate;
  /**
   * 预计到货时间.
   */
  private String arrivalTime;
  /**
   * 品牌编码.
   */
  private String brandCode;
  /**
   * 品牌名称.
   */
  private String brandName;
  /**
   * 承运商编码.
   */
  private String carrierCode;
  /**
   * 承运商.
   */
  private String carrierName;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 创建人.
   */
  private String creator;
  /**
   * 送货日期.
   */
  private String deliveryDate;
  /**
   * 配送方式.
   */
  private String deliveryMethodCode;
  /**
   * 配送方式名称.
   */
  private String deliveryMethodName;
  /**
   * 送货时间.
   */
  private String deliveryTime;
  /**
   * jit类型.
   */
  private VipJitType jitType;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * po编码.
   */
  private String poCode;
  /**
   * 备注.
   */
  private String remark;
  /**
   * 状态.
   */
  private VipDeliveryStatus status;
  /**
   * 唯品入库单号.
   */
  private String storageNo;
  /**
   * 店铺id.
   */
  private Long storeId;
  /**
   * 店铺名称.
   */
  private String storeName;
  /**
   * 版本.
   */
  private Integer version;
  /**
   * 发货单编码.
   */
  private String vipDeliveryCode;
  /**
   * 发货单id.
   */
  private Long vipDeliveryId;
  /**
   * 唯品仓编码.
   */
  private String vipWarehouseCode;
  /**
   * 唯品仓名称.
   */
  private String vipWarehouseName;
  /**
   * 运单号.
   */
  private String waybillNumber;


  @Override
  public void setPrimaryKey(Long pk) {
    this.vipDeliveryId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.vipDeliveryId;
  }


  /**
   * 预计到货日期.
   */
  public void setArrivalDate(String arrivalDate) {
    this.arrivalDate = arrivalDate == null ? null : arrivalDate.trim();
  }

  /**
   * 预计到货日期.
   */
  public String getArrivalDate() {
    return this.arrivalDate;
  }

  /**
   * 预计到货时间.
   */
  public void setArrivalTime(String arrivalTime) {
    this.arrivalTime = arrivalTime == null ? null : arrivalTime.trim();
  }

  /**
   * 预计到货时间.
   */
  public String getArrivalTime() {
    return this.arrivalTime;
  }

  /**
   * 品牌编码.
   */
  public void setBrandCode(String brandCode) {
    this.brandCode = brandCode == null ? null : brandCode.trim();
  }

  /**
   * 品牌编码.
   */
  public String getBrandCode() {
    return this.brandCode;
  }

  /**
   * 品牌名称.
   */
  public void setBrandName(String brandName) {
    this.brandName = brandName == null ? null : brandName.trim();
  }

  /**
   * 品牌名称.
   */
  public String getBrandName() {
    return this.brandName;
  }

  /**
   * 承运商编码.
   */
  public void setCarrierCode(String carrierCode) {
    this.carrierCode = carrierCode == null ? null : carrierCode.trim();
  }

  /**
   * 承运商编码.
   */
  public String getCarrierCode() {
    return this.carrierCode;
  }

  /**
   * 承运商.
   */
  public void setCarrierName(String carrierName) {
    this.carrierName = carrierName == null ? null : carrierName.trim();
  }

  /**
   * 承运商.
   */
  public String getCarrierName() {
    return this.carrierName;
  }

  /**
   * 创建时间.
   */
  @Override
  public void setCreatedTime(LocalDateTime createdTime) {
    this.createdTime = createdTime;
  }

  /**
   * 创建时间.
   */
  @Override
  public LocalDateTime getCreatedTime() {
    return this.createdTime;
  }

  /**
   * 创建人.
   */
  public void setCreator(String creator) {
    this.creator = creator == null ? null : creator.trim();
  }

  /**
   * 创建人.
   */
  public String getCreator() {
    return this.creator;
  }

  /**
   * 送货日期.
   */
  public void setDeliveryDate(String deliveryDate) {
    this.deliveryDate = deliveryDate == null ? null : deliveryDate.trim();
  }

  /**
   * 送货日期.
   */
  public String getDeliveryDate() {
    return this.deliveryDate;
  }

  /**
   * 配送方式.
   */
  public void setDeliveryMethodCode(String deliveryMethodCode) {
    this.deliveryMethodCode = deliveryMethodCode == null ? null : deliveryMethodCode.trim();
  }

  /**
   * 配送方式.
   */
  public String getDeliveryMethodCode() {
    return this.deliveryMethodCode;
  }

  /**
   * 配送方式名称.
   */
  public void setDeliveryMethodName(String deliveryMethodName) {
    this.deliveryMethodName = deliveryMethodName == null ? null : deliveryMethodName.trim();
  }

  /**
   * 配送方式名称.
   */
  public String getDeliveryMethodName() {
    return this.deliveryMethodName;
  }

  /**
   * 送货时间.
   */
  public void setDeliveryTime(String deliveryTime) {
    this.deliveryTime = deliveryTime == null ? null : deliveryTime.trim();
  }

  /**
   * 送货时间.
   */
  public String getDeliveryTime() {
    return this.deliveryTime;
  }

  /**
   * jit类型.
   */
  public void setJitType(VipJitType jitType) {
    this.jitType = jitType;
  }

  /**
   * jit类型.
   */
  public VipJitType getJitType() {
    return this.jitType;
  }

  /**
   * 更新时间.
   */
  @Override
  public void setModifiedTime(LocalDateTime modifiedTime) {
    this.modifiedTime = modifiedTime;
  }

  /**
   * 更新时间.
   */
  @Override
  public LocalDateTime getModifiedTime() {
    return this.modifiedTime;
  }

  /**
   * po编码.
   */
  public void setPoCode(String poCode) {
    this.poCode = poCode == null ? null : poCode.trim();
  }

  /**
   * po编码.
   */
  public String getPoCode() {
    return this.poCode;
  }


  /**
   * 备注.
   */
  public void setRemark(String remark) {
    this.remark = remark == null ? null : remark.trim();
  }

  /**
   * 备注.
   */
  public String getRemark() {
    return this.remark;
  }

  /**
   * 状态.
   */
  public void setStatus(VipDeliveryStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   */
  public VipDeliveryStatus getStatus() {
    return this.status;
  }


  /**
   * 唯品入库单号.
   */
  public void setStorageNo(String storageNo) {
    this.storageNo = storageNo == null ? null : storageNo.trim();
  }

  /**
   * 唯品入库单号.
   */
  public String getStorageNo() {
    return this.storageNo;
  }


  /**
   * 店铺id.
   */
  public void setStoreId(Long storeId) {
    this.storeId = storeId;
  }

  /**
   * 店铺id.
   */
  public Long getStoreId() {
    return this.storeId;
  }


  /**
   * 店铺名称.
   */
  public void setStoreName(String storeName) {
    this.storeName = storeName == null ? null : storeName.trim();
  }

  /**
   * 店铺名称.
   */
  public String getStoreName() {
    return this.storeName;
  }

  /**
   * 版本.
   */
  @Override
  public void setVersion(Integer version) {
    this.version = version;
  }

  /**
   * 版本.
   */
  @Override
  public Integer getVersion() {
    return this.version;
  }

  /**
   * 发货单编码.
   */
  public void setVipDeliveryCode(String vipDeliveryCode) {
    this.vipDeliveryCode = vipDeliveryCode == null ? null : vipDeliveryCode.trim();
  }

  /**
   * 发货单编码.
   */
  public String getVipDeliveryCode() {
    return this.vipDeliveryCode;
  }


  /**
   * 发货单id.
   */
  public void setVipDeliveryId(Long vipDeliveryId) {
    this.vipDeliveryId = vipDeliveryId;
  }

  /**
   * 发货单id.
   */
  public Long getVipDeliveryId() {
    return this.vipDeliveryId;
  }

  /**
   * 唯品仓编码.
   */
  public void setVipWarehouseCode(String vipWarehouseCode) {
    this.vipWarehouseCode = vipWarehouseCode == null ? null : vipWarehouseCode.trim();
  }

  /**
   * 唯品仓编码.
   */
  public String getVipWarehouseCode() {
    return this.vipWarehouseCode;
  }

  /**
   * 唯品仓名称.
   */
  public void setVipWarehouseName(String vipWarehouseName) {
    this.vipWarehouseName = vipWarehouseName == null ? null : vipWarehouseName.trim();
  }

  /**
   * 唯品仓名称.
   */
  public String getVipWarehouseName() {
    return this.vipWarehouseName;
  }

  /**
   * 运单号.
   */
  public void setWaybillNumber(String waybillNumber) {
    this.waybillNumber = waybillNumber == null ? null : waybillNumber.trim();
  }

  /**
   * 运单号.
   */
  public String getWaybillNumber() {
    return this.waybillNumber;
  }

}