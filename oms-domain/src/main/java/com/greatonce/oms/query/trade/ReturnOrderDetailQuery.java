package com.greatonce.oms.query.trade;

import com.greatonce.core.database.Query;
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
public class ReturnOrderDetailQuery extends Query {
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
   * .
   */
  private List<String> brandCodes;
  /**
   * 品牌名称.
   */
  private String brandName;
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 入库正品数量.
   */
  private Integer inQuantity;
  /**
   * 是否通知.
   */
  private Boolean noticed;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
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
   * .
   */
  private List<Long> returnOrderIds;
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
   * 交易号.
   */
  private String tradeId;


  /**
   * 申请单id.
   * @param applyId 申请单id
   */
  public void setApplyId(Long applyId) {
    this.applyId = applyId;
  }

  /**
   * 申请单id.
   * @return 申请单id
   */
  public Long getApplyId() {
      return this.applyId;
  }

  /**
   * 申请数量.
   * @param applyQuantity 申请数量
   */
  public void setApplyQuantity(Integer applyQuantity) {
    this.applyQuantity = applyQuantity;
  }

  /**
   * 申请数量.
   * @return 申请数量
   */
  public Integer getApplyQuantity() {
      return this.applyQuantity;
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
   * .
   * @param brandCodes 
   */
  public void setBrandCodes(List<String> brandCodes) {
    this.brandCodes = brandCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getBrandCodes() {
      return this.brandCodes;
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
   * 入库正品数量.
   * @param inQuantity 入库正品数量
   */
  public void setInQuantity(Integer inQuantity) {
    this.inQuantity = inQuantity;
  }

  /**
   * 入库正品数量.
   * @return 入库正品数量
   */
  public Integer getInQuantity() {
      return this.inQuantity;
  }

  /**
   * 是否通知.
   * @param noticed 是否通知
   */
  public void setNoticed(Boolean noticed) {
    this.noticed = noticed;
  }

  /**
   * 是否通知.
   * @return 是否通知
   */
  public Boolean isNoticed() {
      return this.noticed;
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
   * 商品类型.
   * @param productType 商品类型
   */
  public void setProductType(ProductType productType) {
    this.productType = productType;
  }

  /**
   * 商品类型.
   * @return 商品类型
   */
  public ProductType getProductType() {
      return this.productType;
  }

  /**
   * 数量.
   * @param quantity 数量
   */
  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  /**
   * 数量.
   * @return 数量
   */
  public Integer getQuantity() {
      return this.quantity;
  }

  /**
   * 应退金额.
   * @param refundableAmount 应退金额
   */
  public void setRefundableAmount(Double refundableAmount) {
    this.refundableAmount = refundableAmount;
  }

  /**
   * 应退金额.
   * @return 应退金额
   */
  public Double getRefundableAmount() {
      return this.refundableAmount;
  }

  /**
   * 退换货单明细id.
   * @param returnOrderDetailId 退换货单明细id
   */
  public void setReturnOrderDetailId(Long returnOrderDetailId) {
    this.returnOrderDetailId = returnOrderDetailId;
  }

  /**
   * 退换货单明细id.
   * @return 退换货单明细id
   */
  public Long getReturnOrderDetailId() {
      return this.returnOrderDetailId;
  }

  /**
   * 退换货单id.
   * @param returnOrderId 退换货单id
   */
  public void setReturnOrderId(Long returnOrderId) {
    this.returnOrderId = returnOrderId;
  }

  /**
   * 退换货单id.
   * @return 退换货单id
   */
  public Long getReturnOrderId() {
      return this.returnOrderId;
  }

  /**
   * .
   * @param returnOrderIds 
   */
  public void setReturnOrderIds(List<Long> returnOrderIds) {
    this.returnOrderIds = returnOrderIds;
  }

  /**
   * .
   * @return 
   */
  public List<Long> getReturnOrderIds() {
      return this.returnOrderIds;
  }

  /**
   * 退货原因.
   * @param returnReasonType 退货原因
   */
  public void setReturnReasonType(String returnReasonType) {
    this.returnReasonType = returnReasonType == null ? null : returnReasonType.trim();
  }

  /**
   * 退货原因.
   * @return 退货原因
   */
  public String getReturnReasonType() {
      return this.returnReasonType;
  }

  /**
   * 销售单编码.
   * @param salesOrderCode 销售单编码
   */
  public void setSalesOrderCode(String salesOrderCode) {
    this.salesOrderCode = salesOrderCode == null ? null : salesOrderCode.trim();
  }

  /**
   * 销售单编码.
   * @return 销售单编码
   */
  public String getSalesOrderCode() {
      return this.salesOrderCode;
  }

  /**
   * 明细id.
   * @param salesOrderDetailId 明细id
   */
  public void setSalesOrderDetailId(Long salesOrderDetailId) {
    this.salesOrderDetailId = salesOrderDetailId;
  }

  /**
   * 明细id.
   * @return 明细id
   */
  public Long getSalesOrderDetailId() {
      return this.salesOrderDetailId;
  }

  /**
   * 销售单id.
   * @param salesOrderId 销售单id
   */
  public void setSalesOrderId(Long salesOrderId) {
    this.salesOrderId = salesOrderId;
  }

  /**
   * 销售单id.
   * @return 销售单id
   */
  public Long getSalesOrderId() {
      return this.salesOrderId;
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
   * 交易号.
   * @param tradeId 交易号
   */
  public void setTradeId(String tradeId) {
    this.tradeId = tradeId == null ? null : tradeId.trim();
  }

  /**
   * 交易号.
   * @return 交易号
   */
  public String getTradeId() {
      return this.tradeId;
  }
}