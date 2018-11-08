package com.greatonce.oms.query.admin;

import com.greatonce.core.database.Query;
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
public class GlobalExpressMallMappingQuery extends Query {
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
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
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
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
   * 全局快递商城映射id.
   * @param globalExpressMallMappingId 全局快递商城映射id
   */
  public void setGlobalExpressMallMappingId(Long globalExpressMallMappingId) {
    this.globalExpressMallMappingId = globalExpressMallMappingId;
  }

  /**
   * 全局快递商城映射id.
   * @return 全局快递商城映射id
   */
  public Long getGlobalExpressMallMappingId() {
      return this.globalExpressMallMappingId;
  }

  /**
   * 商城类型.
   * @param mallType 商城类型
   */
  public void setMallType(MallType mallType) {
    this.mallType = mallType;
  }

  /**
   * 商城类型.
   * @return 商城类型
   */
  public MallType getMallType() {
      return this.mallType;
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
   * 外部单号.
   * @param outerCode 外部单号
   */
  public void setOuterCode(String outerCode) {
    this.outerCode = outerCode == null ? null : outerCode.trim();
  }

  /**
   * 外部单号.
   * @return 外部单号
   */
  public String getOuterCode() {
      return this.outerCode;
  }

  /**
   * 外部id.
   * @param outerId 外部id
   */
  public void setOuterId(String outerId) {
    this.outerId = outerId == null ? null : outerId.trim();
  }

  /**
   * 外部id.
   * @return 外部id
   */
  public String getOuterId() {
      return this.outerId;
  }

  /**
   * 外部名称.
   * @param outerName 外部名称
   */
  public void setOuterName(String outerName) {
    this.outerName = outerName == null ? null : outerName.trim();
  }

  /**
   * 外部名称.
   * @return 外部名称
   */
  public String getOuterName() {
      return this.outerName;
  }
}