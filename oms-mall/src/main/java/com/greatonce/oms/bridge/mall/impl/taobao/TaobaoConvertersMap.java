package com.greatonce.oms.bridge.mall.impl.taobao;

import com.greatonce.oms.bridge.mall.request.OrderEventUpdateRequest.OrderEvent;
import com.greatonce.oms.domain.enums.MallType;
import com.greatonce.oms.domain.enums.mall.MallRefundStatus;
import com.greatonce.oms.domain.enums.mall.MallSalesOrderDetailStatus;
import com.greatonce.oms.domain.enums.mall.MallSalesOrderStatus;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2018/7/9
 */
public class TaobaoConvertersMap {

  /**
   * 平台发货，不同方式已生成发货单的错误码.
   */
  static final Map<String, String> DELIVERED_CODE_MAP = new HashMap<>(3);
  /**
   * 事件状态.
   */
  static final Map<OrderEvent, String> ORDER_EVENT_MAP = new HashMap<>(4);
  /**
   * 商家平台编码.
   */
  static final Map<MallType, String> MALL_TYPE_MAP = new HashMap<>(13);
  /**
   * 订单平台状态.
   */
  static final Map<MallSalesOrderStatus, String> MALL_SALES_ORDER_STATUS_MAP = new HashMap<>(6);
  /**
   * ods订单状态转换.
   */
  static final Map<String, MallSalesOrderStatus> ODS_ORDER_STATUS_MAP = new HashMap<>(8);
  /**
   * 平台退款状态转换.
   */
  static final Map<String, MallRefundStatus> MALL_REFUND_STATUS_MAP = new HashMap<>(6);
  /**
   * 平台明细状态转换.
   */
  static final Map<String, MallSalesOrderDetailStatus> MALL_SALES_ORDER_DETAIL_STATUS_MAP = new HashMap<>(9);

  static {
    MALL_TYPE_MAP.put(MallType.JD, "JD");
    MALL_TYPE_MAP.put(MallType.DANGDANG, "DD");
    MALL_TYPE_MAP.put(MallType.AMAZON_CN, "AMAZON");
    MALL_TYPE_MAP.put(MallType.AMAZON_COM, "AMAZON");
    MALL_TYPE_MAP.put(MallType.SUNING, "SN");
    MALL_TYPE_MAP.put(MallType.SUNING_SELF, "SN");
    MALL_TYPE_MAP.put(MallType.SUNING_SPECIAL, "SN");
    MALL_TYPE_MAP.put(MallType.GOME, "GM");
    MALL_TYPE_MAP.put(MallType.VIP, "WPH");
    MALL_TYPE_MAP.put(MallType.JU_MEI, "JM");
    MALL_TYPE_MAP.put(MallType.MO_GU_JIE, "MGJ");
    MALL_TYPE_MAP.put(MallType.ALIBABA, "1688");
    MALL_TYPE_MAP.put(MallType.UNDEFINE, "OTHER");

    ORDER_EVENT_MAP.put(OrderEvent.DOWNLOAD, "QIMEN_ERP_TRANSFER");
    ORDER_EVENT_MAP.put(OrderEvent.AUDIT, "QIMEN_ERP_CHECK");
    ORDER_EVENT_MAP.put(OrderEvent.DISPATCH, "QIMEN_CP_NOTIFY");
    ORDER_EVENT_MAP.put(OrderEvent.DELIVERY, "QIMEN_CP_OUT");

    DELIVERED_CODE_MAP.put("dummy", "isv.logistics-dummy-service-error:B27");
    DELIVERED_CODE_MAP.put("offline", "isv.logistics-offline-service-error:B27");
    DELIVERED_CODE_MAP.put("online", "isv.logistics-online-service-error:B27");

    MALL_SALES_ORDER_STATUS_MAP.put(MallSalesOrderStatus.WAIT_BUYER_PAY, "WAIT_BUYER_PAY");
    MALL_SALES_ORDER_STATUS_MAP.
        put(MallSalesOrderStatus.WAIT_SELLER_SEND_GOODS, "WAIT_SELLER_SEND_GOODS");
    MALL_SALES_ORDER_STATUS_MAP.
        put(MallSalesOrderStatus.WAIT_BUYER_CONFIRM_GOODS, "WAIT_BUYER_CONFIRM_GOODS");
    MALL_SALES_ORDER_STATUS_MAP.put(MallSalesOrderStatus.TRADE_FINISHED, "TRADE_FINISHED");
    MALL_SALES_ORDER_STATUS_MAP.put(MallSalesOrderStatus.TRADE_CLOSE, "TRADE_CLOSED");

    ODS_ORDER_STATUS_MAP.put("TRADE_NO_CREATE_PAY", MallSalesOrderStatus.TRADE_NO_CREATE_PAY);
    ODS_ORDER_STATUS_MAP.put("WAIT_BUYER_PAY", MallSalesOrderStatus.WAIT_BUYER_PAY);
    ODS_ORDER_STATUS_MAP.put("WAIT_SELLER_SEND_GOODS", MallSalesOrderStatus.WAIT_SELLER_SEND_GOODS);
    ODS_ORDER_STATUS_MAP
        .put("WAIT_BUYER_CONFIRM_GOODS", MallSalesOrderStatus.WAIT_BUYER_CONFIRM_GOODS);
    ODS_ORDER_STATUS_MAP.put("TRADE_BUYER_SIGNED", MallSalesOrderStatus.TRADE_BUYER_SIGNED);
    ODS_ORDER_STATUS_MAP.put("TRADE_FINISHED", MallSalesOrderStatus.TRADE_FINISHED);
    ODS_ORDER_STATUS_MAP.put("TRADE_CLOSED", MallSalesOrderStatus.TRADE_CLOSE);
    ODS_ORDER_STATUS_MAP.put("TRADE_CLOSED_BY_TAOBAO", MallSalesOrderStatus.TRADE_CLOSED_BY_TAOBAO);

    MALL_REFUND_STATUS_MAP.put("NO_REFUND", MallRefundStatus.NO_REFUND);
    MALL_REFUND_STATUS_MAP.put("WAIT_SELLER_AGREE", MallRefundStatus.WAIT_SELLER_AGREE);
    MALL_REFUND_STATUS_MAP.put("WAIT_BUYER_RETURN_GOODS", MallRefundStatus.WAIT_BUYER_RETURN_GOODS);
    MALL_REFUND_STATUS_MAP
        .put("WAIT_SELLER_CONFIRM_GOODS", MallRefundStatus.WAIT_SELLER_CONFIRM_GOODS);
    MALL_REFUND_STATUS_MAP.put("CLOSED", MallRefundStatus.CLOSED);
    MALL_REFUND_STATUS_MAP.put("SUCCESS", MallRefundStatus.SUCCESS);

    MALL_SALES_ORDER_DETAIL_STATUS_MAP.put("TRADE_NO_CREATE_PAY", MallSalesOrderDetailStatus.UNKNOWN);
    MALL_SALES_ORDER_DETAIL_STATUS_MAP.put("WAIT_BUYER_PAY", MallSalesOrderDetailStatus.WAIT_BUYER_PAY);
    MALL_SALES_ORDER_DETAIL_STATUS_MAP.put("WAIT_SELLER_SEND_GOODS", MallSalesOrderDetailStatus.WAIT_SELLER_SEND_GOODS);
    MALL_SALES_ORDER_DETAIL_STATUS_MAP.put("WAIT_BUYER_CONFIRM_GOODS", MallSalesOrderDetailStatus.WAIT_BUYER_CONFIRM_GOODS);
    MALL_SALES_ORDER_DETAIL_STATUS_MAP.put("TRADE_BUYER_SIGNED", MallSalesOrderDetailStatus.UNKNOWN);
    MALL_SALES_ORDER_DETAIL_STATUS_MAP.put("TRADE_FINISHED", MallSalesOrderDetailStatus.TRADE_FINISHED);
    MALL_SALES_ORDER_DETAIL_STATUS_MAP.put("TRADE_CLOSED", MallSalesOrderDetailStatus.TRADE_CLOSE);
    MALL_SALES_ORDER_DETAIL_STATUS_MAP.put("PAY_PENDING", MallSalesOrderDetailStatus.UNKNOWN);
  }

}
