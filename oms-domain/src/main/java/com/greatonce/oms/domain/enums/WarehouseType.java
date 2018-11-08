package com.greatonce.oms.domain.enums;

import com.greatonce.core.ValueEnum;

/**
 * @author creoa
 * @version 2017-10-30 10:02
 */
public enum WarehouseType implements ValueEnum {
  OWN("电商仓", 1),
  PLATFORM("平台托管仓", 2),
  AREA("直营区域仓", 3),
  SHOP("直营门店仓", 4),
  LEAGUE_SHOP("外埠门店仓", 5);
  private final String caption;
  private final int value;

  WarehouseType(String caption, int value) {
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
