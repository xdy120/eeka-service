package com.greatonce.oms.domain.enums.mall;

import com.greatonce.core.ValueEnum;

/**
 * MallExchangeStatus
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/26
 */
public enum MallExchangeStatus implements ValueEnum {
  WAIT_DEAL("换货待处理", 1),
  WAIT_BUYER_RETURN("待买家退货", 2),
  BUYER_RETURNED("买家已退货，待收货", 3),
  EXCHANGE_CLOSE("换货关闭", 4),
  EXCHANGE_SUCCESS("换货成功", 5),
  WAIT_BUYER_MODIFY("待买家修改", 6),
  WAIT_SEND_EXCHANGE_GOODS("待发出换货商品", 12),
  WAIT_BUYER_RECEIVE("待买家收货", 13),
  REFUND("请退款", 14);

  private final String caption;
  private final int value;

  MallExchangeStatus(String caption, int value) {
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
