package com.greatonce.oms.domain.vip;

import com.greatonce.oms.domain.BaseDO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 唯品配货单明细.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class VipDispatchDetail extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
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
   * 通知数量.
   */
  private Integer noticeQuantity;
  /**
   * 出库数量.
   */
  private Integer outQuantity;
  /**
   * po编码.
   */
  private String poCode;
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
   * 金额.
   */
  private Double vipAmount;
  /**
   * 唯品商品编码.
   */
  private String vipBarcode;
  /**
   * 拣货单明细.
   */
  private Long vipDispatchDetailId;
  /**
   * 配货单id.
   */
  private Long vipDispatchId;
  /**
   * 唯品价.
   */
  private Double vipPrice;


  @Override
  public void setPrimaryKey(Long pk) {
    this.vipDispatchDetailId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.vipDispatchDetailId;
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
   * 通知数量.
   */
  public void setNoticeQuantity(Integer noticeQuantity) {
    this.noticeQuantity = noticeQuantity;
  }

  /**
   * 通知数量.
   */
  public Integer getNoticeQuantity() {
    return this.noticeQuantity;
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
   * 拣货单明细.
   */
  public void setVipDispatchDetailId(Long vipDispatchDetailId) {
    this.vipDispatchDetailId = vipDispatchDetailId;
  }

  /**
   * 拣货单明细.
   */
  public Long getVipDispatchDetailId() {
    return this.vipDispatchDetailId;
  }

  /**
   * 配货单id.
   */
  public void setVipDispatchId(Long vipDispatchId) {
    this.vipDispatchId = vipDispatchId;
  }

  /**
   * 配货单id.
   */
  public Long getVipDispatchId() {
    return this.vipDispatchId;
  }

  /**
   * 唯品价.
   */
  public void setVipPrice(Double vipPrice) {
    this.vipPrice = vipPrice;
  }

  /**
   * 唯品价.
   */
  public Double getVipPrice() {
    return this.vipPrice;
  }
}