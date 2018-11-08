package com.greatonce.oms.domain.enums.trade;

import com.greatonce.core.ValueEnum;

/**
 * @author buer
 * @version 2017-12-08 10:14
 */
public enum InvoiceType implements ValueEnum {
  NORMAL("普通发票", 1),
  VALUE_ADD("增值税专用发票", 2);
  private final String caption;
  private final int value;

  InvoiceType(String caption, int value) {
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
