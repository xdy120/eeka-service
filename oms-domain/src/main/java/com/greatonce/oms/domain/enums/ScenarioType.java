package com.greatonce.oms.domain.enums;

import com.greatonce.core.ValueEnum;

/**
 * ScenarioType
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/26
 */
public enum ScenarioType implements ValueEnum {

  None("通用", 1),
  PRESALE("售前", 2),
  AFTERSALE("售后", 3);

  private final String caption;
  private final int value;

  ScenarioType(String caption, int value) {
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