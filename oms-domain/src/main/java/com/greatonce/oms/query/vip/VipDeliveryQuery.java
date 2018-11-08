package com.greatonce.oms.query.vip;

import com.greatonce.core.database.Query;
import com.greatonce.oms.domain.enums.vip.VipDeliveryStatus;
import com.greatonce.oms.domain.enums.vip.VipJitType;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 唯品发货单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class VipDeliveryQuery extends Query {
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
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
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
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
  /**
   * po编码.
   */
  private String poCode;
  /**
   * .
   */
  private List<String> poCodes;
  /**
   * 备注.
   */
  private String remark;
  /**
   * 状态.
   */
  private VipDeliveryStatus status;
  /**
   * .
   */
  private List<VipDeliveryStatus> statuses;
  /**
   * 唯品入库单号.
   */
  private String storageNo;
  /**
   * .
   */
  private List<String> storageNos;
  /**
   * 店铺id.
   */
  private Long storeId;
  /**
   * .
   */
  private List<Long> storeIds;
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
   * .
   */
  private List<String> vipDeliveryCodes;
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
  /**
   * .
   */
  private List<String> waybillNumbers;


  /**
   * 预计到货日期.
   * @param arrivalDate 预计到货日期
   */
  public void setArrivalDate(String arrivalDate) {
    this.arrivalDate = arrivalDate == null ? null : arrivalDate.trim();
  }

  /**
   * 预计到货日期.
   * @return 预计到货日期
   */
  public String getArrivalDate() {
      return this.arrivalDate;
  }

  /**
   * 预计到货时间.
   * @param arrivalTime 预计到货时间
   */
  public void setArrivalTime(String arrivalTime) {
    this.arrivalTime = arrivalTime == null ? null : arrivalTime.trim();
  }

  /**
   * 预计到货时间.
   * @return 预计到货时间
   */
  public String getArrivalTime() {
      return this.arrivalTime;
  }

  /**
   * 品牌编码.
   * @param brandCode 品牌编码
   */
  public void setBrandCode(String brandCode) {
    this.brandCode = brandCode == null ? null : brandCode.trim();
  }

  /**
   * 品牌编码.
   * @return 品牌编码
   */
  public String getBrandCode() {
      return this.brandCode;
  }

  /**
   * 品牌名称.
   * @param brandName 品牌名称
   */
  public void setBrandName(String brandName) {
    this.brandName = brandName == null ? null : brandName.trim();
  }

  /**
   * 品牌名称.
   * @return 品牌名称
   */
  public String getBrandName() {
      return this.brandName;
  }

  /**
   * 承运商编码.
   * @param carrierCode 承运商编码
   */
  public void setCarrierCode(String carrierCode) {
    this.carrierCode = carrierCode == null ? null : carrierCode.trim();
  }

  /**
   * 承运商编码.
   * @return 承运商编码
   */
  public String getCarrierCode() {
      return this.carrierCode;
  }

  /**
   * 承运商.
   * @param carrierName 承运商
   */
  public void setCarrierName(String carrierName) {
    this.carrierName = carrierName == null ? null : carrierName.trim();
  }

  /**
   * 承运商.
   * @return 承运商
   */
  public String getCarrierName() {
      return this.carrierName;
  }

  /**
   * 创建时间开始.
   * @param createdTimeBegin 开始.
   */
  public void setCreatedTimeBegin(LocalDateTime createdTimeBegin) {
    this.createdTimeBegin = createdTimeBegin;
  }

  /**
   * 创建时间开始.
   * @return 创建时间开始
   */
  public LocalDateTime getCreatedTimeBegin() {
    return this.createdTimeBegin;
  }

  /**
   * 创建时间结束.
   * @param createdTimeEnd 结束
   */
  public void setCreatedTimeEnd(LocalDateTime createdTimeEnd) {
    this.createdTimeEnd = createdTimeEnd;
  }

  /**
   * 创建时间结束.
   * @return 创建时间结束
   */
  public LocalDateTime getCreatedTimeEnd() {
    return this.createdTimeEnd;
  }

  /**
   * 创建人.
   * @param creator 创建人
   */
  public void setCreator(String creator) {
    this.creator = creator == null ? null : creator.trim();
  }

  /**
   * 创建人.
   * @return 创建人
   */
  public String getCreator() {
      return this.creator;
  }

  /**
   * 送货日期.
   * @param deliveryDate 送货日期
   */
  public void setDeliveryDate(String deliveryDate) {
    this.deliveryDate = deliveryDate == null ? null : deliveryDate.trim();
  }

  /**
   * 送货日期.
   * @return 送货日期
   */
  public String getDeliveryDate() {
      return this.deliveryDate;
  }

  /**
   * 配送方式.
   * @param deliveryMethodCode 配送方式
   */
  public void setDeliveryMethodCode(String deliveryMethodCode) {
    this.deliveryMethodCode = deliveryMethodCode == null ? null : deliveryMethodCode.trim();
  }

  /**
   * 配送方式.
   * @return 配送方式
   */
  public String getDeliveryMethodCode() {
      return this.deliveryMethodCode;
  }

  /**
   * 配送方式名称.
   * @param deliveryMethodName 配送方式名称
   */
  public void setDeliveryMethodName(String deliveryMethodName) {
    this.deliveryMethodName = deliveryMethodName == null ? null : deliveryMethodName.trim();
  }

  /**
   * 配送方式名称.
   * @return 配送方式名称
   */
  public String getDeliveryMethodName() {
      return this.deliveryMethodName;
  }

  /**
   * 送货时间.
   * @param deliveryTime 送货时间
   */
  public void setDeliveryTime(String deliveryTime) {
    this.deliveryTime = deliveryTime == null ? null : deliveryTime.trim();
  }

  /**
   * 送货时间.
   * @return 送货时间
   */
  public String getDeliveryTime() {
      return this.deliveryTime;
  }

  /**
   * jit类型.
   * @param jitType jit类型
   */
  public void setJitType(VipJitType jitType) {
    this.jitType = jitType;
  }

  /**
   * jit类型.
   * @return jit类型
   */
  public VipJitType getJitType() {
      return this.jitType;
  }

  /**
   * 更新时间开始.
   * @param modifiedTimeBegin 开始.
   */
  public void setModifiedTimeBegin(LocalDateTime modifiedTimeBegin) {
    this.modifiedTimeBegin = modifiedTimeBegin;
  }

  /**
   * 更新时间开始.
   * @return 更新时间开始
   */
  public LocalDateTime getModifiedTimeBegin() {
    return this.modifiedTimeBegin;
  }

  /**
   * 更新时间结束.
   * @param modifiedTimeEnd 结束
   */
  public void setModifiedTimeEnd(LocalDateTime modifiedTimeEnd) {
    this.modifiedTimeEnd = modifiedTimeEnd;
  }

  /**
   * 更新时间结束.
   * @return 更新时间结束
   */
  public LocalDateTime getModifiedTimeEnd() {
    return this.modifiedTimeEnd;
  }

  /**
   * po编码.
   * @param poCode po编码
   */
  public void setPoCode(String poCode) {
    this.poCode = poCode == null ? null : poCode.trim();
  }

  /**
   * po编码.
   * @return po编码
   */
  public String getPoCode() {
      return this.poCode;
  }

  /**
   * .
   * @param poCodes 
   */
  public void setPoCodes(List<String> poCodes) {
    this.poCodes = poCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> setPoCodes() {
      return this.poCodes;
  }

  /**
   * 备注.
   * @param remark 备注
   */
  public void setRemark(String remark) {
    this.remark = remark == null ? null : remark.trim();
  }

  /**
   * 备注.
   * @return 备注
   */
  public String getRemark() {
      return this.remark;
  }

  /**
   * 状态.
   * @param status 状态
   */
  public void setStatus(VipDeliveryStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   * @return 状态
   */
  public VipDeliveryStatus getStatus() {
      return this.status;
  }

  /**
   * .
   * @param statuses 
   */
  public void setStatuses(List<VipDeliveryStatus> statuses) {
    this.statuses = statuses;
  }

  /**
   * .
   * @return 
   */
  public List<VipDeliveryStatus> getStatuses() {
      return this.statuses;
  }

  /**
   * 唯品入库单号.
   * @param storageNo 唯品入库单号
   */
  public void setStorageNo(String storageNo) {
    this.storageNo = storageNo == null ? null : storageNo.trim();
  }

  /**
   * 唯品入库单号.
   * @return 唯品入库单号
   */
  public String getStorageNo() {
      return this.storageNo;
  }

  /**
   * .
   * @param storageNos 
   */
  public void setStorageNos(List<String> storageNos) {
    this.storageNos = storageNos;
  }

  /**
   * .
   * @return 
   */
  public List<String> getStorageNos() {
      return this.storageNos;
  }

  /**
   * 店铺id.
   * @param storeId 店铺id
   */
  public void setStoreId(Long storeId) {
    this.storeId = storeId;
  }

  /**
   * 店铺id.
   * @return 店铺id
   */
  public Long getStoreId() {
      return this.storeId;
  }

  /**
   * .
   * @param storeIds 
   */
  public void setStoreIds(List<Long> storeIds) {
    this.storeIds = storeIds;
  }

  /**
   * .
   * @return 
   */
  public List<Long> getStoreIds() {
      return this.storeIds;
  }

  /**
   * 店铺名称.
   * @param storeName 店铺名称
   */
  public void setStoreName(String storeName) {
    this.storeName = storeName == null ? null : storeName.trim();
  }

  /**
   * 店铺名称.
   * @return 店铺名称
   */
  public String getStoreName() {
      return this.storeName;
  }

  /**
   * 版本.
   * @param version 版本
   */
  public void setVersion(Integer version) {
    this.version = version;
  }

  /**
   * 版本.
   * @return 版本
   */
  public Integer getVersion() {
      return this.version;
  }

  /**
   * 发货单编码.
   * @param vipDeliveryCode 发货单编码
   */
  public void setVipDeliveryCode(String vipDeliveryCode) {
    this.vipDeliveryCode = vipDeliveryCode == null ? null : vipDeliveryCode.trim();
  }

  /**
   * 发货单编码.
   * @return 发货单编码
   */
  public String getVipDeliveryCode() {
      return this.vipDeliveryCode;
  }

  /**
   * .
   * @param vipDeliveryCodes 
   */
  public void setVipDeliveryCodes(List<String> vipDeliveryCodes) {
    this.vipDeliveryCodes = vipDeliveryCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getVipDeliveryCodes() {
      return this.vipDeliveryCodes;
  }

  /**
   * 发货单id.
   * @param vipDeliveryId 发货单id
   */
  public void setVipDeliveryId(Long vipDeliveryId) {
    this.vipDeliveryId = vipDeliveryId;
  }

  /**
   * 发货单id.
   * @return 发货单id
   */
  public Long getVipDeliveryId() {
      return this.vipDeliveryId;
  }

  /**
   * 唯品仓编码.
   * @param vipWarehouseCode 唯品仓编码
   */
  public void setVipWarehouseCode(String vipWarehouseCode) {
    this.vipWarehouseCode = vipWarehouseCode == null ? null : vipWarehouseCode.trim();
  }

  /**
   * 唯品仓编码.
   * @return 唯品仓编码
   */
  public String getVipWarehouseCode() {
      return this.vipWarehouseCode;
  }

  /**
   * 唯品仓名称.
   * @param vipWarehouseName 唯品仓名称
   */
  public void setVipWarehouseName(String vipWarehouseName) {
    this.vipWarehouseName = vipWarehouseName == null ? null : vipWarehouseName.trim();
  }

  /**
   * 唯品仓名称.
   * @return 唯品仓名称
   */
  public String getVipWarehouseName() {
      return this.vipWarehouseName;
  }

  /**
   * 运单号.
   * @param waybillNumber 运单号
   */
  public void setWaybillNumber(String waybillNumber) {
    this.waybillNumber = waybillNumber == null ? null : waybillNumber.trim();
  }

  /**
   * 运单号.
   * @return 运单号
   */
  public String getWaybillNumber() {
      return this.waybillNumber;
  }

  /**
   * .
   * @param waybillNumbers 
   */
  public void setWaybillNumbers(List<String> waybillNumbers) {
    this.waybillNumbers = waybillNumbers;
  }

  /**
   * .
   * @return 
   */
  public List<String> getWaybillNumbers() {
      return this.waybillNumbers;
  }
}