package com.greatonce.oms.domain.stock;

import com.greatonce.oms.domain.BaseDO;
import com.greatonce.oms.domain.enums.OrderType;
import com.greatonce.oms.domain.enums.PostStatus;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 入库批次表.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class StockInBatch extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 入库时间.
   */
  private LocalDateTime inTime;
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
   * 过账状态.
   */
  private PostStatus postStatus;
  /**
   * 入库批次id.
   */
  private Long stockInBatchId;
  /**
   * wms单据编码.
   */
  private String wmsOrderCode;

  /**
   * 明细.
   */
  private List<StockInBatchDetail> details;

  @Override
  public void setPrimaryKey(Long pk) {
    this.stockInBatchId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.stockInBatchId;
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
   * 入库时间.
   */
  public void setInTime(LocalDateTime inTime) {
    this.inTime = inTime;
  }

  /**
   * 入库时间.
   */
  public LocalDateTime getInTime() {
    return this.inTime;
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
   * 入库批次id.
   */
  public void setStockInBatchId(Long stockInBatchId) {
    this.stockInBatchId = stockInBatchId;
  }

  /**
   * 入库批次id.
   */
  public Long getStockInBatchId() {
    return this.stockInBatchId;
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
  public void setDetails(List<StockInBatchDetail> details) {
    this.details = details;
  }

  /**
   * 明细.
   */
  public List<StockInBatchDetail> getDetails() {
    return this.details;
  }
}