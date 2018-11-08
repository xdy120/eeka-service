package com.greatonce.oms.domain.enums.vip;

import com.greatonce.core.ValueEnum;

/**
 * 唯品退供单状态
 *
 * @author Coby
 * @version 2017-12-20
 */
public enum VipReturnStatus implements ValueEnum {
  CREATED(1, "新建"),
  AUDITED(2, "已审核"),
  INVALID(3, "已作废");

  public final int value;
  public final String caption;

  VipReturnStatus(final int value, final String caption) {
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
