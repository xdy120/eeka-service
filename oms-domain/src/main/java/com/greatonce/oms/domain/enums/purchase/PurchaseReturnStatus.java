package com.greatonce.oms.domain.enums.purchase;

import com.greatonce.core.ValueEnum;

/**
 * PurchaseReturnStatus
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/26
 */
public enum PurchaseReturnStatus implements ValueEnum {
  CREATED("新建", 1),
  AUDITED("已审核", 2),
  NOTICED("已通知", 3),
  FINISH("已完结", 4),
  CANCELED("已取消", 5),
  NOTICE_FAILED("推送失败", 6);
  private final String caption;
  private final int value;

  PurchaseReturnStatus(String caption, int value) {
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

