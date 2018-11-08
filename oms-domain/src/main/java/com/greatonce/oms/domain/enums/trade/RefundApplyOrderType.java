package com.greatonce.oms.domain.enums.trade;

import com.greatonce.core.ValueEnum;

/**
 * RefundApplyOrderType
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/5
 */
public enum RefundApplyOrderType implements ValueEnum {
  /**
   * 仅退款
   */
  REFUND("仅退款", 1),
  /**
   * 退货退款
   */
  RETURN_REFUND("退货退款", 2);
  private final String caption;
  private final int value;

  RefundApplyOrderType(String caption, int value) {
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
