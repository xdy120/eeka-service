package com.greatonce.oms.query.vip;

import com.greatonce.core.database.Query;
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
public class VipAdjustDetailQuery extends Query {
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 锁定数量.
   */
  private Integer lockQuantity;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
  /**
   * 计划数量.
   */
  private Integer planQuantity;
  /**
   * 商品编码.
   */
  private String productCode;
  /**
   * .
   */
  private List<String> productCodes;
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
   * .
   */
  private List<String> skuCodes;
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
   * .
   */
  private List<VipAdjustDetailStatus> statuses;
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
   * 锁定数量.
   * @param lockQuantity 锁定数量
   */
  public void setLockQuantity(Integer lockQuantity) {
    this.lockQuantity = lockQuantity;
  }

  /**
   * 锁定数量.
   * @return 锁定数量
   */
  public Integer getLockQuantity() {
      return this.lockQuantity;
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
   * 计划数量.
   * @param planQuantity 计划数量
   */
  public void setPlanQuantity(Integer planQuantity) {
    this.planQuantity = planQuantity;
  }

  /**
   * 计划数量.
   * @return 计划数量
   */
  public Integer getPlanQuantity() {
      return this.planQuantity;
  }

  /**
   * 商品编码.
   * @param productCode 商品编码
   */
  public void setProductCode(String productCode) {
    this.productCode = productCode == null ? null : productCode.trim();
  }

  /**
   * 商品编码.
   * @return 商品编码
   */
  public String getProductCode() {
      return this.productCode;
  }

  /**
   * .
   * @param productCodes 
   */
  public void setProductCodes(List<String> productCodes) {
    this.productCodes = productCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getProductCodes() {
      return this.productCodes;
  }

  /**
   * 商品id.
   * @param productId 商品id
   */
  public void setProductId(Long productId) {
    this.productId = productId;
  }

  /**
   * 商品id.
   * @return 商品id
   */
  public Long getProductId() {
      return this.productId;
  }

  /**
   * 商品名称.
   * @param productName 商品名称
   */
  public void setProductName(String productName) {
    this.productName = productName == null ? null : productName.trim();
  }

  /**
   * 商品名称.
   * @return 商品名称
   */
  public String getProductName() {
      return this.productName;
  }

  /**
   * 商品规格编码.
   * @param skuCode 商品规格编码
   */
  public void setSkuCode(String skuCode) {
    this.skuCode = skuCode == null ? null : skuCode.trim();
  }

  /**
   * 商品规格编码.
   * @return 商品规格编码
   */
  public String getSkuCode() {
      return this.skuCode;
  }

  /**
   * .
   * @param skuCodes 
   */
  public void setSkuCodes(List<String> skuCodes) {
    this.skuCodes = skuCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getSkuCodes() {
      return this.skuCodes;
  }

  /**
   * 商品规格id.
   * @param skuId 商品规格id
   */
  public void setSkuId(Long skuId) {
    this.skuId = skuId;
  }

  /**
   * 商品规格id.
   * @return 商品规格id
   */
  public Long getSkuId() {
      return this.skuId;
  }

  /**
   * 商品规格名称.
   * @param skuName 商品规格名称
   */
  public void setSkuName(String skuName) {
    this.skuName = skuName == null ? null : skuName.trim();
  }

  /**
   * 商品规格名称.
   * @return 商品规格名称
   */
  public String getSkuName() {
      return this.skuName;
  }

  /**
   * 状态.
   * @param status 状态
   */
  public void setStatus(VipAdjustDetailStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   * @return 状态
   */
  public VipAdjustDetailStatus getStatus() {
      return this.status;
  }

  /**
   * .
   * @param statuses 
   */
  public void setStatuses(List<VipAdjustDetailStatus> statuses) {
    this.statuses = statuses;
  }

  /**
   * .
   * @return 
   */
  public List<VipAdjustDetailStatus> getStatuses() {
      return this.statuses;
  }

  /**
   * 上传数量.
   * @param uploadQuantity 上传数量
   */
  public void setUploadQuantity(Integer uploadQuantity) {
    this.uploadQuantity = uploadQuantity;
  }

  /**
   * 上传数量.
   * @return 上传数量
   */
  public Integer getUploadQuantity() {
      return this.uploadQuantity;
  }

  /**
   * 上传类型.
   * @param uploadType 上传类型
   */
  public void setUploadType(VipScheduleUploadType uploadType) {
    this.uploadType = uploadType;
  }

  /**
   * 上传类型.
   * @return 上传类型
   */
  public VipScheduleUploadType getUploadType() {
      return this.uploadType;
  }

  /**
   * 调整单明细id.
   * @param vipAdjustDetailId 调整单明细id
   */
  public void setVipAdjustDetailId(Long vipAdjustDetailId) {
    this.vipAdjustDetailId = vipAdjustDetailId;
  }

  /**
   * 调整单明细id.
   * @return 调整单明细id
   */
  public Long getVipAdjustDetailId() {
      return this.vipAdjustDetailId;
  }

  /**
   * 调整单id.
   * @param vipAdjustId 调整单id
   */
  public void setVipAdjustId(Long vipAdjustId) {
    this.vipAdjustId = vipAdjustId;
  }

  /**
   * 调整单id.
   * @return 调整单id
   */
  public Long getVipAdjustId() {
      return this.vipAdjustId;
  }

  /**
   * 唯品商品编码.
   * @param vipBarcode 唯品商品编码
   */
  public void setVipBarcode(String vipBarcode) {
    this.vipBarcode = vipBarcode == null ? null : vipBarcode.trim();
  }

  /**
   * 唯品商品编码.
   * @return 唯品商品编码
   */
  public String getVipBarcode() {
      return this.vipBarcode;
  }
}