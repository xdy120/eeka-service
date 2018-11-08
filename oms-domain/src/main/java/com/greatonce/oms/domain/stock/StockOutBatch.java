package com.greatonce.oms.domain.stock;

import com.greatonce.oms.domain.BaseDO;
import com.greatonce.oms.domain.enums.OrderType;
import com.greatonce.oms.domain.enums.PostStatus;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 出库批次表.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class StockOutBatch extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 通知单编码.
   */
  private String noticeOrderCode;
  /**
   * 通知单id.
   */
  private Long noticeOrderId;
  /**
   * 通知单类型.
   */
  private OrderType noticeOrderType;
  /**
   * 出库时间.
   */
  private LocalDateTime outTime;
  /**
   * 过账状态.
   */
  private PostStatus postStatus;
  /**
   * 出库批次id.
   */
  private Long stockOutBatchId;
  /**
   * wms单据编码.
   */
  private String wmsOrderCode;

  /**
   * 明细.
   */
  private List<StockOutBatchDetail> details;

  @Override
  public void setPrimaryKey(Long pk) {
    this.stockOutBatchId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.stockOutBatchId;
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
   * 通知单编码.
   */
  public void setNoticeOrderCode(String noticeOrderCode) {
    this.noticeOrderCode = noticeOrderCode == null ? null : noticeOrderCode.trim();
  }

  /**
   * 通知单编码.
   */
  public String getNoticeOrderCode() {
    return this.noticeOrderCode;
  }

  /**
   * 通知单id.
   */
  public void setNoticeOrderId(Long noticeOrderId) {
    this.noticeOrderId = noticeOrderId;
  }

  /**
   * 通知单id.
   */
  public Long getNoticeOrderId() {
    return this.noticeOrderId;
  }

  /**
   * 通知单类型.
   */
  public void setNoticeOrderType(OrderType noticeOrderType) {
    this.noticeOrderType = noticeOrderType;
  }

  /**
   * 通知单类型.
   */
  public OrderType getNoticeOrderType() {
    return this.noticeOrderType;
  }

  /**
   * 出库时间.
   */
  public void setOutTime(LocalDateTime outTime) {
    this.outTime = outTime;
  }

  /**
   * 出库时间.
   */
  public LocalDateTime getOutTime() {
    return this.outTime;
  }

  /**
   * 过账状态.
   */
  public void setPostStatus(PostStatus postStatus) {
    this.postStatus = postStatus;
  }

  /**
   * 过账状态.
   */
  public PostStatus getPostStatus() {
    return this.postStatus;
  }

  /**
   * 出库批次id.
   */
  public void setStockOutBatchId(Long stockOutBatchId) {
    this.stockOutBatchId = stockOutBatchId;
  }

  /**
   * 出库批次id.
   */
  public Long getStockOutBatchId() {
    return this.stockOutBatchId;
  }

  /**
   * wms单据编码.
   */
  public void setWmsOrderCode(String wmsOrderCode) {
    this.wmsOrderCode = wmsOrderCode == null ? null : wmsOrderCode.trim();
  }

  /**
   * wms单据编码.
   */
  public String getWmsOrderCode() {
    return this.wmsOrderCode;
  }

  /**
   * 明细.
   */
  public void setDetails(List<StockOutBatchDetail> details) {
    this.details = details;
  }

  /**
   * 明细.
   */
  public List<StockOutBatchDetail> getDetails() {
    return this.details;
  }
}