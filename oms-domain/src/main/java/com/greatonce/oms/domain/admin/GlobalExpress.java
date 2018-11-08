package com.greatonce.oms.domain.admin;

import com.greatonce.oms.domain.EnableDO;
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
public class GlobalExpress extends EnableDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
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
   * 更新时间.
   */
  private LocalDateTime modifiedTime;


  @Override
  public void setPrimaryKey(Long pk) {
    this.globalExpressId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.globalExpressId;
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
   * 全局快递编码.
   */
  public void setGlobalExpressCode(String globalExpressCode) {
    this.globalExpressCode = globalExpressCode == null ? null : globalExpressCode.trim();
  }

  /**
   * 全局快递编码.
   */
  public String getGlobalExpressCode() {
    return this.globalExpressCode;
  }

  /**
   * 标识.
   */
  public void setGlobalExpressId(Long globalExpressId) {
    this.globalExpressId = globalExpressId;
  }

  /**
   * 标识.
   */
  public Long getGlobalExpressId() {
    return this.globalExpressId;
  }

  /**
   * 全局快递名称.
   */
  public void setGlobalExpressName(String globalExpressName) {
    this.globalExpressName = globalExpressName == null ? null : globalExpressName.trim();
  }

  /**
   * 全局快递名称.
   */
  public String getGlobalExpressName() {
    return this.globalExpressName;
  }

  /**
   * 全局快递单号验证表达式.
   */
  public void setGlobalExpressNoRegex(String globalExpressNoRegex) {
    this.globalExpressNoRegex = globalExpressNoRegex == null ? null : globalExpressNoRegex.trim();
  }

  /**
   * 全局快递单号验证表达式.
   */
  public String getGlobalExpressNoRegex() {
    return this.globalExpressNoRegex;
  }

  /**
   * 是否启用.
   */
  @Override
  public void setEnable(Boolean enable) {
    this.enable = enable;
  }

  /**
   * 是否启用.
   */
  @Override
  public Boolean isEnable() {
    return this.enable;
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
}