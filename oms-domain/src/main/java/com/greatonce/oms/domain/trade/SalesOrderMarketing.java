package com.greatonce.oms.domain.trade;

import com.greatonce.oms.domain.BaseDO;
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
public class SalesOrderMarketing extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
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
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 销售单id.
   */
  private Long salesOrderId;
  /**
   * 销售订单营销表id.
   */
  private Long salesOrderMarketingId;


  @Override
  public void setPrimaryKey(Long pk) {
    this.salesOrderMarketingId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.salesOrderMarketingId;
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
   * 营销编码.
   */
  public void setMarketingCode(String marketingCode) {
    this.marketingCode = marketingCode == null ? null : marketingCode.trim();
  }

  /**
   * 营销编码.
   */
  public String getMarketingCode() {
    return this.marketingCode;
  }

  /**
   * 营销id.
   */
  public void setMarketingId(Long marketingId) {
    this.marketingId = marketingId;
  }

  /**
   * 营销id.
   */
  public Long getMarketingId() {
    return this.marketingId;
  }

  /**
   * 营销名称.
   */
  public void setMarketingName(String marketingName) {
    this.marketingName = marketingName == null ? null : marketingName.trim();
  }

  /**
   * 营销名称.
   */
  public String getMarketingName() {
    return this.marketingName;
  }

  /**
   * 营销类型.
   */
  public void setMarketingType(MarketingType marketingType) {
    this.marketingType = marketingType;
  }

  /**
   * 营销类型.
   */
  public MarketingType getMarketingType() {
    return this.marketingType;
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
   * 销售订单营销表id.
   */
  public void setSalesOrderMarketingId(Long salesOrderMarketingId) {
    this.salesOrderMarketingId = salesOrderMarketingId;
  }

  /**
   * 销售订单营销表id.
   */
  public Long getSalesOrderMarketingId() {
    return this.salesOrderMarketingId;
  }
}