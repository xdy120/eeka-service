package com.greatonce.oms.domain.enums;

import com.greatonce.core.ValueEnum;

/**
 * 电子面单类型.
 *
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2018/8/14
 */
public enum WaybillType implements ValueEnum {
  NONE("非电子面单快递", 1),
  JD("京东面单", 2),
  CAINIAO("菜鸟面单", 3);

  private final String caption;
  private final int value;


  WaybillType(String caption, int value) {
    this.caption = caption;
    this.value = value;
  }

  @Override
  public int value() {
    return value;
  }

  @Override
  public String caption() {
    return caption;
  }
}
