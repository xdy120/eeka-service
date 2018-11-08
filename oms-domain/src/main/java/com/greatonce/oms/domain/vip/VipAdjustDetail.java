package com.greatonce.oms.domain.vip;

import com.greatonce.oms.domain.BaseDO;
import com.greatonce.oms.domain.enums.vip.VipScheduleUploadType;
import com.greatonce.oms.domain.enums.vip.VipAdjustDetailStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 唯品调整单明细.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class VipAdjustDetail extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 锁定数量.
   */
  private Integer lockQuantity;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 计划数量.
   */
  private Integer planQuantity;
  /**
   * 商品编码.
   */
  private String productCode;
  /**
   * 商品id.
   */
  private Long productId;
  /**
   * 商品名称.
   */
  private String productName;
  /**
   * 商品规格编码.
   */
  private String skuCode;
  /**
   * 商品规格id.
   */
  private Long skuId;
  /**
   * 商品规格名称.
   */
  private String skuName;
  /**
   * 状态.
   */
  private VipAdjustDetailStatus status;
  /**
   * 上传数量.
   */
  private Integer uploadQuantity;
  /**
   * 上传类型.
   */
  private VipScheduleUploadType uploadType;
  /**
   * 调整单明细id.
   */
  private Long vipAdjustDetailId;
  /**
   * 调整单id.
   */
  private Long vipAdjustId;
  /**
   * 唯品商品编码.
   */
  private String vipBarcode;


  @Override
  public void setPrimaryKey(Long pk) {
    this.vipAdjustDetailId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.vipAdjustDetailId;
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
   * 锁定数量.
   */
  public void setLockQuantity(Integer lockQuantity) {
    this.lockQuantity = lockQuantity;
  }

  /**
   * 锁定数量.
   */
  public Integer getLockQuantity() {
    return this.lockQuantity;
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
   * 计划数量.
   */
  public void setPlanQuantity(Integer planQuantity) {
    this.planQuantity = planQuantity;
  }

  /**
   * 计划数量.
   */
  public Integer getPlanQuantity() {
    return this.planQuantity;
  }

  /**
   * 商品编码.
   */
  public void setProductCode(String productCode) {
    this.productCode = productCode == null ? null : productCode.trim();
  }

  /**
   * 商品编码.
   */
  public String getProductCode() {
    return this.productCode;
  }


  /**
   * 商品id.
   */
  public void setProductId(Long productId) {
    this.productId = productId;
  }

  /**
   * 商品id.
   */
  public Long getProductId() {
    return this.productId;
  }

  /**
   * 商品名称.
   */
  public void setProductName(String productName) {
    this.productName = productName == null ? null : productName.trim();
  }

  /**
   * 商品名称.
   */
  public String getProductName() {
    return this.productName;
  }

  /**
   * 商品规格编码.
   */
  public void setSkuCode(String skuCode) {
    this.skuCode = skuCode == null ? null : skuCode.trim();
  }

  /**
   * 商品规格编码.
   */
  public String getSkuCode() {
    return this.skuCode;
  }


  /**
   * 商品规格id.
   */
  public void setSkuId(Long skuId) {
    this.skuId = skuId;
  }

  /**
   * 商品规格id.
   */
  public Long getSkuId() {
    return this.skuId;
  }

  /**
   * 商品规格名称.
   */
  public void setSkuName(String skuName) {
    this.skuName = skuName == null ? null : skuName.trim();
  }

  /**
   * 商品规格名称.
   */
  public String getSkuName() {
    return this.skuName;
  }

  /**
   * 状态.
   */
  public void setStatus(VipAdjustDetailStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   */
  public VipAdjustDetailStatus getStatus() {
    return this.status;
  }


  /**
   * 上传数量.
   */
  public void setUploadQuantity(Integer uploadQuantity) {
    this.uploadQuantity = uploadQuantity;
  }

  /**
   * 上传数量.
   */
  public Integer getUploadQuantity() {
    return this.uploadQuantity;
  }

  /**
   * 上传类型.
   */
  public void setUploadType(VipScheduleUploadType uploadType) {
    this.uploadType = uploadType;
  }

  /**
   * 上传类型.
   */
  public VipScheduleUploadType getUploadType() {
    return this.uploadType;
  }

  /**
   * 调整单明细id.
   */
  public void setVipAdjustDetailId(Long vipAdjustDetailId) {
    this.vipAdjustDetailId = vipAdjustDetailId;
  }

  /**
   * 调整单明细id.
   */
  public Long getVipAdjustDetailId() {
    return this.vipAdjustDetailId;
  }

  /**
   * 调整单id.
   */
  public void setVipAdjustId(Long vipAdjustId) {
    this.vipAdjustId = vipAdjustId;
  }

  /**
   * 调整单id.
   */
  public Long getVipAdjustId() {
    return this.vipAdjustId;
  }

  /**
   * 唯品商品编码.
   */
  public void setVipBarcode(String vipBarcode) {
    this.vipBarcode = vipBarcode == null ? null : vipBarcode.trim();
  }

  /**
   * 唯品商品编码.
   */
  public String getVipBarcode() {
    return this.vipBarcode;
  }
}