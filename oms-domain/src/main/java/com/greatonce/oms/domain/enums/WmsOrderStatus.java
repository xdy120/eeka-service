package com.greatonce.oms.domain.enums;

import com.greatonce.core.ValueEnum;

/**
 * 配货单仓库状态.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/6/6
 */
public enum WmsOrderStatus implements ValueEnum {
  /**
   * 仓库接单.
   */
  ACCEPT("仓库接单", 1),
  /**
   * 部分收货完成.
   */
  PARTFULFILLED("部分收货完成", 2),
  /**
   * 收货完成.
   */
  FULFILLED("收货完成", 3),
  /**
   * 打印.
   */
  PRINT("打印", 4),
  /**
   * 捡货.
   */
  PICK("捡货", 5),
  /**
   * 复核.
   */
  CHECK("复核", 6),
  /**
   * 打包.
   */
  PACKAGE("打包", 7),
  /**
   * 称重.
   */
  WEIGH("称重", 8),
  /**
   * 待提货.
   */
  READY("待提货", 9),
  /**
   * 已发货.
   */
  DELIVERED("已发货", 10),
  /**
   * 买家拒签.
   */
  REFUSE("买家拒签", 11),
  /**
   * 异常.
   */
  EXCEPTION("异常", 12),
  /**
   * 关闭.
   */
  CLOSED("关闭", 13),
  /**
   * 取消.
   */
  CANCELED("取消", 14),
  /**
   * 签收.
   */
  SIGN("签收", 15),
  /**
   * 快递拦截.
   */
  TMSCANCELED("快递拦截", 16),
  /**
   * 其他.
   */
  OTHER("其他", 17),
  /**
   * 部分发货完成.
   */
  PARTDELIVERED("部分发货完成", 18),
  /**
   * 快递拦截失败.
   */
  TMSCANCELFAILED("快递拦截失败", 19);

  private final String caption;
  private final int value;

  WmsOrderStatus(String caption, int value) {
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
