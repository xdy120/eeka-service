package com.greatonce.oms.query.message;

import com.greatonce.core.database.Query;
import com.greatonce.oms.domain.enums.message.MessageType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 消息.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class MessageQuery extends Query {
  /**
   * 内容.
   */
  private String content;
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 过期时间开始.
   */
  private LocalDateTime expirationTimeBegin;
  /**
   * 过期时间结束.
   */
  private LocalDateTime expirationTimeEnd;
  /**
   * 是否已读.
   */
  private Boolean read;
  /**
   * 消息id.
   */
  private Long messageId;
  /**
   * 消息类型.
   */
  private MessageType messageType;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
  /**
   * 链接.
   */
  private String url;
  /**
   * 用户id.
   */
  private Long userId;


  /**
   * 内容.
   * @param content 内容
   */
  public void setContent(String content) {
    this.content = content == null ? null : content.trim();
  }

  /**
   * 内容.
   * @return 内容
   */
  public String getContent() {
      return this.content;
  }

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
   * 过期时间开始.
   * @param expirationTimeBegin 开始.
   */
  public void setExpirationTimeBegin(LocalDateTime expirationTimeBegin) {
    this.expirationTimeBegin = expirationTimeBegin;
  }

  /**
   * 过期时间开始.
   * @return 过期时间开始
   */
  public LocalDateTime getExpirationTimeBegin() {
    return this.expirationTimeBegin;
  }

  /**
   * 过期时间结束.
   * @param expirationTimeEnd 结束
   */
  public void setExpirationTimeEnd(LocalDateTime expirationTimeEnd) {
    this.expirationTimeEnd = expirationTimeEnd;
  }

  /**
   * 过期时间结束.
   * @return 过期时间结束
   */
  public LocalDateTime getExpirationTimeEnd() {
    return this.expirationTimeEnd;
  }

  /**
   * 是否已读.
   * @param read 是否已读
   */
  public void setRead(Boolean read) {
    this.read = read;
  }

  /**
   * 是否已读.
   * @return 是否已读
   */
  public Boolean isRead() {
      return this.read;
  }

  /**
   * 消息id.
   * @param messageId 消息id
   */
  public void setMessageId(Long messageId) {
    this.messageId = messageId;
  }

  /**
   * 消息id.
   * @return 消息id
   */
  public Long getMessageId() {
      return this.messageId;
  }

  /**
   * 消息类型.
   * @param messageType 消息类型
   */
  public void setMessageType(MessageType messageType) {
    this.messageType = messageType;
  }

  /**
   * 消息类型.
   * @return 消息类型
   */
  public MessageType getMessageType() {
      return this.messageType;
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
   * 链接.
   * @param url 链接
   */
  public void setUrl(String url) {
    this.url = url == null ? null : url.trim();
  }

  /**
   * 链接.
   * @return 链接
   */
  public String getUrl() {
      return this.url;
  }

  /**
   * 用户id.
   * @param userId 用户id
   */
  public void setUserId(Long userId) {
    this.userId = userId;
  }

  /**
   * 用户id.
   * @return 用户id
   */
  public Long getUserId() {
      return this.userId;
  }
}