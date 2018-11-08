package com.greatonce.oms.domain.enums.purchase;

import com.greatonce.core.ValueEnum;

/**
 * PurchaseNoticeStatus
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/26
 */
public enum PurchaseNoticeStatus implements ValueEnum {
  CREATED("新建", 1),
  NOTICED("已通知", 2),
  NOTICE_FAILED("推送失败", 3),
  CANCEL("已取消", 4);

  private final String caption;
  private final int value;

  PurchaseNoticeStatus(String caption, int value) {
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
