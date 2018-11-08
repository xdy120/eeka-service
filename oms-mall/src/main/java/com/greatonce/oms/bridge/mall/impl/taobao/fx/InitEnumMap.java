package com.greatonce.oms.bridge.mall.impl.taobao.fx;

import com.greatonce.oms.domain.enums.mall.MallRefundStatus;
import com.greatonce.oms.domain.enums.mall.MallSalesOrderDetailStatus;
import com.greatonce.oms.domain.enums.mall.MallSalesOrderStatus;
import com.greatonce.oms.domain.enums.product.MallProductStatus;
import com.greatonce.oms.domain.enums.trade.PayType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/7/9
 */
public class InitEnumMap {



  static final Map<MallSalesOrderStatus,String> MALL_STATUS_MAP= new HashMap<>(7);
  static final Map<String,PayType> OMS_PAY_TYPE = new HashMap<>(3);
  static final Map<String,MallSalesOrderStatus> ORDER_STATUS_MAP = new HashMap<>(8);
  static final Map<String,MallSalesOrderDetailStatus> MALL_SALES_ORDER_DETAIL_STATUS_MAP = new HashMap<>(9);

  static final Map<MallProductStatus,String> MALL_PRODUCT_STATUS_STRING_MAP_STRING = new HashMap<>(2);
  static final Map<String,MallProductStatus> MALL_PRODUCT_STATUS_STRING_MAP_MENU = new HashMap<>(2);


  static final Map<Integer,MallRefundStatus> MALL_REFUND_STATUS_MAP = new HashMap<>(9);


  static {
    MALL_STATUS_MAP.put(MallSalesOrderStatus.WAIT_BUYER_PAY,"WAIT_BUYER_PAY");
    MALL_STATUS_MAP.put(MallSalesOrderStatus.WAIT_SELLER_SEND_GOODS,"WAIT_SELLER_SEND_GOODS");
    MALL_STATUS_MAP.put(MallSalesOrderStatus.WAIT_BUYER_CONFIRM_GOODS,"WAIT_BUYER_CONFIRM_GOODS");
    MALL_STATUS_MAP.put(MallSalesOrderStatus.TRADE_FINISHED,"TRADE_FINISHED");
    MALL_STATUS_MAP.put(MallSalesOrderStatus.TRADE_CLOSE,"TRADE_CLOSED");
    MALL_STATUS_MAP.put(MallSalesOrderStatus.WAIT_BUYER_PAY,"");
//  default:  WAIT_SELLER_SEND_GOODS

    OMS_PAY_TYPE.put("ALIPAY_SURETY",PayType.ALI_PAY);
    OMS_PAY_TYPE.put("ALIPAY_CHAIN",PayType.ALI_PAY);
//    defaut : PayType.OTHER

    ORDER_STATUS_MAP.put("TRADE_NO_CREATE_PAY",MallSalesOrderStatus.TRADE_NO_CREATE_PAY);
    ORDER_STATUS_MAP.put("WAIT_BUYER_PAY",MallSalesOrderStatus.WAIT_BUYER_PAY);
    ORDER_STATUS_MAP.put("WAIT_SELLER_SEND_GOODS",MallSalesOrderStatus.WAIT_BUYER_PAY);
    ORDER_STATUS_MAP.put("WAIT_BUYER_CONFIRM_GOODS",MallSalesOrderStatus.WAIT_BUYER_CONFIRM_GOODS);
    ORDER_STATUS_MAP.put("TRADE_BUYER_SIGNED",MallSalesOrderStatus.TRADE_BUYER_SIGNED);
    ORDER_STATUS_MAP.put("TRADE_FINISHED",MallSalesOrderStatus.TRADE_FINISHED);
    ORDER_STATUS_MAP.put("TRADE_CLOSED",MallSalesOrderStatus.TRADE_CLOSE);
    ORDER_STATUS_MAP.put("TRADE_CLOSED_BY_TAOBAO",MallSalesOrderStatus.TRADE_CLOSED_BY_TAOBAO);
//    default :   MallSalesOrderStatus.UNKNOWN

    MALL_SALES_ORDER_DETAIL_STATUS_MAP.put("WAIT_BUYER_PAY",MallSalesOrderDetailStatus.WAIT_BUYER_PAY);
    MALL_SALES_ORDER_DETAIL_STATUS_MAP.put("WAIT_SELLER_SEND_GOODS",MallSalesOrderDetailStatus.WAIT_SELLER_SEND_GOODS);
    //以下几种给一种状态
    MALL_SALES_ORDER_DETAIL_STATUS_MAP.put("WAIT_BUYER_CONFIRM_GOODS",MallSalesOrderDetailStatus.WAIT_BUYER_CONFIRM_GOODS);
    MALL_SALES_ORDER_DETAIL_STATUS_MAP.put("WAIT_BUYER_CONFIRM_GOODS_ACOUNTED",MallSalesOrderDetailStatus.WAIT_BUYER_CONFIRM_GOODS);
    MALL_SALES_ORDER_DETAIL_STATUS_MAP.put("PAY_ACOUNTED_GOODS_CONFIRM ",MallSalesOrderDetailStatus.WAIT_BUYER_CONFIRM_GOODS);
    MALL_SALES_ORDER_DETAIL_STATUS_MAP.put("PAY_WAIT_ACOUNT_GOODS_CONFIRM ",MallSalesOrderDetailStatus.WAIT_BUYER_CONFIRM_GOODS);

    MALL_SALES_ORDER_DETAIL_STATUS_MAP.put("TRADE_FINISHED",MallSalesOrderDetailStatus.TRADE_FINISHED);
    MALL_SALES_ORDER_DETAIL_STATUS_MAP.put("TRADE_CLOSED",MallSalesOrderDetailStatus.TRADE_CLOSE);
    MALL_SALES_ORDER_DETAIL_STATUS_MAP.put("PAID_FORBID_CONSIGN",MallSalesOrderDetailStatus.UNKNOWN);
//  default :   MallSalesOrderDetailStatus.UNKNOWN
  }

  static {
    MALL_PRODUCT_STATUS_STRING_MAP_STRING.put(MallProductStatus.ONSALE,"up");
    MALL_PRODUCT_STATUS_STRING_MAP_STRING.put(MallProductStatus.INSTOCK,"down");

    MALL_PRODUCT_STATUS_STRING_MAP_MENU.put("up",MallProductStatus.ONSALE);
    MALL_PRODUCT_STATUS_STRING_MAP_MENU.put("down",MallProductStatus.INSTOCK);
  }

  static {
    MALL_REFUND_STATUS_MAP.put(1,MallRefundStatus.WAIT_SELLER_AGREE);
    MALL_REFUND_STATUS_MAP.put(2,MallRefundStatus.WAIT_BUYER_RETURN_GOODS);
    MALL_REFUND_STATUS_MAP.put(3,MallRefundStatus.WAIT_SELLER_CONFIRM_GOODS);
    MALL_REFUND_STATUS_MAP.put(4,MallRefundStatus.CLOSED);
    MALL_REFUND_STATUS_MAP.put(5,MallRefundStatus.SUCCESS);
    MALL_REFUND_STATUS_MAP.put(6,MallRefundStatus.SELLER_REFUSE_BUYER);
    MALL_REFUND_STATUS_MAP.put(9,MallRefundStatus.NO_REFUND);
    MALL_REFUND_STATUS_MAP.put(10,MallRefundStatus.SELLER_REFUSE_BUYER);
    MALL_REFUND_STATUS_MAP.put(12,MallRefundStatus.WAIT_BUYER_RETURN_GOODS);
  }

}
