package com.greatonce.oms.custom.eeka.consumer;

/**
 * 赢家常量.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/20
 */
public class EekaConstants {

  /**
   * FMS发货单通知队列.
   */
  public static final String QUEUE_DELIVERY_ORDER_NOTICE = "oms.eeka.fms.dispatch.order.notice";
  /**
   * FMS发货单通知队列绑定KEY.
   */
  public static final String QUEUE_DELIVERY_ORDER_NOTICE_BINDING_KEY = "oms.trade.dispatch.order.delivery";
  public static final String QUEUE_DELIVERY_ORDER_NOTICE_REPOSTING_BINDING_KEY = "oms.trade.dispatch.order.reposting";
  /**
   * FMS退货单通知队列.
   */
  public static final String QUEUE_RETURN_ORDER_NOTICE = "oms.eeka.fms.return.order.notice";
  /**
   * FMS退货单通知队列绑定KEY.
   */
  public static final String QUEUE_RETURN_ORDER_NOTICE_BINDING_KEY = "oms.trade.return.notice.order.entry";
  public static final String QUEUE_RETURN_ORDER_REVIEW_BINDING_KEY = "oms.trade.return.notice.order.review";
  public static final String QUEUE_RETURN_ORDER_NOTICE_REPOSTING_BINDING_KEY = "oms.trade.return.notice.order.reposting";
  /**
   * FMS唯品发货单通知队列.
   */
  public static final String QUEUE_VIP_DISPATCH_ORDER_DELIVERY_NOTICE = "oms.eeka.fms.vip.dispatch.notice";
  /**
   * FMS唯品发货单通知队列绑定KEY.
   */
  public static final String QUEUE_VIP_DISPATCH_ORDER_DELIVERY_BINDING_KEY = "oms.vip.dispatch.delivery";
  public static final String QUEUE_VIP_DISPATCH_ORDER_DELIVERY_REPOSTING_BINDING_KEY = "oms.vip.dispatch.reposting";
  /**
   * FMS唯品退货单通知队列.
   */
  public static final String QUEUE_VIP_RETURN_ORDER_NOTICE = "oms.eeka.fms.vip.return.notice";
  /**
   * FMS唯品退货单通知队列绑定KEY.
   */
  public static final String QUEUE_VIP_RETURN_ORDER_IN_BINDING_KEY = "oms.vip.return.notice.in";
  public static final String QUEUE_VIP_RETURN_ORDER_IN_REPOSTING_BINDING_KEY = "oms.vip.return.notice.reposting";
}
