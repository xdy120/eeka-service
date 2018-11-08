package com.greatonce.oms.query.base;

import com.greatonce.core.database.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 快递区域.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class ExpressRegionQuery extends Query {
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
   * 快递区域id.
   */
  private Long expressRegionId;
  /**
   * 是否关键字全到.
   */
  private Boolean keywordArrive;
  /**
   * 关键字.
   */
  private String keyword;
  /**
   * 级别.
   */
  private Integer level;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
  /**
   * 集合点编码.
   */
  private String rallyPointCode;
  /**
   * 集合点名称.
   */
  private String rallyPointName;
  /**
   * 区域id.
   */
  private Long regionId;
  /**
   * 区域名称.
   */
  private String regionName;


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
   * 快递区域id.
   * @param expressRegionId 快递区域id
   */
  public void setExpressRegionId(Long expressRegionId) {
    this.expressRegionId = expressRegionId;
  }

  /**
   * 快递区域id.
   * @return 快递区域id
   */
  public Long getExpressRegionId() {
      return this.expressRegionId;
  }

  /**
   * 是否关键字全到.
   * @param keywordArrive 是否关键字全到
   */
  public void setKeywordArrive(Boolean keywordArrive) {
    this.keywordArrive = keywordArrive;
  }

  /**
   * 是否关键字全到.
   * @return 是否关键字全到
   */
  public Boolean isKeywordArrive() {
      return this.keywordArrive;
  }

  /**
   * 关键字.
   * @param keyword 关键字
   */
  public void setKeyword(String keyword) {
    this.keyword = keyword == null ? null : keyword.trim();
  }

  /**
   * 关键字.
   * @return 关键字
   */
  public String getKeyword() {
      return this.keyword;
  }

  /**
   * 级别.
   * @param level 级别
   */
  public void setLevel(Integer level) {
    this.level = level;
  }

  /**
   * 级别.
   * @return 级别
   */
  public Integer getLevel() {
      return this.level;
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
   * 集合点编码.
   * @param rallyPointCode 集合点编码
   */
  public void setRallyPointCode(String rallyPointCode) {
    this.rallyPointCode = rallyPointCode == null ? null : rallyPointCode.trim();
  }

  /**
   * 集合点编码.
   * @return 集合点编码
   */
  public String getRallyPointCode() {
      return this.rallyPointCode;
  }

  /**
   * 集合点名称.
   * @param rallyPointName 集合点名称
   */
  public void setRallyPointName(String rallyPointName) {
    this.rallyPointName = rallyPointName == null ? null : rallyPointName.trim();
  }

  /**
   * 集合点名称.
   * @return 集合点名称
   */
  public String getRallyPointName() {
      return this.rallyPointName;
  }

  /**
   * 区域id.
   * @param regionId 区域id
   */
  public void setRegionId(Long regionId) {
    this.regionId = regionId;
  }

  /**
   * 区域id.
   * @return 区域id
   */
  public Long getRegionId() {
      return this.regionId;
  }

  /**
   * 区域名称.
   * @param regionName 区域名称
   */
  public void setRegionName(String regionName) {
    this.regionName = regionName == null ? null : regionName.trim();
  }

  /**
   * 区域名称.
   * @return 区域名称
   */
  public String getRegionName() {
      return this.regionName;
  }
}