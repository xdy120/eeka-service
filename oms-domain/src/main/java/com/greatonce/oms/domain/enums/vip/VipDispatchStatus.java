package com.greatonce.oms.domain.enums.vip;

import com.greatonce.core.ValueEnum;

/**
 * 唯品配货单状态
 *
 * @author Coby
 * @version 2017-12-17
 */
public enum VipDispatchStatus implements ValueEnum {

  CREATED(1, "新建"),
  AUDITED(2, "已审核"),
  BIND(3, "已关联出仓单"),
  NOTICED(4, "已通知"),
  NOTICE_FAILED(5, "通知失败"),
  CANCELED(6, "已取消"),
  FINISH(7, "已完结"),
  ABNORMAL(8, "异常");


  public final int value;
  public final String caption;

  VipDispatchStatus(final int value, final String caption) {
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
