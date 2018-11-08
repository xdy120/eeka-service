package com.greatonce.oms.domain.enums.mall;

import com.greatonce.core.ValueEnum;

/**
 * MallSalesOrderDetailStatus
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/26
 */
public enum MallSalesOrderDetailStatus implements ValueEnum {
  UNKNOWN("未知", 0),
  NEW_TRADE("新建", 10),
  WAIT_BUYER_PAY("等待买家支付", 20),
  WAIT_SELLER_SEND_GOODS("等待卖家发货", 30),
  WAIT_BUYER_CONFIRM_GOODS("等待买家确认收货", 40),
  TRADE_FINISHED("交易完成", 50),
  TRADE_CLOSE("交易关闭", 60);
  private final String caption;
  private final int value;

  MallSalesOrderDetailStatus(String caption, int value) {
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
