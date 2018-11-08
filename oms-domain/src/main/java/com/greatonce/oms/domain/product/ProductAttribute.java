package com.greatonce.oms.domain.product;

import com.greatonce.oms.domain.BaseDO;
import com.greatonce.oms.domain.enums.product.ProductAttributeType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 商品属性.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class ProductAttribute extends BaseDO {
  /**
   * 属性id.
   */
  private Long attributeId;
  /**
   * 名称.
   */
  private String attributeName;
  /**
   * 属性类型.
   */
  private ProductAttributeType attributeType;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 字典id.
   */
  private Long dataDictId;
  /**
   * 字典名称.
   */
  private String dataDictName;
  /**
   * 是否系统.
   */
  private Boolean system;
  /**
   * 是否用于编码生成.
   */
  private Boolean useCode;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;


  @Override
  public void setPrimaryKey(Long pk) {
    this.attributeId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.attributeId;
  }


  /**
   * 属性id.
   */
  public void setAttributeId(Long attributeId) {
    this.attributeId = attributeId;
  }

  /**
   * 属性id.
   */
  public Long getAttributeId() {
    return this.attributeId;
  }

  /**
   * 名称.
   */
  public void setAttributeName(String attributeName) {
    this.attributeName = attributeName == null ? null : attributeName.trim();
  }

  /**
   * 名称.
   */
  public String getAttributeName() {
    return this.attributeName;
  }

  /**
   * 属性类型.
   */
  public void setAttributeType(ProductAttributeType attributeType) {
    this.attributeType = attributeType;
  }

  /**
   * 属性类型.
   */
  public ProductAttributeType getAttributeType() {
    return this.attributeType;
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
   * 字典id.
   */
  public void setDataDictId(Long dataDictId) {
    this.dataDictId = dataDictId;
  }

  /**
   * 字典id.
   */
  public Long getDataDictId() {
    return this.dataDictId;
  }

  /**
   * 字典名称.
   */
  public void setDataDictName(String dataDictName) {
    this.dataDictName = dataDictName == null ? null : dataDictName.trim();
  }

  /**
   * 字典名称.
   */
  public String getDataDictName() {
    return this.dataDictName;
  }

  /**
   * 是否系统.
   */
  public void setSystem(Boolean system) {
    this.system = system;
  }

  /**
   * 是否系统.
   */
  public Boolean isSystem() {
    return this.system;
  }

  /**
   * 是否用于编码生成.
   */
  public void setUseCode(Boolean useCode) {
    this.useCode = useCode;
  }

  /**
   * 是否用于编码生成.
   */
  public Boolean isUseCode() {
    return this.useCode;
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