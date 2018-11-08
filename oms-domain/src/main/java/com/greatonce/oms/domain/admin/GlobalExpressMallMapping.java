package com.greatonce.oms.domain.admin;

import com.greatonce.oms.domain.BaseDO;
import com.greatonce.oms.domain.enums.MallType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 全局快递商城映射.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class GlobalExpressMallMapping extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 标识.
   */
  private Long globalExpressId;
  /**
   * 全局快递商城映射id.
   */
  private Long globalExpressMallMappingId;
  /**
   * 商城类型.
   */
  private MallType mallType;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 外部单号.
   */
  private String outerCode;
  /**
   * 外部id.
   */
  private String outerId;
  /**
   * 外部名称.
   */
  private String outerName;


  @Override
  public void setPrimaryKey(Long pk) {
    this.globalExpressMallMappingId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.globalExpressMallMappingId;
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
   * 全局快递商城映射id.
   */
  public void setGlobalExpressMallMappingId(Long globalExpressMallMappingId) {
    this.globalExpressMallMappingId = globalExpressMallMappingId;
  }

  /**
   * 全局快递商城映射id.
   */
  public Long getGlobalExpressMallMappingId() {
    return this.globalExpressMallMappingId;
  }

  /**
   * 商城类型.
   */
  public void setMallType(MallType mallType) {
    this.mallType = mallType;
  }

  /**
   * 商城类型.
   */
  public MallType getMallType() {
    return this.mallType;
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
   * 外部单号.
   */
  public void setOuterCode(String outerCode) {
    this.outerCode = outerCode == null ? null : outerCode.trim();
  }

  /**
   * 外部单号.
   */
  public String getOuterCode() {
    return this.outerCode;
  }

  /**
   * 外部id.
   */
  public void setOuterId(String outerId) {
    this.outerId = outerId == null ? null : outerId.trim();
  }

  /**
   * 外部id.
   */
  public String getOuterId() {
    return this.outerId;
  }

  /**
   * 外部名称.
   */
  public void setOuterName(String outerName) {
    this.outerName = outerName == null ? null : outerName.trim();
  }

  /**
   * 外部名称.
   */
  public String getOuterName() {
    return this.outerName;
  }
}