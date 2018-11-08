package com.greatonce.oms.query.trade;

import com.greatonce.core.database.Query;
import com.greatonce.oms.domain.enums.MarketingType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 销售订单活动表.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class SalesOrderMarketingQuery extends Query {
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 营销编码.
   */
  private String marketingCode;
  /**
   * 营销id.
   */
  private Long marketingId;
  /**
   * 营销名称.
   */
  private String marketingName;
  /**
   * 营销类型.
   */
  private MarketingType marketingType;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
  /**
   * 销售单id.
   */
  private Long salesOrderId;
  /**
   * 销售订单营销表id.
   */
  private Long salesOrderMarketingId;


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
   * 营销编码.
   * @param marketingCode 营销编码
   */
  public void setMarketingCode(String marketingCode) {
    this.marketingCode = marketingCode == null ? null : marketingCode.trim();
  }

  /**
   * 营销编码.
   * @return 营销编码
   */
  public String getMarketingCode() {
      return this.marketingCode;
  }

  /**
   * 营销id.
   * @param marketingId 营销id
   */
  public void setMarketingId(Long marketingId) {
    this.marketingId = marketingId;
  }

  /**
   * 营销id.
   * @return 营销id
   */
  public Long getMarketingId() {
      return this.marketingId;
  }

  /**
   * 营销名称.
   * @param marketingName 营销名称
   */
  public void setMarketingName(String marketingName) {
    this.marketingName = marketingName == null ? null : marketingName.trim();
  }

  /**
   * 营销名称.
   * @return 营销名称
   */
  public String getMarketingName() {
      return this.marketingName;
  }

  /**
   * 营销类型.
   * @param marketingType 营销类型
   */
  public void setMarketingType(MarketingType marketingType) {
    this.marketingType = marketingType;
  }

  /**
   * 营销类型.
   * @return 营销类型
   */
  public MarketingType getMarketingType() {
      return this.marketingType;
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
   * 销售订单营销表id.
   * @param salesOrderMarketingId 销售订单营销表id
   */
  public void setSalesOrderMarketingId(Long salesOrderMarketingId) {
    this.salesOrderMarketingId = salesOrderMarketingId;
  }

  /**
   * 销售订单营销表id.
   * @return 销售订单营销表id
   */
  public Long getSalesOrderMarketingId() {
      return this.salesOrderMarketingId;
  }
}