package com.greatonce.oms.bridge.mall.impl.jd;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.domain.enums.mall.MallSalesOrderDetailStatus;
import com.greatonce.oms.domain.enums.mall.MallSalesOrderStatus;

/**
 * 京东转换器.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-08-22
 */
public abstract class JdConverter {

  /**
   * 转化OMS订单状态.
   */
  public static MallSalesOrderStatus toOmsOrderStatus(String status) {
    if (!Assert.isEmpty(status)) {
      switch (status) {
        case "WAIT_SELLER_STOCK_OUT":
          return MallSalesOrderStatus.WAIT_SELLER_SEND_GOODS;
        case "WAIT_GOODS_RECEIVE_CONFIRM":
          return MallSalesOrderStatus.WAIT_BUYER_CONFIRM_GOODS;
        case "WAIT_SELLER_DELIVERY":
          return MallSalesOrderStatus.WAIT_SELLER_SEND_GOODS;
        case "TRADE_CANCELED":
          return MallSalesOrderStatus.TRADE_CLOSE;
        case "FINISHED_L":
          return MallSalesOrderStatus.TRADE_FINISHED;
        default:
          return MallSalesOrderStatus.UNKNOWN;
      }
    }
    return MallSalesOrderStatus.UNKNOWN;
  }

  public static MallSalesOrderDetailStatus toOmsOrderDetailStatus(String status){
    if (!Assert.isEmpty(status)){
      switch (status){
        case "WAIT_SELLER_STOCK_OUT":
        case "WAIT_SELLER_DELIVERY":
          return MallSalesOrderDetailStatus.WAIT_SELLER_SEND_GOODS;
        case "WAIT_GOODS_RECEIVE_CONFIRM":
          return MallSalesOrderDetailStatus.WAIT_BUYER_CONFIRM_GOODS;
        case "TRADE_CANCELED":
          return MallSalesOrderDetailStatus.TRADE_CLOSE;
        case "FINISHED_L":
          return MallSalesOrderDetailStatus.TRADE_FINISHED;
        default:
          return MallSalesOrderDetailStatus.UNKNOWN;
      }
    }
    return MallSalesOrderDetailStatus.UNKNOWN;
  }

  /**
   * 转换京东订单状态.
   */
  public static String toJdOrderStatus(MallSalesOrderStatus status) {
    switch (status) {
      case WAIT_SELLER_SEND_GOODS:
        return "WAIT_SELLER_STOCK_OUT";
      case TRADE_FINISHED:
        return "FINISHED_L";
      default:
        return "WAIT_SELLER_STOCK_OUT";
    }
  }
}
