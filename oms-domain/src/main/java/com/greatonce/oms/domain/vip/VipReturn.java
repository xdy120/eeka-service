package com.greatonce.oms.domain.vip;

import com.greatonce.oms.domain.VersionDO;
import com.greatonce.oms.domain.enums.vip.VipReturnStatus;
import com.greatonce.oms.domain.enums.vip.VipSignStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 唯品退供单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class VipReturn extends VersionDO {
  /**
   * 箱数.
   */
  private Integer boxQuantity;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 创建人.
   */
  private String creator;
  /**
   * 是否异常.
   */
  private Boolean abnormal;
  /**
   * 唯品价异常.
   */
  private Boolean vipPriceAbnormal;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 外部单号.
   */
  private String outerCode;
  /**
   * 数量.
   */
  private Integer quantity;
  /**
   * 备注.
   */
  private String remark;
  /**
   * 退货时间.
   */
  private LocalDateTime returnTime;
  /**
   * 扫描数量.
   */
  private Integer scanQuantity;
  /**
   * 签收时间.
   */
  private LocalDateTime signTime;
  /**
   * 签收人.
   */
  private String signer;
  /**
   * 规格数量.
   */
  private Integer skuQuantity;
  /**
   * 状态.
   */
  private VipReturnStatus status;
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
   * 金额.
   */
  private Double vipAmount;
  /**
   * 唯品退供编码.
   */
  private String vipReturnCode;
  /**
   * 唯品退供单id.
   */
  private Long vipReturnId;
  /**
   * 唯品退货类型.
   */
  private String vipReturnType;
  /**
   * 唯品签收类型.
   */
  private VipSignStatus vipSignStatus;
  /**
   * 唯品仓编码.
   */
  private String vipWarehouseCode;
  /**
   * 唯品仓名称.
   */
  private String vipWarehouseName;
  /**
   * 虚拟仓id.
   */
  private Long virtualWarehouseId;
  /**
   * 虚拟仓名称.
   */
  private String virtualWarehouseName;

  /**
   * ${field.comment}.
   */
  private List<VipReturnDetail> details;

  @Override
  public void setPrimaryKey(Long pk) {
    this.vipReturnId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.vipReturnId;
  }


  /**
   * 箱数.
   */
  public void setBoxQuantity(Integer boxQuantity) {
    this.boxQuantity = boxQuantity;
  }

  /**
   * 箱数.
   */
  public Integer getBoxQuantity() {
    return this.boxQuantity;
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
   * 是否异常.
   */
  public void setAbnormal(Boolean abnormal) {
    this.abnormal = abnormal;
  }

  /**
   * 是否异常.
   */
  public Boolean isAbnormal() {
    return this.abnormal;
  }

  /**
   * 唯品价异常.
   */
  public void setVipPriceAbnormal(Boolean vipPriceAbnormal) {
    this.vipPriceAbnormal = vipPriceAbnormal;
  }

  /**
   * 唯品价异常.
   */
  public Boolean isVipPriceAbnormal() {
    return this.vipPriceAbnormal;
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
   * 外部单号.
   */
  public void setOuterCode(String outerCode) {
    this.outerCode = outerCode == null ? null : outerCode.trim();
  }

  /**
   * 外部单号.
   */
  public String getOuterCode() {
    return this.outerCode;
  }


  /**
   * 数量.
   */
  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  /**
   * 数量.
   */
  public Integer getQuantity() {
    return this.quantity;
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
   * 退货时间.
   */
  public void setReturnTime(LocalDateTime returnTime) {
    this.returnTime = returnTime;
  }

  /**
   * 退货时间.
   */
  public LocalDateTime getReturnTime() {
    return this.returnTime;
  }

  /**
   * 扫描数量.
   */
  public void setScanQuantity(Integer scanQuantity) {
    this.scanQuantity = scanQuantity;
  }

  /**
   * 扫描数量.
   */
  public Integer getScanQuantity() {
    return this.scanQuantity;
  }

  /**
   * 签收时间.
   */
  public void setSignTime(LocalDateTime signTime) {
    this.signTime = signTime;
  }

  /**
   * 签收时间.
   */
  public LocalDateTime getSignTime() {
    return this.signTime;
  }

  /**
   * 签收人.
   */
  public void setSigner(String signer) {
    this.signer = signer == null ? null : signer.trim();
  }

  /**
   * 签收人.
   */
  public String getSigner() {
    return this.signer;
  }

  /**
   * 规格数量.
   */
  public void setSkuQuantity(Integer skuQuantity) {
    this.skuQuantity = skuQuantity;
  }

  /**
   * 规格数量.
   */
  public Integer getSkuQuantity() {
    return this.skuQuantity;
  }

  /**
   * 状态.
   */
  public void setStatus(VipReturnStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   */
  public VipReturnStatus getStatus() {
    return this.status;
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
   * 金额.
   */
  public void setVipAmount(Double vipAmount) {
    this.vipAmount = vipAmount;
  }

  /**
   * 金额.
   */
  public Double getVipAmount() {
    return this.vipAmount;
  }

  /**
   * 唯品退供编码.
   */
  public void setVipReturnCode(String vipReturnCode) {
    this.vipReturnCode = vipReturnCode == null ? null : vipReturnCode.trim();
  }

  /**
   * 唯品退供编码.
   */
  public String getVipReturnCode() {
    return this.vipReturnCode;
  }


  /**
   * 唯品退供单id.
   */
  public void setVipReturnId(Long vipReturnId) {
    this.vipReturnId = vipReturnId;
  }

  /**
   * 唯品退供单id.
   */
  public Long getVipReturnId() {
    return this.vipReturnId;
  }

  /**
   * 唯品退货类型.
   */
  public void setVipReturnType(String vipReturnType) {
    this.vipReturnType = vipReturnType == null ? null : vipReturnType.trim();
  }

  /**
   * 唯品退货类型.
   */
  public String getVipReturnType() {
    return this.vipReturnType;
  }

  /**
   * 唯品签收类型.
   */
  public void setVipSignStatus(VipSignStatus vipSignStatus) {
    this.vipSignStatus = vipSignStatus;
  }

  /**
   * 唯品签收类型.
   */
  public VipSignStatus getVipSignStatus() {
    return this.vipSignStatus;
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
   * 虚拟仓id.
   */
  public void setVirtualWarehouseId(Long virtualWarehouseId) {
    this.virtualWarehouseId = virtualWarehouseId;
  }

  /**
   * 虚拟仓id.
   */
  public Long getVirtualWarehouseId() {
    return this.virtualWarehouseId;
  }

  /**
   * 虚拟仓名称.
   */
  public void setVirtualWarehouseName(String virtualWarehouseName) {
    this.virtualWarehouseName = virtualWarehouseName == null ? null : virtualWarehouseName.trim();
  }

  /**
   * 虚拟仓名称.
   */
  public String getVirtualWarehouseName() {
    return this.virtualWarehouseName;
  }

  /**
   * ${field.comment}.
   */
  public void setDetails(List<VipReturnDetail> details) {
    this.details = details;
  }

  /**
   * ${field.comment}.
   */
  public List<VipReturnDetail> getDetails() {
    return this.details;
  }
}