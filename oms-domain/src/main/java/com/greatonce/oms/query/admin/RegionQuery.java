package com.greatonce.oms.query.admin;

import com.greatonce.core.database.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 区域表.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class RegionQuery extends Query {
  /**
   * 子节点数量.
   */
  private Integer childrenQuantity;
  /**
   * 链路id.
   */
  private String cid;
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 维度.
   */
  private Double latitude;
  /**
   * 级别.
   */
  private Integer level;
  /**
   * 经度.
   */
  private Double longitude;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
  /**
   * 父id.
   */
  private Long parentId;
  /**
   * 区域编码.
   */
  private String regionCode;
  /**
   * 区域id.
   */
  private Long regionId;
  /**
   * 区域名称.
   */
  private String regionName;
  /**
   * 区域短名称.
   */
  private String regionShortName;
  /**
   * 邮编.
   */
  private String zip;


  /**
   * 子节点数量.
   * @param childrenQuantity 子节点数量
   */
  public void setChildrenQuantity(Integer childrenQuantity) {
    this.childrenQuantity = childrenQuantity;
  }

  /**
   * 子节点数量.
   * @return 子节点数量
   */
  public Integer getChildrenQuantity() {
      return this.childrenQuantity;
  }

  /**
   * 链路id.
   * @param cid 链路id
   */
  public void setCid(String cid) {
    this.cid = cid == null ? null : cid.trim();
  }

  /**
   * 链路id.
   * @return 链路id
   */
  public String getCid() {
      return this.cid;
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
   * 维度.
   * @param latitude 维度
   */
  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  /**
   * 维度.
   * @return 维度
   */
  public Double getLatitude() {
      return this.latitude;
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
   * 经度.
   * @param longitude 经度
   */
  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  /**
   * 经度.
   * @return 经度
   */
  public Double getLongitude() {
      return this.longitude;
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
   * 父id.
   * @param parentId 父id
   */
  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  /**
   * 父id.
   * @return 父id
   */
  public Long getParentId() {
      return this.parentId;
  }

  /**
   * 区域编码.
   * @param regionCode 区域编码
   */
  public void setRegionCode(String regionCode) {
    this.regionCode = regionCode == null ? null : regionCode.trim();
  }

  /**
   * 区域编码.
   * @return 区域编码
   */
  public String getRegionCode() {
      return this.regionCode;
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

  /**
   * 区域短名称.
   * @param regionShortName 区域短名称
   */
  public void setRegionShortName(String regionShortName) {
    this.regionShortName = regionShortName == null ? null : regionShortName.trim();
  }

  /**
   * 区域短名称.
   * @return 区域短名称
   */
  public String getRegionShortName() {
      return this.regionShortName;
  }

  /**
   * 邮编.
   * @param zip 邮编
   */
  public void setZip(String zip) {
    this.zip = zip == null ? null : zip.trim();
  }

  /**
   * 邮编.
   * @return 邮编
   */
  public String getZip() {
      return this.zip;
  }
}