package com.greatonce.oms.query.admin;

import com.greatonce.core.database.Query;
import com.greatonce.oms.domain.enums.MenuItemType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 菜单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class MenuOperationQuery extends Query {
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
   * 图标.
   */
  private String icon;
  /**
   * 是否系统.
   */
  private Boolean system;
  /**
   * 菜单项id.
   */
  private Long itemId;
  /**
   * 名称.
   */
  private String itemName;
  /**
   * 类型.
   */
  private MenuItemType itemType;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
  /**
   * 排序.
   */
  private Integer orderId;
  /**
   * 父id.
   */
  private Long parentId;
  /**
   * 路由.
   */
  private String router;


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
   * 图标.
   * @param icon 图标
   */
  public void setIcon(String icon) {
    this.icon = icon == null ? null : icon.trim();
  }

  /**
   * 图标.
   * @return 图标
   */
  public String getIcon() {
      return this.icon;
  }

  /**
   * 是否系统.
   * @param system 是否系统
   */
  public void setSystem(Boolean system) {
    this.system = system;
  }

  /**
   * 是否系统.
   * @return 是否系统
   */
  public Boolean isSystem() {
      return this.system;
  }

  /**
   * 菜单项id.
   * @param itemId 菜单项id
   */
  public void setItemId(Long itemId) {
    this.itemId = itemId;
  }

  /**
   * 菜单项id.
   * @return 菜单项id
   */
  public Long getItemId() {
      return this.itemId;
  }

  /**
   * 名称.
   * @param itemName 名称
   */
  public void setItemName(String itemName) {
    this.itemName = itemName == null ? null : itemName.trim();
  }

  /**
   * 名称.
   * @return 名称
   */
  public String getItemName() {
      return this.itemName;
  }

  /**
   * 类型.
   * @param itemType 类型
   */
  public void setItemType(MenuItemType itemType) {
    this.itemType = itemType;
  }

  /**
   * 类型.
   * @return 类型
   */
  public MenuItemType getItemType() {
      return this.itemType;
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
   * 排序.
   * @param orderId 排序
   */
  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  /**
   * 排序.
   * @return 排序
   */
  public Integer getOrderId() {
      return this.orderId;
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
   * 路由.
   * @param router 路由
   */
  public void setRouter(String router) {
    this.router = router == null ? null : router.trim();
  }

  /**
   * 路由.
   * @return 路由
   */
  public String getRouter() {
      return this.router;
  }
}