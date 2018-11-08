package com.greatonce.oms.domain.admin;

import com.greatonce.oms.domain.BaseDO;
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
public class MenuOperation extends BaseDO {
  /**
   * 链路id.
   */
  private String cid;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
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
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
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
   * 子菜单项.
   */
  private List<MenuOperation> children;

  @Override
  public void setPrimaryKey(Long pk) {
    this.itemId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.itemId;
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
   * 图标.
   */
  public void setIcon(String icon) {
    this.icon = icon == null ? null : icon.trim();
  }

  /**
   * 图标.
   */
  public String getIcon() {
    return this.icon;
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
   * 菜单项id.
   */
  public void setItemId(Long itemId) {
    this.itemId = itemId;
  }

  /**
   * 菜单项id.
   */
  public Long getItemId() {
    return this.itemId;
  }

  /**
   * 名称.
   */
  public void setItemName(String itemName) {
    this.itemName = itemName == null ? null : itemName.trim();
  }

  /**
   * 名称.
   */
  public String getItemName() {
    return this.itemName;
  }

  /**
   * 类型.
   */
  public void setItemType(MenuItemType itemType) {
    this.itemType = itemType;
  }

  /**
   * 类型.
   */
  public MenuItemType getItemType() {
    return this.itemType;
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
   * 排序.
   */
  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  /**
   * 排序.
   */
  public Integer getOrderId() {
    return this.orderId;
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
   * 路由.
   */
  public void setRouter(String router) {
    this.router = router == null ? null : router.trim();
  }

  /**
   * 路由.
   */
  public String getRouter() {
    return this.router;
  }

  /**
   * 子菜单项.
   */
  public void setChildren(List<MenuOperation> children) {
    this.children = children;
  }

  /**
   * 子菜单项.
   */
  public List<MenuOperation> getChildren() {
    return this.children;
  }
}