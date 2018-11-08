package com.greatonce.oms.domain.trade;

import com.greatonce.oms.domain.BaseDO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 退货通知单明细.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class ReturnNoticeOrderDetail extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 入库正品数量.
   */
  private Integer inQuantity;
  /**
   * 入库次品数量.
   */
  private Integer inSubstandardQuantity;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 通知数量.
   */
  private Integer noticeQuantity;
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
   * 退货通知单明细id.
   */
  private Long returnNoticeOrderDetailId;
  /**
   * 退换货通知单id.
   */
  private Long returnNoticeOrderId;
  /**
   * 退换货单code.
   */
  private String returnOrderCode;
  /**
   * 退换货单明细id.
   */
  private Long returnOrderDetailId;
  /**
   * 退换货单id.
   */
  private Long returnOrderId;
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
    this.returnNoticeOrderDetailId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.returnNoticeOrderDetailId;
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
   * 入库次品数量.
   */
  public void setInSubstandardQuantity(Integer inSubstandardQuantity) {
    this.inSubstandardQuantity = inSubstandardQuantity;
  }

  /**
   * 入库次品数量.
   */
  public Integer getInSubstandardQuantity() {
    return this.inSubstandardQuantity;
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
   * 退货通知单明细id.
   */
  public void setReturnNoticeOrderDetailId(Long returnNoticeOrderDetailId) {
    this.returnNoticeOrderDetailId = returnNoticeOrderDetailId;
  }

  /**
   * 退货通知单明细id.
   */
  public Long getReturnNoticeOrderDetailId() {
    return this.returnNoticeOrderDetailId;
  }

  /**
   * 退换货通知单id.
   */
  public void setReturnNoticeOrderId(Long returnNoticeOrderId) {
    this.returnNoticeOrderId = returnNoticeOrderId;
  }

  /**
   * 退换货通知单id.
   */
  public Long getReturnNoticeOrderId() {
    return this.returnNoticeOrderId;
  }

  /**
   * 退换货单code.
   */
  public void setReturnOrderCode(String returnOrderCode) {
    this.returnOrderCode = returnOrderCode == null ? null : returnOrderCode.trim();
  }

  /**
   * 退换货单code.
   */
  public String getReturnOrderCode() {
    return this.returnOrderCode;
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