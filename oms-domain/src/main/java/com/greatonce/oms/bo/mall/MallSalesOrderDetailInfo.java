package com.greatonce.oms.bo.mall;

import com.greatonce.oms.domain.enums.mall.MallRefundStatus;
import com.greatonce.oms.domain.enums.mall.MallSalesOrderDetailStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 商城订单明细
 * MallSalesOrderDetailInfo
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/9
 */
public class MallSalesOrderDetailInfo {

  /**
   * 子订单编号
   */
  private String oid;
  /**
   * 是否已发货
   */
  private boolean deliveried;
  /**
   * 预计发货时间
   */
  private LocalDate presellDeliveryDate;
  /**
   * 平台发货要求
   */
  private String mallPresellDeliveryRequiring;
  /**
   * 是否门店发货
   */
  private boolean shopDelivery;
  /**
   * 门店ID
   */
  private String shopCode;
  /**
   * 修改时间
   */
  private LocalDateTime modifiedTime;
  /**
   * 状态
   */
  private MallSalesOrderDetailStatus status;
  /**
   * 平台的商品编号
   */
  private String mallProductId;
  /**
   * 商品标题
   */
  private String mallProductName;
  /**
   * 商品代码
   */
  private String outerCode;
  /**
   * 平台的商品数字ID
   */
  private String mallSkuId;
  /**
   * sku属性名称
   */
  private String mallSkuName;
  /**
   * 商品规格代码
   */
  private String outerSkuCode;
  /**
   * 商品数量
   */
  private Integer quantity;
  /**
   * 退款状态
   */
  private MallRefundStatus refundStatus;
  /**
   * 分销价
   */
  private Double distributionPrice;
  /**
   * 销售价（原始吊牌价）
   */
  private Double sellingPrice;
  /**
   * 实际售价（成交价格）
   */
  private Double actualSellingPrice;
  /**
   * 分销金额
   */
  private Double distributionAmount;
  /**
   * 销售金额（销售价*数量）
   */
  private Double sellingAmount;
  /**
   * 实际销售金额（销售金额*数量）
   */
  private Double actualSellingAmount;
  /**
   * 优惠金额（整单优惠金额*销售金额/整单结算价）
   */
  private Double discountAmount;
  /**
   * 结算金额（销售金额-优惠金额）
   */
  private Double settlementAmount;

  public MallSalesOrderDetailInfo() {
    shopDelivery = false;
    discountAmount = 0D;
    distributionPrice = 0D;
    distributionAmount = 0D;
    refundStatus = MallRefundStatus.UNKNOWN;
    status = MallSalesOrderDetailStatus.UNKNOWN;
  }

  public String getOid() {
    return oid;
  }

  public void setOid(String oid) {
    this.oid = oid;
  }

  public LocalDate getPresellDeliveryDate() {
    return presellDeliveryDate;
  }

  public void setPresellDeliveryDate(LocalDate presellDeliveryDate) {
    this.presellDeliveryDate = presellDeliveryDate;
  }

  public boolean isShopDelivery() {
    return shopDelivery;
  }

  public void setShopDelivery(boolean shopDelivery) {
    this.shopDelivery = shopDelivery;
  }

  public String getShopCode() {
    return shopCode;
  }

  public void setShopCode(String shopCode) {
    this.shopCode = shopCode;
  }

  public LocalDateTime getModifiedTime() {
    return modifiedTime;
  }

  public void setModifiedTime(LocalDateTime modifiedTime) {
    this.modifiedTime = modifiedTime;
  }

  public MallSalesOrderDetailStatus getStatus() {
    return status;
  }

  public void setStatus(MallSalesOrderDetailStatus status) {
    this.status = status;
  }

  public String getMallProductId() {
    return mallProductId;
  }

  public void setMallProductId(String mallProductId) {
    this.mallProductId = mallProductId;
  }

  public String getMallProductName() {
    return mallProductName;
  }

  public void setMallProductName(String mallProductName) {
    this.mallProductName = mallProductName;
  }

  public String getOuterCode() {
    return outerCode;
  }

  public void setOuterCode(String outerCode) {
    this.outerCode = outerCode;
  }

  public String getMallSkuId() {
    return mallSkuId;
  }

  public void setMallSkuId(String mallSkuId) {
    this.mallSkuId = mallSkuId;
  }

  public String getMallSkuName() {
    return mallSkuName;
  }

  public void setMallSkuName(String mallSkuName) {
    this.mallSkuName = mallSkuName;
  }

  public String getOuterSkuCode() {
    return outerSkuCode;
  }

  public void setOuterSkuCode(String outerSkuCode) {
    this.outerSkuCode = outerSkuCode;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public MallRefundStatus getRefundStatus() {
    return refundStatus;
  }

  public void setRefundStatus(MallRefundStatus refundStatus) {
    this.refundStatus = refundStatus;
  }

  public Double getDistributionPrice() {
    return distributionPrice;
  }

  public void setDistributionPrice(Double distributionPrice) {
    this.distributionPrice = distributionPrice;
  }

  public Double getSellingPrice() {
    return sellingPrice;
  }

  public void setSellingPrice(Double sellingPrice) {
    this.sellingPrice = sellingPrice;
  }

  public Double getActualSellingPrice() {
    return actualSellingPrice;
  }

  public void setActualSellingPrice(Double actualSellingPrice) {
    this.actualSellingPrice = actualSellingPrice;
  }

  public Double getDistributionAmount() {
    return distributionAmount;
  }

  public void setDistributionAmount(Double distributionAmount) {
    this.distributionAmount = distributionAmount;
  }

  public Double getSellingAmount() {
    return sellingAmount;
  }

  public void setSellingAmount(Double sellingAmount) {
    this.sellingAmount = sellingAmount;
  }

  public Double getActualSellingAmount() {
    return actualSellingAmount;
  }

  public void setActualSellingAmount(Double actualSellingAmount) {
    this.actualSellingAmount = actualSellingAmount;
  }

  public Double getDiscountAmount() {
    return discountAmount;
  }

  public void setDiscountAmount(Double discountAmount) {
    this.discountAmount = discountAmount;
  }

  public Double getSettlementAmount() {
    return settlementAmount;
  }

  public void setSettlementAmount(Double settlementAmount) {
    this.settlementAmount = settlementAmount;
  }

  public String getMallPresellDeliveryRequiring() {
    return mallPresellDeliveryRequiring;
  }

  public void setMallPresellDeliveryRequiring(String mallPresellDeliveryRequiring) {
    this.mallPresellDeliveryRequiring = mallPresellDeliveryRequiring;
  }

  public boolean isDeliveried() {
    return deliveried;
  }

  public void setDeliveried(boolean deliveried) {
    this.deliveried = deliveried;
  }
}
