package com.greatonce.oms.query.base;

import com.greatonce.core.database.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 仓库快递映射.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class ExpressWmsMappingQuery extends Query {
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 快递id.
   */
  private Long expressId;
  /**
   * 快递名称.
   */
  private String expressName;
  /**
   * 映射id.
   */
  private Long expressWmsMappingId;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
  /**
   * 外部编码.
   */
  private String outerCode;
  /**
   * 仓库应用id.
   */
  private Long wmsAppId;
  /**
   * 物流接口编码.
   */
  private String wmsAppName;


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
   * 快递id.
   * @param expressId 快递id
   */
  public void setExpressId(Long expressId) {
    this.expressId = expressId;
  }

  /**
   * 快递id.
   * @return 快递id
   */
  public Long getExpressId() {
      return this.expressId;
  }

  /**
   * 快递名称.
   * @param expressName 快递名称
   */
  public void setExpressName(String expressName) {
    this.expressName = expressName == null ? null : expressName.trim();
  }

  /**
   * 快递名称.
   * @return 快递名称
   */
  public String getExpressName() {
      return this.expressName;
  }

  /**
   * 映射id.
   * @param expressWmsMappingId 映射id
   */
  public void setExpressWmsMappingId(Long expressWmsMappingId) {
    this.expressWmsMappingId = expressWmsMappingId;
  }

  /**
   * 映射id.
   * @return 映射id
   */
  public Long getExpressWmsMappingId() {
      return this.expressWmsMappingId;
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
   * 外部编码.
   * @param outerCode 外部编码
   */
  public void setOuterCode(String outerCode) {
    this.outerCode = outerCode == null ? null : outerCode.trim();
  }

  /**
   * 外部编码.
   * @return 外部编码
   */
  public String getOuterCode() {
      return this.outerCode;
  }

  /**
   * 仓库应用id.
   * @param wmsAppId 仓库应用id
   */
  public void setWmsAppId(Long wmsAppId) {
    this.wmsAppId = wmsAppId;
  }

  /**
   * 仓库应用id.
   * @return 仓库应用id
   */
  public Long getWmsAppId() {
      return this.wmsAppId;
  }

  /**
   * 物流接口编码.
   * @param wmsAppName 物流接口编码
   */
  public void setWmsAppName(String wmsAppName) {
    this.wmsAppName = wmsAppName == null ? null : wmsAppName.trim();
  }

  /**
   * 物流接口编码.
   * @return 物流接口编码
   */
  public String getWmsAppName() {
      return this.wmsAppName;
  }
}