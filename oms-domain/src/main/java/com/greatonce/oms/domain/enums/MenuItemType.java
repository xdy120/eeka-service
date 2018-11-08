package com.greatonce.oms.domain.enums;

import com.greatonce.core.ValueEnum;

/**
 * @author buer
 * @version 2018-01-22 9:21
 */
public enum MenuItemType implements ValueEnum {
  MODULE("模块", 1),
  MENU("菜单", 2),
  MENU_ITEM("菜单项", 3),
  OPERATOR("操作", 4);
  private final String caption;
  private final int value;

  MenuItemType(String caption, int value) {
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
