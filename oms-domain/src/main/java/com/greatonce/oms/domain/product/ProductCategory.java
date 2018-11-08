package com.greatonce.oms.domain.product;

import com.greatonce.oms.domain.BaseDO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 商品分类.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class ProductCategory extends BaseDO {
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
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 父id.
   */
  private Long parentId;
  /**
   * 分类编码.
   */
  private String productCategoryCode;
  /**
   * 分类id.
   */
  private Long productCategoryId;
  /**
   * 分类名称.
   */
  private String productCategoryName;


  @Override
  public void setPrimaryKey(Long pk) {
    this.productCategoryId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.productCategoryId;
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
   * 分类编码.
   */
  public void setProductCategoryCode(String productCategoryCode) {
    this.productCategoryCode = productCategoryCode == null ? null : productCategoryCode.trim();
  }

  /**
   * 分类编码.
   */
  public String getProductCategoryCode() {
    return this.productCategoryCode;
  }

  /**
   * 分类id.
   */
  public void setProductCategoryId(Long productCategoryId) {
    this.productCategoryId = productCategoryId;
  }

  /**
   * 分类id.
   */
  public Long getProductCategoryId() {
    return this.productCategoryId;
  }

  /**
   * 分类名称.
   */
  public void setProductCategoryName(String productCategoryName) {
    this.productCategoryName = productCategoryName == null ? null : productCategoryName.trim();
  }

  /**
   * 分类名称.
   */
  public String getProductCategoryName() {
    return this.productCategoryName;
  }
}