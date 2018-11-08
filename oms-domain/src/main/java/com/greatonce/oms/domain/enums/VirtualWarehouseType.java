package com.greatonce.oms.domain.enums;

import com.greatonce.core.ValueEnum;

/**
 * @author buer
 * @version 2017-12-13 11:26
 */
public enum VirtualWarehouseType implements ValueEnum {
  SHARE("共享仓", 1),
  EXCLUSIVE("独占仓", 2),
  SUBSTANDARD("次品仓", 3);
  private final String caption;
  private final int value;

  VirtualWarehouseType(String caption, int value) {
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
