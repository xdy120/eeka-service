package com.greatonce.oms.query.trade;

import com.greatonce.core.database.Query;
import com.greatonce.oms.domain.enums.trade.DispatchOrderDetailStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 配货单明细.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class DispatchOrderDetailQuery extends Query {
  /**
   * 实际金额.
   */
  private Double actualAmount;
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 优惠金额.
   */
  private Double discountAmount;
  /**
   * 配货单明细id.
   */
  private Long dispatchOrderDetailId;
  /**
   * 配货单id.
   */
  private Long dispatchOrderId;
  /**
   * 分销金额.
   */
  private Double distributionAmount;
  /**
   * 分销价.
   */
  private Double distributionPrice;
  /**
   * 是否需要发票.
   */
  private Boolean hasInvoice;
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
   * 销售金额.
   */
  private Double sellingAmount;
  /**
   * 销售价.
   */
  private Double sellingPrice;
  /**
   * 结算金额.
   */
  private Double settlementAmount;
  /**
   * 结算价.
   */
  private Double settlementPrice;
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
  private DispatchOrderDetailStatus status;
  /**
   * 交易号.
   */
  private String tradeId;
  /**
   * 虚拟仓id.
   */
  private Long virtualWarehouseId;
  /**
   * 虚拟仓名称.
   */
  private String virtualWarehouseName;


  /**
   * 实际金额.
   * @param actualAmount 实际金额
   */
  public void setActualAmount(Double actualAmount) {
    this.actualAmount = actualAmount;
  }

  /**
   * 实际金额.
   * @return 实际金额
   */
  public Double getActualAmount() {
      return this.actualAmount;
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
   * 优惠金额.
   * @param discountAmount 优惠金额
   */
  public void setDiscountAmount(Double discountAmount) {
    this.discountAmount = discountAmount;
  }

  /**
   * 优惠金额.
   * @return 优惠金额
   */
  public Double getDiscountAmount() {
      return this.discountAmount;
  }

  /**
   * 配货单明细id.
   * @param dispatchOrderDetailId 配货单明细id
   */
  public void setDispatchOrderDetailId(Long dispatchOrderDetailId) {
    this.dispatchOrderDetailId = dispatchOrderDetailId;
  }

  /**
   * 配货单明细id.
   * @return 配货单明细id
   */
  public Long getDispatchOrderDetailId() {
      return this.dispatchOrderDetailId;
  }

  /**
   * 配货单id.
   * @param dispatchOrderId 配货单id
   */
  public void setDispatchOrderId(Long dispatchOrderId) {
    this.dispatchOrderId = dispatchOrderId;
  }

  /**
   * 配货单id.
   * @return 配货单id
   */
  public Long getDispatchOrderId() {
      return this.dispatchOrderId;
  }

  /**
   * 分销金额.
   * @param distributionAmount 分销金额
   */
  public void setDistributionAmount(Double distributionAmount) {
    this.distributionAmount = distributionAmount;
  }

  /**
   * 分销金额.
   * @return 分销金额
   */
  public Double getDistributionAmount() {
      return this.distributionAmount;
  }

  /**
   * 分销价.
   * @param distributionPrice 分销价
   */
  public void setDistributionPrice(Double distributionPrice) {
    this.distributionPrice = distributionPrice;
  }

  /**
   * 分销价.
   * @return 分销价
   */
  public Double getDistributionPrice() {
      return this.distributionPrice;
  }

  /**
   * 是否需要发票.
   * @param hasInvoice 是否需要发票
   */
  public void setHasInvoice(Boolean hasInvoice) {
    this.hasInvoice = hasInvoice;
  }

  /**
   * 是否需要发票.
   * @return 是否需要发票
   */
  public Boolean isHasInvoice() {
      return this.hasInvoice;
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
   * 销售金额.
   * @param sellingAmount 销售金额
   */
  public void setSellingAmount(Double sellingAmount) {
    this.sellingAmount = sellingAmount;
  }

  /**
   * 销售金额.
   * @return 销售金额
   */
  public Double getSellingAmount() {
      return this.sellingAmount;
  }

  /**
   * 销售价.
   * @param sellingPrice 销售价
   */
  public void setSellingPrice(Double sellingPrice) {
    this.sellingPrice = sellingPrice;
  }

  /**
   * 销售价.
   * @return 销售价
   */
  public Double getSellingPrice() {
      return this.sellingPrice;
  }

  /**
   * 结算金额.
   * @param settlementAmount 结算金额
   */
  public void setSettlementAmount(Double settlementAmount) {
    this.settlementAmount = settlementAmount;
  }

  /**
   * 结算金额.
   * @return 结算金额
   */
  public Double getSettlementAmount() {
      return this.settlementAmount;
  }

  /**
   * 结算价.
   * @param settlementPrice 结算价
   */
  public void setSettlementPrice(Double settlementPrice) {
    this.settlementPrice = settlementPrice;
  }

  /**
   * 结算价.
   * @return 结算价
   */
  public Double getSettlementPrice() {
      return this.settlementPrice;
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

  /**
   * 状态.
   * @param status 状态
   */
  public void setStatus(DispatchOrderDetailStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   * @return 状态
   */
  public DispatchOrderDetailStatus getStatus() {
      return this.status;
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

  /**
   * 虚拟仓id.
   * @param virtualWarehouseId 虚拟仓id
   */
  public void setVirtualWarehouseId(Long virtualWarehouseId) {
    this.virtualWarehouseId = virtualWarehouseId;
  }

  /**
   * 虚拟仓id.
   * @return 虚拟仓id
   */
  public Long getVirtualWarehouseId() {
      return this.virtualWarehouseId;
  }

  /**
   * 虚拟仓名称.
   * @param virtualWarehouseName 虚拟仓名称
   */
  public void setVirtualWarehouseName(String virtualWarehouseName) {
    this.virtualWarehouseName = virtualWarehouseName == null ? null : virtualWarehouseName.trim();
  }

  /**
   * 虚拟仓名称.
   * @return 虚拟仓名称
   */
  public String getVirtualWarehouseName() {
      return this.virtualWarehouseName;
  }
}