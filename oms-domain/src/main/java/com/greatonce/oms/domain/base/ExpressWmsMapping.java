package com.greatonce.oms.domain.base;

import com.greatonce.oms.domain.BaseDO;
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
public class ExpressWmsMapping extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
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
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
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


  @Override
  public void setPrimaryKey(Long pk) {
    this.expressWmsMappingId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.expressWmsMappingId;
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
   * 快递id.
   */
  public void setExpressId(Long expressId) {
    this.expressId = expressId;
  }

  /**
   * 快递id.
   */
  public Long getExpressId() {
    return this.expressId;
  }

  /**
   * 快递名称.
   */
  public void setExpressName(String expressName) {
    this.expressName = expressName == null ? null : expressName.trim();
  }

  /**
   * 快递名称.
   */
  public String getExpressName() {
    return this.expressName;
  }

  /**
   * 映射id.
   */
  public void setExpressWmsMappingId(Long expressWmsMappingId) {
    this.expressWmsMappingId = expressWmsMappingId;
  }

  /**
   * 映射id.
   */
  public Long getExpressWmsMappingId() {
    return this.expressWmsMappingId;
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
   * 外部编码.
   */
  public void setOuterCode(String outerCode) {
    this.outerCode = outerCode == null ? null : outerCode.trim();
  }

  /**
   * 外部编码.
   */
  public String getOuterCode() {
    return this.outerCode;
  }

  /**
   * 仓库应用id.
   */
  public void setWmsAppId(Long wmsAppId) {
    this.wmsAppId = wmsAppId;
  }

  /**
   * 仓库应用id.
   */
  public Long getWmsAppId() {
    return this.wmsAppId;
  }

  /**
   * 物流接口编码.
   */
  public void setWmsAppName(String wmsAppName) {
    this.wmsAppName = wmsAppName == null ? null : wmsAppName.trim();
  }

  /**
   * 物流接口编码.
   */
  public String getWmsAppName() {
    return this.wmsAppName;
  }
}