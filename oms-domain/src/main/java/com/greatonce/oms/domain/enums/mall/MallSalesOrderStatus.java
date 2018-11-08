package com.greatonce.oms.domain.enums.mall;

import com.greatonce.core.ValueEnum;

/**
 * MallSalesOrderStatus
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/26
 */
public enum MallSalesOrderStatus implements ValueEnum {
  UNKNOWN("未知", 0),
  TRADE_NO_CREATE_PAY("没有创建支付宝交易", 1),
  WAIT_BUYER_PAY("等待买家支付", 10),
  WAIT_SELLER_SEND_GOODS("等待卖家发货", 20),
  WAIT_BUYER_CONFIRM_GOODS("等待买家确认收货", 30),
  TRADE_BUYER_SIGNED("买家已签收", 40),
  TRADE_FINISHED("交易完成", 51),
  TRADE_CLOSE("交易关闭", 52),
  TRADE_CLOSED_BY_TAOBAO("交易被淘宝关闭", 53),
  REFUND_FINISHED("退款完成", 60);
  private final String caption;
  private final int value;

  MallSalesOrderStatus(String caption, int value) {
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
