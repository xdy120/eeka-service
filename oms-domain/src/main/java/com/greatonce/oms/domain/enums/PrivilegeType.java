package com.greatonce.oms.domain.enums;

import com.greatonce.core.ValueEnum;

/**
 * @author buer
 * @version 2017-12-04 13:52
 */
public enum PrivilegeType implements ValueEnum {
  MODULE("模块", 1),
  MENU("菜单", 2),
  MENU_ITEM("菜单项", 3),
  OPERATOR("操作", 4),
  STORE("店铺", 5),
  WAREHOUSE("仓库", 6),
  SUPPLIER("字段", 7),
  FIELD("字段", 8);
  private final String caption;
  private final int value;

  PrivilegeType(String caption, int value) {
    this.caption = caption;
    this.value = value;
  }

  @Override
  public int value() {
    return this.value;
  }

  @Override
  public String caption() {
    return this.caption;
  }
}
