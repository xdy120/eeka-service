package com.greatonce.oms.domain.enums;

import com.greatonce.core.ValueEnum;

/**
 * ExpressType
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/26
 */
public enum ExpressType implements ValueEnum {
  EXPRESS("快递", 1),
  LOGISTICS("物流", 2);
  private final String caption;
  private final int value;

  ExpressType(String caption, int value) {
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
