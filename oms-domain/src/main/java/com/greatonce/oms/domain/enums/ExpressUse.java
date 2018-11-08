package com.greatonce.oms.domain.enums;

import com.greatonce.core.ValueEnum;

/**
 * ExpressUse
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/26
 */
public enum ExpressUse implements ValueEnum {
  PRE_SALE("售前快递", 1),
  AFTER_SALE("售后快递", 2),
  ALL("售前+售后", 3);
  private final String caption;
  private final int value;

  ExpressUse(String caption, int value) {
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
