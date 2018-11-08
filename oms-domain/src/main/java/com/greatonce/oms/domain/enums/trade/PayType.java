package com.greatonce.oms.domain.enums.trade;

import com.greatonce.core.ValueEnum;

/**
 * @author buer
 * @version 2017-12-08 10:14
 */
public enum PayType implements ValueEnum {
  /**
   * 其他
   */
  OTHER("其他", 0),
  /**
   * 支付宝
   */
  ALI_PAY("支付宝", 1),
  /**
   * 银联
   */
  UNION_PAY("银联", 2),
  /**
   * 邮局汇款
   */
  POSTAL("邮局汇款", 3),
  /**
   * 财付通
   */
  TEN_PAY("财付通", 4),
  /**
   * 块钱
   */
  KUAI_QIAN("块钱", 5),
  /**
   * 现金
   */
  CASH("现金", 6),
  /**
   * 礼品卡
   */
  GIFT_CARD("礼品卡", 7),
  /**
   * 余额支付
   */
  BALANCE("余额支付", 8),
  /**
   * 货到付款
   */
  COD_PAY("货到付款", 9),
  /**
   * 贝宝
   */
  PAY_PAL("贝宝", 10),
  /**
   * 微信支付
   */
  WEIXIN_PAY("微信支付", 11),
  /**
   * 优惠券
   */
  COUPON("优惠券", 12),
  /**
   * 红包
   */
  RED_PACKET("红包", 13),
  /**
   * 京豆
   */
  JINGDOU("京豆", 14),
  /**
   * 积分
   */
  INTEGRAL("积分支付", 15),
  /**
   * 在线支付
   */
  ONLINE_PAY("在线支付", 16);

  private final String caption;
  private final int value;

  PayType(String caption, int value) {
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
