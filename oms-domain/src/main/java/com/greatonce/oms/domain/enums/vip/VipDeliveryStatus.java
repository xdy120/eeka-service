package com.greatonce.oms.domain.enums.vip;

import com.greatonce.core.ValueEnum;

/**
 * 唯品唯品发货单状态
 *
 * @author Coby
 * @version 2017-12-18
 */
public enum VipDeliveryStatus implements ValueEnum {

  CREATED(1, "新建"),
  AUDITED(2, "已审核"),
  DELIVERED(3, "已发货"),
  INVALID(4, "已作废"),
  CANCELED(5, "已取消"),;

  public final int value;
  public final String caption;

  VipDeliveryStatus(final int value, final String caption) {
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
