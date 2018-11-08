package com.greatonce.oms.query.stock;

import com.greatonce.core.database.Query;
import com.greatonce.oms.domain.enums.OrderType;
import com.greatonce.oms.domain.enums.PostStatus;
import java.time.LocalDateTime;

/**
 * 出库批次表.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class StockOutBatchQuery extends Query {
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
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
   * 出库时间开始.
   */
  private LocalDateTime outTimeBegin;
  /**
   * 出库时间结束.
   */
  private LocalDateTime outTimeEnd;
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
   * 出库时间开始.
   * @param outTimeBegin 开始.
   */
  public void setOutTimeBegin(LocalDateTime outTimeBegin) {
    this.outTimeBegin = outTimeBegin;
  }

  /**
   * 出库时间开始.
   * @return 出库时间开始
   */
  public LocalDateTime getOutTimeBegin() {
    return this.outTimeBegin;
  }

  /**
   * 出库时间结束.
   * @param outTimeEnd 结束
   */
  public void setOutTimeEnd(LocalDateTime outTimeEnd) {
    this.outTimeEnd = outTimeEnd;
  }

  /**
   * 出库时间结束.
   * @return 出库时间结束
   */
  public LocalDateTime getOutTimeEnd() {
    return this.outTimeEnd;
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
   * 出库批次id.
   * @param stockOutBatchId 出库批次id
   */
  public void setStockOutBatchId(Long stockOutBatchId) {
    this.stockOutBatchId = stockOutBatchId;
  }

  /**
   * 出库批次id.
   * @return 出库批次id
   */
  public Long getStockOutBatchId() {
      return this.stockOutBatchId;
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