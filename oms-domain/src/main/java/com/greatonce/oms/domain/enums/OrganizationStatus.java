package com.greatonce.oms.domain.enums;

import com.greatonce.core.ValueEnum;

/**
 * @author buer
 * @version 2018-01-18 11:17
 */
public enum OrganizationStatus implements ValueEnum {
  PILOT_RUN("试运行", 1),
  ONLINE("上线", 2),
  OFFLINE("下线", 3);
  private final String caption;
  private final int value;

  OrganizationStatus(String caption, int value) {
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
