package com.greatonce.oms.domain.base.setting;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * 订单审核策略.
 *
 * @author ginta
 */
public class OrderAuditStrategy implements Serializable {

  /**
   * 名称.
   **/
  private String name;
  /**
   * 启用.
   **/
  private Boolean enable;
  /**
   * 拦截买家留言.
   **/
  private Boolean buyerMessage;
  /**
   * 拦截卖家留言.
   **/
  private Boolean sellerMessage;
  /**
   * 拦截货到付款.
   **/
  private Boolean cod;
  /**
   * 拦截需要发票.
   **/
  private Boolean invoice;
  /**
   * 拦截黑名单会员.
   **/
  private Boolean blackListMember;
  /**
   * 拦截此付款时间之前.
   **/
  private LocalDateTime interceptPoint;
  /**
   * 暂停审单. 固定日期开始
   **/
  private LocalDateTime interceptBeginDate;
  /**
   * 暂停审单. 固定日期结束
   **/
  private LocalDateTime interceptEndDate;
  /**
   * 暂停审单. 每天暂停时间开始
   **/
  private LocalTime interceptBeginTime;
  /**
   * 暂停审单. 每天暂停时间结束
   **/
  private LocalTime interceptEndTime;
  /**
   * 最大金额.
   **/
  private Double maxAmount;
  /**
   * 最小金额.
   **/
  private Double minAmount;
  /**
   * 拦截数量大于等于.
   **/
  private Integer maxQuantity;
  /**
   * 拦截数量小于.
   **/
  private Integer minQuantity;
  /**
   * 拦截商品编码列表.
   **/
  private List<String> interceptProduct;
  /**
   * 拦截区域列表.
   **/
  private List<String> interceptRegion;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean isEnable() {
    return enable;
  }

  public void setEnable(Boolean enable) {
    this.enable = enable;
  }

  public Boolean isBuyerMessage() {
    return buyerMessage;
  }

  public void setBuyerMessage(Boolean buyerMessage) {
    this.buyerMessage = buyerMessage;
  }

  public Boolean isSellerMessage() {
    return sellerMessage;
  }

  public void setSellerMessage(Boolean sellerMessage) {
    this.sellerMessage = sellerMessage;
  }

  public Boolean isCod() {
    return cod;
  }

  public void setCod(Boolean cod) {
    this.cod = cod;
  }

  public Boolean isInvoice() {
    return invoice;
  }

  public void setInvoice(Boolean invoice) {
    this.invoice = invoice;
  }

  public Boolean isBlackListMember() {
    return blackListMember;
  }

  public void setBlackListMember(Boolean blackListMember) {
    this.blackListMember = blackListMember;
  }

  public LocalDateTime getInterceptPoint() {
    return interceptPoint;
  }

  public void setInterceptPoint(LocalDateTime interceptPoint) {
    this.interceptPoint = interceptPoint;
  }

  public LocalDateTime getInterceptBeginDate() {
    return interceptBeginDate;
  }

  public void setInterceptBeginDate(LocalDateTime interceptBeginDate) {
    this.interceptBeginDate = interceptBeginDate;
  }

  public LocalDateTime getInterceptEndDate() {
    return interceptEndDate;
  }

  public void setInterceptEndDate(LocalDateTime interceptEndDate) {
    this.interceptEndDate = interceptEndDate;
  }

  public LocalTime getInterceptBeginTime() {
    return interceptBeginTime;
  }

  public void setInterceptBeginTime(LocalTime interceptBeginTime) {
    this.interceptBeginTime = interceptBeginTime;
  }

  public LocalTime getInterceptEndTime() {
    return interceptEndTime;
  }

  public void setInterceptEndTime(LocalTime interceptEndTime) {
    this.interceptEndTime = interceptEndTime;
  }

  public Double getMaxAmount() {
    return maxAmount;
  }

  public void setMaxAmount(Double maxAmount) {
    this.maxAmount = maxAmount;
  }

  public Double getMinAmount() {
    return minAmount;
  }

  public void setMinAmount(Double minAmount) {
    this.minAmount = minAmount;
  }

  public Integer getMaxQuantity() {
    return maxQuantity;
  }

  public void setMaxQuantity(Integer maxQuantity) {
    this.maxQuantity = maxQuantity;
  }

  public Integer getMinQuantity() {
    return minQuantity;
  }

  public void setMinQuantity(Integer minQuantity) {
    this.minQuantity = minQuantity;
  }

  public List<String> getInterceptProduct() {
    return interceptProduct;
  }

  public void setInterceptProduct(List<String> interceptProduct) {
    this.interceptProduct = interceptProduct;
  }

  public List<String> getInterceptRegion() {
    return interceptRegion;
  }

  public void setInterceptRegion(List<String> interceptRegion) {
    this.interceptRegion = interceptRegion;
  }
}
