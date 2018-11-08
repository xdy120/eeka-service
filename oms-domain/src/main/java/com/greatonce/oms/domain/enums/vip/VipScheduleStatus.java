package com.greatonce.oms.domain.enums.vip;

import com.greatonce.core.ValueEnum;

/**
 * 唯品档期状态
 *
 * @author Coby
 * @version 2017-12-14
 */
public enum VipScheduleStatus implements ValueEnum {

  CREATED(1, "新建"),
  AUDITED(2, "已审核"),
  UPLOADED(3, "已上传"),
  FINISHED(4, "已结束");

  public final int value;
  public final String caption;

  VipScheduleStatus(final int value, final String caption) {
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
