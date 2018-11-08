package com.greatonce.oms.query.purchase;

import com.greatonce.core.database.Query;
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
public class PurchaseReturnOrderDetailQuery extends Query {
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
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
   * 通知数量.
   * @param noticeQuantity 通知数量
   */
  public void setNoticeQuantity(Integer noticeQuantity) {
    this.noticeQuantity = noticeQuantity;
  }

  /**
   * 通知数量.
   * @return 通知数量
   */
  public Integer getNoticeQuantity() {
      return this.noticeQuantity;
  }

  /**
   * 出库数量.
   * @param outQuantity 出库数量
   */
  public void setOutQuantity(Integer outQuantity) {
    this.outQuantity = outQuantity;
  }

  /**
   * 出库数量.
   * @return 出库数量
   */
  public Integer getOutQuantity() {
      return this.outQuantity;
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
   * 采购价格.
   * @param purchasePrice 采购价格
   */
  public void setPurchasePrice(Double purchasePrice) {
    this.purchasePrice = purchasePrice;
  }

  /**
   * 采购价格.
   * @return 采购价格
   */
  public Double getPurchasePrice() {
      return this.purchasePrice;
  }

  /**
   * 采购退货明细id.
   * @param purchaseReturnOrderDetailId 采购退货明细id
   */
  public void setPurchaseReturnOrderDetailId(Long purchaseReturnOrderDetailId) {
    this.purchaseReturnOrderDetailId = purchaseReturnOrderDetailId;
  }

  /**
   * 采购退货明细id.
   * @return 采购退货明细id
   */
  public Long getPurchaseReturnOrderDetailId() {
      return this.purchaseReturnOrderDetailId;
  }

  /**
   * 采购退货单id.
   * @param purchaseReturnOrderId 采购退货单id
   */
  public void setPurchaseReturnOrderId(Long purchaseReturnOrderId) {
    this.purchaseReturnOrderId = purchaseReturnOrderId;
  }

  /**
   * 采购退货单id.
   * @return 采购退货单id
   */
  public Long getPurchaseReturnOrderId() {
      return this.purchaseReturnOrderId;
  }

  /**
   * 退货价格.
   * @param purchaseReturnPrice 退货价格
   */
  public void setPurchaseReturnPrice(Double purchaseReturnPrice) {
    this.purchaseReturnPrice = purchaseReturnPrice;
  }

  /**
   * 退货价格.
   * @return 退货价格
   */
  public Double getPurchaseReturnPrice() {
      return this.purchaseReturnPrice;
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
}