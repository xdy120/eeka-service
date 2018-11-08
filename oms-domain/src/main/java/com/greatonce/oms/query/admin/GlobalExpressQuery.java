package com.greatonce.oms.query.admin;

import com.greatonce.core.database.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 全局快递.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class GlobalExpressQuery extends Query {
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 全局快递编码.
   */
  private String globalExpressCode;
  /**
   * 标识.
   */
  private Long globalExpressId;
  /**
   * 全局快递名称.
   */
  private String globalExpressName;
  /**
   * 全局快递单号验证表达式.
   */
  private String globalExpressNoRegex;
  /**
   * 是否启用.
   */
  private Boolean enable;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;


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
   * 全局快递编码.
   * @param globalExpressCode 全局快递编码
   */
  public void setGlobalExpressCode(String globalExpressCode) {
    this.globalExpressCode = globalExpressCode == null ? null : globalExpressCode.trim();
  }

  /**
   * 全局快递编码.
   * @return 全局快递编码
   */
  public String getGlobalExpressCode() {
      return this.globalExpressCode;
  }

  /**
   * 标识.
   * @param globalExpressId 标识
   */
  public void setGlobalExpressId(Long globalExpressId) {
    this.globalExpressId = globalExpressId;
  }

  /**
   * 标识.
   * @return 标识
   */
  public Long getGlobalExpressId() {
      return this.globalExpressId;
  }

  /**
   * 全局快递名称.
   * @param globalExpressName 全局快递名称
   */
  public void setGlobalExpressName(String globalExpressName) {
    this.globalExpressName = globalExpressName == null ? null : globalExpressName.trim();
  }

  /**
   * 全局快递名称.
   * @return 全局快递名称
   */
  public String getGlobalExpressName() {
      return this.globalExpressName;
  }

  /**
   * 全局快递单号验证表达式.
   * @param globalExpressNoRegex 全局快递单号验证表达式
   */
  public void setGlobalExpressNoRegex(String globalExpressNoRegex) {
    this.globalExpressNoRegex = globalExpressNoRegex == null ? null : globalExpressNoRegex.trim();
  }

  /**
   * 全局快递单号验证表达式.
   * @return 全局快递单号验证表达式
   */
  public String getGlobalExpressNoRegex() {
      return this.globalExpressNoRegex;
  }

  /**
   * 是否启用.
   * @param enable 是否启用
   */
  public void setEnable(Boolean enable) {
    this.enable = enable;
  }

  /**
   * 是否启用.
   * @return 是否启用
   */
  public Boolean isEnable() {
      return this.enable;
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
}