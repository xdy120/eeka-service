package com.greatonce.oms.domain.trade;

import com.greatonce.oms.domain.BaseDO;
import com.greatonce.oms.domain.enums.product.ProductType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 退换货单明细.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class ReturnOrderDetail extends BaseDO {
  /**
   * 申请单id.
   */
  private Long applyId;
  /**
   * 申请数量.
   */
  private Integer applyQuantity;
  /**
   * 品牌编码.
   */
  private String brandCode;
  /**
   * 品牌名称.
   */
  private String brandName;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 入库正品数量.
   */
  private Integer inQuantity;
  /**
   * 是否通知.
   */
  private Boolean noticed;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
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
   * 商品类型.
   */
  private ProductType productType;
  /**
   * 数量.
   */
  private Integer quantity;
  /**
   * 应退金额.
   */
  private Double refundableAmount;
  /**
   * 退换货单明细id.
   */
  private Long returnOrderDetailId;
  /**
   * 退换货单id.
   */
  private Long returnOrderId;
  /**
   * 退货原因.
   */
  private String returnReasonType;
  /**
   * 销售单编码.
   */
  private String salesOrderCode;
  /**
   * 明细id.
   */
  private Long salesOrderDetailId;
  /**
   * 销售单id.
   */
  private Long salesOrderId;
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
   * 交易号.
   */
  private String tradeId;


  @Override
  public void setPrimaryKey(Long pk) {
    this.returnOrderDetailId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.returnOrderDetailId;
  }


  /**
   * 申请单id.
   */
  public void setApplyId(Long applyId) {
    this.applyId = applyId;
  }

  /**
   * 申请单id.
   */
  public Long getApplyId() {
    return this.applyId;
  }

  /**
   * 申请数量.
   */
  public void setApplyQuantity(Integer applyQuantity) {
    this.applyQuantity = applyQuantity;
  }

  /**
   * 申请数量.
   */
  public Integer getApplyQuantity() {
    return this.applyQuantity;
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
   * 入库正品数量.
   */
  public void setInQuantity(Integer inQuantity) {
    this.inQuantity = inQuantity;
  }

  /**
   * 入库正品数量.
   */
  public Integer getInQuantity() {
    return this.inQuantity;
  }

  /**
   * 是否通知.
   */
  public void setNoticed(Boolean noticed) {
    this.noticed = noticed;
  }

  /**
   * 是否通知.
   */
  public Boolean isNoticed() {
    return this.noticed;
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
   * 商品类型.
   */
  public void setProductType(ProductType productType) {
    this.productType = productType;
  }

  /**
   * 商品类型.
   */
  public ProductType getProductType() {
    return this.productType;
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
   * 应退金额.
   */
  public void setRefundableAmount(Double refundableAmount) {
    this.refundableAmount = refundableAmount;
  }

  /**
   * 应退金额.
   */
  public Double getRefundableAmount() {
    return this.refundableAmount;
  }

  /**
   * 退换货单明细id.
   */
  public void setReturnOrderDetailId(Long returnOrderDetailId) {
    this.returnOrderDetailId = returnOrderDetailId;
  }

  /**
   * 退换货单明细id.
   */
  public Long getReturnOrderDetailId() {
    return this.returnOrderDetailId;
  }

  /**
   * 退换货单id.
   */
  public void setReturnOrderId(Long returnOrderId) {
    this.returnOrderId = returnOrderId;
  }

  /**
   * 退换货单id.
   */
  public Long getReturnOrderId() {
    return this.returnOrderId;
  }


  /**
   * 退货原因.
   */
  public void setReturnReasonType(String returnReasonType) {
    this.returnReasonType = returnReasonType == null ? null : returnReasonType.trim();
  }

  /**
   * 退货原因.
   */
  public String getReturnReasonType() {
    return this.returnReasonType;
  }

  /**
   * 销售单编码.
   */
  public void setSalesOrderCode(String salesOrderCode) {
    this.salesOrderCode = salesOrderCode == null ? null : salesOrderCode.trim();
  }

  /**
   * 销售单编码.
   */
  public String getSalesOrderCode() {
    return this.salesOrderCode;
  }

  /**
   * 明细id.
   */
  public void setSalesOrderDetailId(Long salesOrderDetailId) {
    this.salesOrderDetailId = salesOrderDetailId;
  }

  /**
   * 明细id.
   */
  public Long getSalesOrderDetailId() {
    return this.salesOrderDetailId;
  }

  /**
   * 销售单id.
   */
  public void setSalesOrderId(Long salesOrderId) {
    this.salesOrderId = salesOrderId;
  }

  /**
   * 销售单id.
   */
  public Long getSalesOrderId() {
    return this.salesOrderId;
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
   * 交易号.
   */
  public void setTradeId(String tradeId) {
    this.tradeId = tradeId == null ? null : tradeId.trim();
  }

  /**
   * 交易号.
   */
  public String getTradeId() {
    return this.tradeId;
  }
}