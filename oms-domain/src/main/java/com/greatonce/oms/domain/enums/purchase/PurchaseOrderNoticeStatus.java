package com.greatonce.oms.domain.enums.purchase;

import com.greatonce.core.ValueEnum;

/**
 * PurchaseStatus
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/26
 */
public enum PurchaseOrderNoticeStatus implements ValueEnum {
  NO_NOTICE("未通知", 1),
  PART_NOTICE("部分通知", 2),
  ALL_NOTICE("全部通知", 3);

  private final String caption;
  private final int value;

  PurchaseOrderNoticeStatus(String caption, int value) {
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
