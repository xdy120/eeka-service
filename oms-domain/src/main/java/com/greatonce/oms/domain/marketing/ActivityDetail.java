package com.greatonce.oms.domain.marketing;

import com.greatonce.oms.domain.BaseDO;
import com.greatonce.oms.domain.enums.marketing.ActivityDetailStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 活动报名明细.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class ActivityDetail extends BaseDO {
  /**
   * 活动报名明细id.
   */
  private Long activityDetailId;
  /**
   * 活动id.
   */
  private Long activityId;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 锁定数量.
   */
  private Integer lockQuantity;
  /**
   * 商城商品编码.
   */
  private String mallProductId;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 计划数量.
   */
  private Integer planQuantity;
  /**
   * 单价.
   */
  private Double price;
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
   * 备注.
   */
  private String remark;
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
  private ActivityDetailStatus status;


  @Override
  public void setPrimaryKey(Long pk) {
    this.activityDetailId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.activityDetailId;
  }


  /**
   * 活动报名明细id.
   */
  public void setActivityDetailId(Long activityDetailId) {
    this.activityDetailId = activityDetailId;
  }

  /**
   * 活动报名明细id.
   */
  public Long getActivityDetailId() {
    return this.activityDetailId;
  }

  /**
   * 活动id.
   */
  public void setActivityId(Long activityId) {
    this.activityId = activityId;
  }

  /**
   * 活动id.
   */
  public Long getActivityId() {
    return this.activityId;
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
   * 商城商品编码.
   */
  public void setMallProductId(String mallProductId) {
    this.mallProductId = mallProductId == null ? null : mallProductId.trim();
  }

  /**
   * 商城商品编码.
   */
  public String getMallProductId() {
    return this.mallProductId;
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
   * 单价.
   */
  public void setPrice(Double price) {
    this.price = price;
  }

  /**
   * 单价.
   */
  public Double getPrice() {
    return this.price;
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
  public void setStatus(ActivityDetailStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   */
  public ActivityDetailStatus getStatus() {
    return this.status;
  }

}