package com.greatonce.oms.domain.message;

import com.greatonce.oms.domain.BaseDO;
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
public class Message extends BaseDO {
  /**
   * 内容.
   */
  private String content;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 过期时间.
   */
  private LocalDateTime expirationTime;
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
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 链接.
   */
  private String url;
  /**
   * 用户id.
   */
  private Long userId;


  @Override
  public void setPrimaryKey(Long pk) {
    this.messageId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.messageId;
  }


  /**
   * 内容.
   */
  public void setContent(String content) {
    this.content = content == null ? null : content.trim();
  }

  /**
   * 内容.
   */
  public String getContent() {
    return this.content;
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
   * 过期时间.
   */
  public void setExpirationTime(LocalDateTime expirationTime) {
    this.expirationTime = expirationTime;
  }

  /**
   * 过期时间.
   */
  public LocalDateTime getExpirationTime() {
    return this.expirationTime;
  }

  /**
   * 是否已读.
   */
  public void setRead(Boolean read) {
    this.read = read;
  }

  /**
   * 是否已读.
   */
  public Boolean isRead() {
    return this.read;
  }

  /**
   * 消息id.
   */
  public void setMessageId(Long messageId) {
    this.messageId = messageId;
  }

  /**
   * 消息id.
   */
  public Long getMessageId() {
    return this.messageId;
  }

  /**
   * 消息类型.
   */
  public void setMessageType(MessageType messageType) {
    this.messageType = messageType;
  }

  /**
   * 消息类型.
   */
  public MessageType getMessageType() {
    return this.messageType;
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
   * 链接.
   */
  public void setUrl(String url) {
    this.url = url == null ? null : url.trim();
  }

  /**
   * 链接.
   */
  public String getUrl() {
    return this.url;
  }

  /**
   * 用户id.
   */
  public void setUserId(Long userId) {
    this.userId = userId;
  }

  /**
   * 用户id.
   */
  public Long getUserId() {
    return this.userId;
  }
}