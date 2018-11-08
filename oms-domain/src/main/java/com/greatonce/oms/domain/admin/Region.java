package com.greatonce.oms.domain.admin;

import com.greatonce.oms.domain.BaseDO;
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
public class Region extends BaseDO {
  /**
   * 子节点数量.
   */
  private Integer childrenQuantity;
  /**
   * 链路id.
   */
  private String cid;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
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
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
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
   * 子区域.
   */
  private List<Region> children;

  @Override
  public void setPrimaryKey(Long pk) {
    this.regionId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.regionId;
  }


  /**
   * 子节点数量.
   */
  public void setChildrenQuantity(Integer childrenQuantity) {
    this.childrenQuantity = childrenQuantity;
  }

  /**
   * 子节点数量.
   */
  public Integer getChildrenQuantity() {
    return this.childrenQuantity;
  }

  /**
   * 链路id.
   */
  public void setCid(String cid) {
    this.cid = cid == null ? null : cid.trim();
  }

  /**
   * 链路id.
   */
  public String getCid() {
    return this.cid;
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
   * 维度.
   */
  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  /**
   * 维度.
   */
  public Double getLatitude() {
    return this.latitude;
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
   * 经度.
   */
  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  /**
   * 经度.
   */
  public Double getLongitude() {
    return this.longitude;
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
   * 父id.
   */
  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  /**
   * 父id.
   */
  public Long getParentId() {
    return this.parentId;
  }

  /**
   * 区域编码.
   */
  public void setRegionCode(String regionCode) {
    this.regionCode = regionCode == null ? null : regionCode.trim();
  }

  /**
   * 区域编码.
   */
  public String getRegionCode() {
    return this.regionCode;
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

  /**
   * 区域短名称.
   */
  public void setRegionShortName(String regionShortName) {
    this.regionShortName = regionShortName == null ? null : regionShortName.trim();
  }

  /**
   * 区域短名称.
   */
  public String getRegionShortName() {
    return this.regionShortName;
  }

  /**
   * 邮编.
   */
  public void setZip(String zip) {
    this.zip = zip == null ? null : zip.trim();
  }

  /**
   * 邮编.
   */
  public String getZip() {
    return this.zip;
  }

  /**
   * 子区域.
   */
  public void setChildren(List<Region> children) {
    this.children = children;
  }

  /**
   * 子区域.
   */
  public List<Region> getChildren() {
    return this.children;
  }
}