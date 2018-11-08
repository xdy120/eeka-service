package com.greatonce.oms.query.trade;

import com.greatonce.core.database.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 配货单发货信息.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class DispatchOrderDeliveryQuery extends Query {
  /**
   * 箱码.
   */
  private String boxNo;
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 发货时间开始.
   */
  private LocalDateTime deliveryTimeBegin;
  /**
   * 发货时间结束.
   */
  private LocalDateTime deliveryTimeEnd;
  /**
   * 配货单明细快递id.
   */
  private Long dispatchOrderDeliveryId;
  /**
   * 配货单明细id.
   */
  private Long dispatchOrderDetailId;
  /**
   * 配货单id.
   */
  private Long dispatchOrderId;
  /**
   * 快递id.
   */
  private Long expressId;
  /**
   * 快递名称.
   */
  private String expressName;
  /**
   * 快递单号.
   */
  private String expressNo;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
  /**
   * 外部单号.
   */
  private String outerCode;
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
   * 数量.
   */
  private Integer quantity;
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
   * 箱码.
   * @param boxNo 箱码
   */
  public void setBoxNo(String boxNo) {
    this.boxNo = boxNo == null ? null : boxNo.trim();
  }

  /**
   * 箱码.
   * @return 箱码
   */
  public String getBoxNo() {
      return this.boxNo;
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
   * 发货时间开始.
   * @param deliveryTimeBegin 开始.
   */
  public void setDeliveryTimeBegin(LocalDateTime deliveryTimeBegin) {
    this.deliveryTimeBegin = deliveryTimeBegin;
  }

  /**
   * 发货时间开始.
   * @return 发货时间开始
   */
  public LocalDateTime getDeliveryTimeBegin() {
    return this.deliveryTimeBegin;
  }

  /**
   * 发货时间结束.
   * @param deliveryTimeEnd 结束
   */
  public void setDeliveryTimeEnd(LocalDateTime deliveryTimeEnd) {
    this.deliveryTimeEnd = deliveryTimeEnd;
  }

  /**
   * 发货时间结束.
   * @return 发货时间结束
   */
  public LocalDateTime getDeliveryTimeEnd() {
    return this.deliveryTimeEnd;
  }

  /**
   * 配货单明细快递id.
   * @param dispatchOrderDeliveryId 配货单明细快递id
   */
  public void setDispatchOrderDeliveryId(Long dispatchOrderDeliveryId) {
    this.dispatchOrderDeliveryId = dispatchOrderDeliveryId;
  }

  /**
   * 配货单明细快递id.
   * @return 配货单明细快递id
   */
  public Long getDispatchOrderDeliveryId() {
      return this.dispatchOrderDeliveryId;
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
   * 快递id.
   * @param expressId 快递id
   */
  public void setExpressId(Long expressId) {
    this.expressId = expressId;
  }

  /**
   * 快递id.
   * @return 快递id
   */
  public Long getExpressId() {
      return this.expressId;
  }

  /**
   * 快递名称.
   * @param expressName 快递名称
   */
  public void setExpressName(String expressName) {
    this.expressName = expressName == null ? null : expressName.trim();
  }

  /**
   * 快递名称.
   * @return 快递名称
   */
  public String getExpressName() {
      return this.expressName;
  }

  /**
   * 快递单号.
   * @param expressNo 快递单号
   */
  public void setExpressNo(String expressNo) {
    this.expressNo = expressNo == null ? null : expressNo.trim();
  }

  /**
   * 快递单号.
   * @return 快递单号
   */
  public String getExpressNo() {
      return this.expressNo;
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
   * 外部单号.
   * @param outerCode 外部单号
   */
  public void setOuterCode(String outerCode) {
    this.outerCode = outerCode == null ? null : outerCode.trim();
  }

  /**
   * 外部单号.
   * @return 外部单号
   */
  public String getOuterCode() {
      return this.outerCode;
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