package com.greatonce.oms.domain.enums.mall;

import com.greatonce.core.ValueEnum;

/**
 *
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2018/8/14
 */
public enum JdWaybillProviderType implements ValueEnum {
  JOIN("加盟型快递公司", 1),
  DIRECT("直营型快递公司", 2);

  private final String caption;
  private final int value;

  JdWaybillProviderType(String caption, int value) {
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
