package com.greatonce.oms.domain.trade;

import com.greatonce.oms.domain.BaseDO;
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
public class DispatchOrderDelivery extends BaseDO {
  /**
   * 箱码.
   */
  private String boxNo;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 发货时间.
   */
  private LocalDateTime deliveryTime;
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
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
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


  @Override
  public void setPrimaryKey(Long pk) {
    this.dispatchOrderDeliveryId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.dispatchOrderDeliveryId;
  }


  /**
   * 箱码.
   */
  public void setBoxNo(String boxNo) {
    this.boxNo = boxNo == null ? null : boxNo.trim();
  }

  /**
   * 箱码.
   */
  public String getBoxNo() {
    return this.boxNo;
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
   * 发货时间.
   */
  public void setDeliveryTime(LocalDateTime deliveryTime) {
    this.deliveryTime = deliveryTime;
  }

  /**
   * 发货时间.
   */
  public LocalDateTime getDeliveryTime() {
    return this.deliveryTime;
  }

  /**
   * 配货单明细快递id.
   */
  public void setDispatchOrderDeliveryId(Long dispatchOrderDeliveryId) {
    this.dispatchOrderDeliveryId = dispatchOrderDeliveryId;
  }

  /**
   * 配货单明细快递id.
   */
  public Long getDispatchOrderDeliveryId() {
    return this.dispatchOrderDeliveryId;
  }

  /**
   * 配货单明细id.
   */
  public void setDispatchOrderDetailId(Long dispatchOrderDetailId) {
    this.dispatchOrderDetailId = dispatchOrderDetailId;
  }

  /**
   * 配货单明细id.
   */
  public Long getDispatchOrderDetailId() {
    return this.dispatchOrderDetailId;
  }

  /**
   * 配货单id.
   */
  public void setDispatchOrderId(Long dispatchOrderId) {
    this.dispatchOrderId = dispatchOrderId;
  }

  /**
   * 配货单id.
   */
  public Long getDispatchOrderId() {
    return this.dispatchOrderId;
  }

  /**
   * 快递id.
   */
  public void setExpressId(Long expressId) {
    this.expressId = expressId;
  }

  /**
   * 快递id.
   */
  public Long getExpressId() {
    return this.expressId;
  }

  /**
   * 快递名称.
   */
  public void setExpressName(String expressName) {
    this.expressName = expressName == null ? null : expressName.trim();
  }

  /**
   * 快递名称.
   */
  public String getExpressName() {
    return this.expressName;
  }

  /**
   * 快递单号.
   */
  public void setExpressNo(String expressNo) {
    this.expressNo = expressNo == null ? null : expressNo.trim();
  }

  /**
   * 快递单号.
   */
  public String getExpressNo() {
    return this.expressNo;
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
   * 外部单号.
   */
  public void setOuterCode(String outerCode) {
    this.outerCode = outerCode == null ? null : outerCode.trim();
  }

  /**
   * 外部单号.
   */
  public String getOuterCode() {
    return this.outerCode;
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
}