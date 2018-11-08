package com.greatonce.oms.query.stock;

import com.greatonce.core.database.Query;
import com.greatonce.oms.domain.enums.OrderType;
import com.greatonce.oms.domain.enums.PostStatus;
import java.time.LocalDateTime;

/**
 * 入库批次表.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class StockInBatchQuery extends Query {
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 入库时间开始.
   */
  private LocalDateTime inTimeBegin;
  /**
   * 入库时间结束.
   */
  private LocalDateTime inTimeEnd;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
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
   * 入库时间开始.
   * @param inTimeBegin 开始.
   */
  public void setInTimeBegin(LocalDateTime inTimeBegin) {
    this.inTimeBegin = inTimeBegin;
  }

  /**
   * 入库时间开始.
   * @return 入库时间开始
   */
  public LocalDateTime getInTimeBegin() {
    return this.inTimeBegin;
  }

  /**
   * 入库时间结束.
   * @param inTimeEnd 结束
   */
  public void setInTimeEnd(LocalDateTime inTimeEnd) {
    this.inTimeEnd = inTimeEnd;
  }

  /**
   * 入库时间结束.
   * @return 入库时间结束
   */
  public LocalDateTime getInTimeEnd() {
    return this.inTimeEnd;
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
   * 通知单编码.
   * @param noticeOrderCode 通知单编码
   */
  public void setNoticeOrderCode(String noticeOrderCode) {
    this.noticeOrderCode = noticeOrderCode == null ? null : noticeOrderCode.trim();
  }

  /**
   * 通知单编码.
   * @return 通知单编码
   */
  public String getNoticeOrderCode() {
      return this.noticeOrderCode;
  }

  /**
   * 通知单id.
   * @param noticeOrderId 通知单id
   */
  public void setNoticeOrderId(Long noticeOrderId) {
    this.noticeOrderId = noticeOrderId;
  }

  /**
   * 通知单id.
   * @return 通知单id
   */
  public Long getNoticeOrderId() {
      return this.noticeOrderId;
  }

  /**
   * 通知单类型.
   * @param noticeOrderType 通知单类型
   */
  public void setNoticeOrderType(OrderType noticeOrderType) {
    this.noticeOrderType = noticeOrderType;
  }

  /**
   * 通知单类型.
   * @return 通知单类型
   */
  public OrderType getNoticeOrderType() {
      return this.noticeOrderType;
  }

  /**
   * 过账状态.
   * @param postStatus 过账状态
   */
  public void setPostStatus(PostStatus postStatus) {
    this.postStatus = postStatus;
  }

  /**
   * 过账状态.
   * @return 过账状态
   */
  public PostStatus getPostStatus() {
      return this.postStatus;
  }

  /**
   * 入库批次id.
   * @param stockInBatchId 入库批次id
   */
  public void setStockInBatchId(Long stockInBatchId) {
    this.stockInBatchId = stockInBatchId;
  }

  /**
   * 入库批次id.
   * @return 入库批次id
   */
  public Long getStockInBatchId() {
      return this.stockInBatchId;
  }

  /**
   * wms单据编码.
   * @param wmsOrderCode wms单据编码
   */
  public void setWmsOrderCode(String wmsOrderCode) {
    this.wmsOrderCode = wmsOrderCode == null ? null : wmsOrderCode.trim();
  }

  /**
   * wms单据编码.
   * @return wms单据编码
   */
  public String getWmsOrderCode() {
      return this.wmsOrderCode;
  }
}