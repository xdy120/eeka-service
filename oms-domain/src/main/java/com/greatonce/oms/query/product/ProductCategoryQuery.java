package com.greatonce.oms.query.product;

import com.greatonce.core.database.Query;
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
public class ProductCategoryQuery extends Query {
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
   * 分类编码.
   * @param productCategoryCode 分类编码
   */
  public void setProductCategoryCode(String productCategoryCode) {
    this.productCategoryCode = productCategoryCode == null ? null : productCategoryCode.trim();
  }

  /**
   * 分类编码.
   * @return 分类编码
   */
  public String getProductCategoryCode() {
      return this.productCategoryCode;
  }

  /**
   * 分类id.
   * @param productCategoryId 分类id
   */
  public void setProductCategoryId(Long productCategoryId) {
    this.productCategoryId = productCategoryId;
  }

  /**
   * 分类id.
   * @return 分类id
   */
  public Long getProductCategoryId() {
      return this.productCategoryId;
  }

  /**
   * 分类名称.
   * @param productCategoryName 分类名称
   */
  public void setProductCategoryName(String productCategoryName) {
    this.productCategoryName = productCategoryName == null ? null : productCategoryName.trim();
  }

  /**
   * 分类名称.
   * @return 分类名称
   */
  public String getProductCategoryName() {
      return this.productCategoryName;
  }
}