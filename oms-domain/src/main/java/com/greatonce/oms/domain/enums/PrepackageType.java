package com.greatonce.oms.domain.enums;

import com.greatonce.core.ValueEnum;

/**
 * 预包装类型.
 *
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2018/7/9
 */
public enum PrepackageType implements ValueEnum {

  ALL("不限制",1),
  SINGLE("只发预包装单品", 2),
  COMBINATION("只发预包装套装",3),
  ALL_PREPACKAGE("支持所有预包装",4);

  private final String caption;
  private final int value;

  PrepackageType(final String caption, final int value) {
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
