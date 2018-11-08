package com.greatonce.oms.domain.purchase;

import com.greatonce.oms.domain.BaseDO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 采购退货明细.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class PurchaseReturnOrderDetail extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
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
   * 采购价格.
   */
  private Double purchasePrice;
  /**
   * 采购退货明细id.
   */
  private Long purchaseReturnOrderDetailId;
  /**
   * 采购退货单id.
   */
  private Long purchaseReturnOrderId;
  /**
   * 退货价格.
   */
  private Double purchaseReturnPrice;
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


  @Override
  public void setPrimaryKey(Long pk) {
    this.purchaseReturnOrderDetailId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.purchaseReturnOrderDetailId;
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
   * 采购价格.
   */
  public void setPurchasePrice(Double purchasePrice) {
    this.purchasePrice = purchasePrice;
  }

  /**
   * 采购价格.
   */
  public Double getPurchasePrice() {
    return this.purchasePrice;
  }

  /**
   * 采购退货明细id.
   */
  public void setPurchaseReturnOrderDetailId(Long purchaseReturnOrderDetailId) {
    this.purchaseReturnOrderDetailId = purchaseReturnOrderDetailId;
  }

  /**
   * 采购退货明细id.
   */
  public Long getPurchaseReturnOrderDetailId() {
    return this.purchaseReturnOrderDetailId;
  }

  /**
   * 采购退货单id.
   */
  public void setPurchaseReturnOrderId(Long purchaseReturnOrderId) {
    this.purchaseReturnOrderId = purchaseReturnOrderId;
  }

  /**
   * 采购退货单id.
   */
  public Long getPurchaseReturnOrderId() {
    return this.purchaseReturnOrderId;
  }

  /**
   * 退货价格.
   */
  public void setPurchaseReturnPrice(Double purchaseReturnPrice) {
    this.purchaseReturnPrice = purchaseReturnPrice;
  }

  /**
   * 退货价格.
   */
  public Double getPurchaseReturnPrice() {
    return this.purchaseReturnPrice;
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
}