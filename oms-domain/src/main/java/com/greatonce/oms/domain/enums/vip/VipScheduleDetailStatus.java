package com.greatonce.oms.domain.enums.vip;

import com.greatonce.core.ValueEnum;

/**
 * 唯品档期状态
 *
 * @author Coby
 * @version 2017-12-14
 */
public enum VipScheduleDetailStatus implements ValueEnum {

  NO_UPLOAD(1, "未上传"),
  UPLOADED(2, "已上传"),
  FINISHED(3, "已结束");

  public final int value;
  public final String caption;

  VipScheduleDetailStatus(final int value, final String caption) {
    this.value = value;
    this.caption = caption;
  }

  @Override
  public String caption() {
    return caption;
  }

  @Override
  public int value() {
    return value;
  }

}
