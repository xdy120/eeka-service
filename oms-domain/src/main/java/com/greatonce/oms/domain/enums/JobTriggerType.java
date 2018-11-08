package com.greatonce.oms.domain.enums;

import com.greatonce.core.ValueEnum;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/21
 */
public enum JobTriggerType implements ValueEnum {
  SIMPLE("简单触发器", 1),
  CORN("CORN触发器", 2);
  private final String caption;
  private final int value;

  JobTriggerType(String caption, int value) {
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
