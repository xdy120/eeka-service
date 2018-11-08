package com.greatonce.oms.domain.vip;

import com.greatonce.oms.domain.BaseDO;
import com.greatonce.oms.domain.enums.vip.VipScheduleDetailStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 唯品档期明细.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class VipScheduleDetail extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 配货占用数量.
   */
  private Integer dispatchOccupancyQuantity;
  /**
   * 货值.
   */
  private Double goodsValue;
  /**
   * 锁定数量.
   */
  private Integer lockQuantity;
  /**
   * 消息.
   */
  private String message;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 出库数量.
   */
  private Integer outQuantity;
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
  private VipScheduleDetailStatus status;
  /**
   * 唯品商品编码.
   */
  private String vipBarcode;
  /**
   * 档期明细id.
   */
  private Long vipScheduleDetailId;
  /**
   * 档期id.
   */
  private Long vipScheduleId;


  @Override
  public void setPrimaryKey(Long pk) {
    this.vipScheduleDetailId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.vipScheduleDetailId;
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
   * 配货占用数量.
   */
  public void setDispatchOccupancyQuantity(Integer dispatchOccupancyQuantity) {
    this.dispatchOccupancyQuantity = dispatchOccupancyQuantity;
  }

  /**
   * 配货占用数量.
   */
  public Integer getDispatchOccupancyQuantity() {
    return this.dispatchOccupancyQuantity;
  }

  /**
   * 货值.
   */
  public void setGoodsValue(Double goodsValue) {
    this.goodsValue = goodsValue;
  }

  /**
   * 货值.
   */
  public Double getGoodsValue() {
    return this.goodsValue;
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
   * 消息.
   */
  public void setMessage(String message) {
    this.message = message == null ? null : message.trim();
  }

  /**
   * 消息.
   */
  public String getMessage() {
    return this.message;
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
   * 出库数量.
   */
  public void setOutQuantity(Integer outQuantity) {
    this.outQuantity = outQuantity;
  }

  /**
   * 出库数量.
   */
  public Integer getOutQuantity() {
    return this.outQuantity;
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
  public void setStatus(VipScheduleDetailStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   */
  public VipScheduleDetailStatus getStatus() {
    return this.status;
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

  /**
   * 档期明细id.
   */
  public void setVipScheduleDetailId(Long vipScheduleDetailId) {
    this.vipScheduleDetailId = vipScheduleDetailId;
  }

  /**
   * 档期明细id.
   */
  public Long getVipScheduleDetailId() {
    return this.vipScheduleDetailId;
  }

  /**
   * 档期id.
   */
  public void setVipScheduleId(Long vipScheduleId) {
    this.vipScheduleId = vipScheduleId;
  }

  /**
   * 档期id.
   */
  public Long getVipScheduleId() {
    return this.vipScheduleId;
  }
}