package com.greatonce.oms.domain.enums;

import com.greatonce.core.ValueEnum;

/**
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/10/15
 */
public enum PostStatus implements ValueEnum {

  UN_POST("未过帐", 1),
  POSTED("已过帐", 2);

  private final String caption;
  private final int value;

  PostStatus(String caption, int value) {
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
