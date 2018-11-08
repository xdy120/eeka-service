package com.greatonce.oms.domain.enums;

import com.greatonce.core.ValueEnum;

/**
 * @author buer
 * @version 2017-12-22 17:19
 */
public enum JingdongMode implements ValueEnum {
  FBP("FBP", 1),
  SOP("SOP", 3),
  N360("N360", 5),
  NTP("NTP", 6);
  private final String caption;
  private final int value;

  JingdongMode(String caption, int value) {
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
