package com.greatonce.oms.domain.enums;

import com.greatonce.core.ValueEnum;

/**
 * WaybillStrategy
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/26
 */
public enum WaybillStrategy implements ValueEnum {
  OMS("OMS获取", 1),
  WMS("WMS获取", 2);
  private final String caption;
  private final int value;

  WaybillStrategy(String caption, int value) {
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
