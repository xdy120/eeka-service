package com.greatonce.oms.domain.enums;

import com.greatonce.core.ValueEnum;

/**
 * 短信模板类型
 *
 * @author Coby
 * @version 2017-11-16
 */
public enum SmsTemplateType implements ValueEnum {

  PRE_SALES_ORDER_NOTIFY(1, "预售订单通知"),
  SALES_ORDER_SHIP_NOTIFY(2, "销售订单发货通知"),
  EXCHANGE_ORDER_SHIP_NOTIFY(3, "换货订单发货通知"),
  AGREE_RETURN_NOTIFY(4, "同意退货通知"),
  RETURN_STORAGE_NOTIFY(5, "退货入库通知"),
  REFUND_SUCCESS_NOTIFY(6, "退款成功通知"),
  ELECTRONIC_INVOICE_NOTIFY(7, "电子发票通知"),
  MARKETING_ACTIVITIES(8, "营销活动"),
  ORDER_CUSTOMIZE(9, "订单自定义信息"),;

  public final String caption;
  public final int value;

  private SmsTemplateType(final int value, final String caption) {
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
