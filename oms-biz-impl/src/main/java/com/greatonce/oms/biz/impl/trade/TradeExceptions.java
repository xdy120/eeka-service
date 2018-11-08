package com.greatonce.oms.biz.impl.trade;

import com.greatonce.oms.domain.OmsException;

/**
 * @author buer
 * @version 2017-12-23 11:12
 */
public final class TradeExceptions {

  /**
   * 订单异常编码
   */
  public static Integer SALES_ORDER_ERROR_CODE = 100001;
  public static OmsException SALES_ORDER_NOT_ALLOW_ACTION = new OmsException(10000, "订单不允许此操作！");
  public static OmsException SALES_ORDER_RECV_INFO_ABNORMAL = new OmsException(10000, "联系方式异常！");
  public static OmsException SALES_ORDER_MONEY_ABNORMAL = new OmsException(10000, "订单金额异常！");
  public static OmsException SALES_ORDER_DETAIL_EMPTY = new OmsException(10000, "订单明细为空！");
  public static OmsException SALES_ORDER_DETAIL_ABNORMAL = new OmsException(10000, "订单商品匹配异常！");
  public static OmsException SALES_ORDER_DISPATCHED = new OmsException(10000, "订单已配货！");
  public static OmsException SALES_ORDER_DELIVERED = new OmsException(10000, "订单已发货！");
  public static OmsException SALES_ORDER_DETAIL_INVALID = new OmsException(10000, "订单商品已作废！");

  /**
   * 配货单
   */
  public static Integer DISPATCH_ORDER_ERROR_CODE = 100001;
  public static OmsException DISPATCH_ORDER_NOT_ALLOW_ACTION = new OmsException(10000,
      "配货单不允许此操作！");
  public static OmsException DISPATCH_ORDER_OUTBOUND = new OmsException(10000, "配货单已出库！");
}
