package com.greatonce.oms.domain.base;

import com.greatonce.oms.domain.BaseDO;
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
public class ExpressRegion extends BaseDO {
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
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
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


  @Override
  public void setPrimaryKey(Long pk) {
    this.expressRegionId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.expressRegionId;
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
   * 快递区域id.
   */
  public void setExpressRegionId(Long expressRegionId) {
    this.expressRegionId = expressRegionId;
  }

  /**
   * 快递区域id.
   */
  public Long getExpressRegionId() {
    return this.expressRegionId;
  }

  /**
   * 是否关键字全到.
   */
  public void setKeywordArrive(Boolean keywordArrive) {
    this.keywordArrive = keywordArrive;
  }

  /**
   * 是否关键字全到.
   */
  public Boolean isKeywordArrive() {
    return this.keywordArrive;
  }

  /**
   * 关键字.
   */
  public void setKeyword(String keyword) {
    this.keyword = keyword == null ? null : keyword.trim();
  }

  /**
   * 关键字.
   */
  public String getKeyword() {
    return this.keyword;
  }

  /**
   * 级别.
   */
  public void setLevel(Integer level) {
    this.level = level;
  }

  /**
   * 级别.
   */
  public Integer getLevel() {
    return this.level;
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
   * 集合点编码.
   */
  public void setRallyPointCode(String rallyPointCode) {
    this.rallyPointCode = rallyPointCode == null ? null : rallyPointCode.trim();
  }

  /**
   * 集合点编码.
   */
  public String getRallyPointCode() {
    return this.rallyPointCode;
  }

  /**
   * 集合点名称.
   */
  public void setRallyPointName(String rallyPointName) {
    this.rallyPointName = rallyPointName == null ? null : rallyPointName.trim();
  }

  /**
   * 集合点名称.
   */
  public String getRallyPointName() {
    return this.rallyPointName;
  }

  /**
   * 区域id.
   */
  public void setRegionId(Long regionId) {
    this.regionId = regionId;
  }

  /**
   * 区域id.
   */
  public Long getRegionId() {
    return this.regionId;
  }

  /**
   * 区域名称.
   */
  public void setRegionName(String regionName) {
    this.regionName = regionName == null ? null : regionName.trim();
  }

  /**
   * 区域名称.
   */
  public String getRegionName() {
    return this.regionName;
  }
}