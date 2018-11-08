package com.greatonce.oms.domain.enums.purchase;

import com.greatonce.core.ValueEnum;

/**
 * PurchaseStatus
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/26
 */
public enum PurchaseStatus implements ValueEnum {
  CREATED("新建", 1),
  AUDITED("已审核", 2),
  INVALID("已作废", 3),
  FINISH("已完结", 4);


  private final String caption;
  private final int value;

  PurchaseStatus(String caption, int value) {
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
