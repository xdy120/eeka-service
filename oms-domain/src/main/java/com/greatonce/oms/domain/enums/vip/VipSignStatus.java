package com.greatonce.oms.domain.enums.vip;

import com.greatonce.core.ValueEnum;

/**
 * 唯品签收类型
 *
 * @author Coby
 * @version 2017-12-20
 */
public enum VipSignStatus implements ValueEnum {

  NO_SIGN(1, "未签收"),
  NORMAL(2, "正常签收"),
  ABNORMAL(3, "异常签收");

  public final int value;
  public final String caption;

  VipSignStatus(final int value, final String caption) {
    this.value = value;
    this.caption = caption;
  }

  @Override
  public String caption() {
    return caption;
  }

  @Override
  public int value() {
    return value;
  }

}
