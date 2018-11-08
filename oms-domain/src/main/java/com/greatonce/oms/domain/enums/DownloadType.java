package com.greatonce.oms.domain.enums;

import com.greatonce.core.ValueEnum;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/16
 */
public enum DownloadType implements ValueEnum {
  SALES_ORDER("销售订单", 10),
  REFUND_ORDER("售后申请", 11),
  EXCHANGE_ORDER("换货申请", 12),
  ALIPAY_RECORD("支付宝账单", 13),

  VIP_ORDER("唯品销售订单", 20),
  VIP_CANCEL_ORDER("唯品取消单", 21),
  VIP_PICK_ORDER("唯品拣货单", 22),
  VIP_RETURN_ORDER("唯品退供单", 23);
  private final String caption;
  private final int value;

  DownloadType(String caption, int value) {
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
