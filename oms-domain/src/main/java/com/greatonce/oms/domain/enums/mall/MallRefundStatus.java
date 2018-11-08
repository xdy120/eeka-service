package com.greatonce.oms.domain.enums.mall;

import com.greatonce.core.ValueEnum;

/**
 * MallRefundStatus
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/26
 */
public enum MallRefundStatus implements ValueEnum {
  UNKNOWN("未知", 0),
  NO_REFUND("未退款", 1),
  WAIT_SELLER_AGREE("等待卖家同意", 2),
  WAIT_BUYER_RETURN_GOODS("等待买家退货", 3),
  WAIT_SELLER_CONFIRM_GOODS("等待卖家确认收货", 4),
  SELLER_REFUSE_BUYER("卖家拒绝退款", 5),
  CLOSED("退款关闭", 6),
  SUCCESS("退款成功", 7);
  private final String caption;
  private final int value;

  MallRefundStatus(String caption, int value) {
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
