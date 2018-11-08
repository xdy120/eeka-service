package com.greatonce.oms.domain.enums;

import com.greatonce.core.ValueEnum;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 6/12/2018
 */
public enum WmsNoticeStatus implements ValueEnum {
  NONE("未通知", 1),
  PART("部分通知", 2),
  ALL("全部通知", 3);
  private final String caption;
  private final int value;

  WmsNoticeStatus(String caption, int value) {
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
