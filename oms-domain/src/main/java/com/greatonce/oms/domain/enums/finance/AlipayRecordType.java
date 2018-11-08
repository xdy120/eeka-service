package com.greatonce.oms.domain.enums.finance;

import com.greatonce.core.ValueEnum;

public enum AlipayRecordType implements ValueEnum {

  PAYMENT("在线支付", 1),
  TRANSFER("转账", 2),
  DEPOSIT("充值", 3),
  WITHDRAW("提现", 4),
  CHARGE("收费", 5),
  PREAUTH("预授权", 6),
  OTHER("其它", 7);

  private final String caption;
  private final int value;

  AlipayRecordType(String caption, int value) {
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
