package com.greatonce.oms.domain.enums.marketing;

import com.greatonce.core.ValueEnum;

/**
 * 会员状态
 *
 * @author Coby
 * @version 2017-11-21
 */
public enum MemberStatus implements ValueEnum {

  NORMAL(1, "正常"),
  BLACK_LIST(2, "黑名单"),;

  public final int value;
  public final String caption;

  MemberStatus(final int value, final String caption) {
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
