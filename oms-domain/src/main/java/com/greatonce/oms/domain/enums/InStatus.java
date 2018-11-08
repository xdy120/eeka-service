package com.greatonce.oms.domain.enums;

import com.greatonce.core.ValueEnum;

/**
 * InStatus
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/26
 */
public enum InStatus implements ValueEnum {
  NO_IN("未入库", 1),
  PART_IN("部分入库", 2),
  ALL_IN("全部入库", 3);

  private final String caption;
  private final int value;

  InStatus(String caption, int value) {
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
