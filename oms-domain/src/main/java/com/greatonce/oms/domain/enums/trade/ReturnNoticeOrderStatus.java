package com.greatonce.oms.domain.enums.trade;

import com.greatonce.core.ValueEnum;

/**
 * ReturnNoticeOrderStatus
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/26
 */
public enum ReturnNoticeOrderStatus implements ValueEnum {
  CREATED("新建", 1),
  NOTICED("已通知", 2),
  NOTICE_FAILED("推送失败", 3),
  CANCELED("已取消", 4);

  private final String caption;
  private final int value;

  ReturnNoticeOrderStatus(String caption, int value) {
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
