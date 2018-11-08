package com.greatonce.oms.bridge.wms.impl.qimen.convert;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.domain.enums.MallType;
import com.greatonce.oms.domain.enums.OrderType;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 奇门出库类型转换.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/30
 */
public abstract class QimenConverter {

  private static Pattern ORDER_PREFIX_REGEX = Pattern.compile("[A-Z]+");

  /**
   * OMS转奇门发货单类型.
   */
  private static Map<OrderType, QimenDeliveryOrderType> OMS_QIMEN_DELIVERY_ORDER_TYPE_MAP = new HashMap<>(
      2);

  /**
   * OMS转奇门退货单类型.
   */
  private static Map<OrderType, QimenReturnOrderType> OMS_QIMEN_RETURN_ORDER_TYPE_MAP = new HashMap<>(
      2);
  /**
   * OMS转奇门出库单类型.
   */
  private static Map<OrderType, QimenOutOrderType> OMS_QIMEN_OUT_ORDER_TYPE_MAP = new HashMap<>(2);
  /**
   * OMS转奇门入库单类型.
   */
  private static Map<OrderType, QimenInOrderType> OMS_QIMEN_IN_ORDER_TYPE_MAP = new HashMap<>(2);
  /**
   * 奇门转OMS出库单类型.
   */
  private static Map<String, OrderType> QIMEN_OMS_ORDER_TYPE_MAP = new HashMap<>(17);
  /**
   * 奇门转OMS出库单类型.
   */
  private static Map<String, OrderType> QIMEN_OMS_OUT_ORDER_TYPE_MAP = new HashMap<>(2);
  /**
   * 奇门转OMS入库单类型.
   */
  private static Map<String, OrderType> QIMEN_OMS_IN_ORDER_TYPE_MAP = new HashMap<>(2);
  /**
   * 商城类型.
   */
  private static Map<MallType, String> QIMEN_OMS_MALL_TYPE_MAP = new HashMap<>(15);
  /**
   * 单据状态.
   */
  private static Map<String, String> ORDER_PROCESS_MAP = new HashMap<>(20);

  static {
    //OMS转奇门发货单类型
    OMS_QIMEN_DELIVERY_ORDER_TYPE_MAP
        .put(OrderType.B2C_DISPATCH_ORDER, QimenDeliveryOrderType.JYCK);
    //OMS转奇门退货单类型
    OMS_QIMEN_RETURN_ORDER_TYPE_MAP
        .put(OrderType.B2C_RETURN_NOTICE_ORDER, QimenReturnOrderType.THRK);
    //OMS转奇门出库单类型
    OMS_QIMEN_OUT_ORDER_TYPE_MAP.put(OrderType.VIP_DISPATCH_ORDER, QimenOutOrderType.JITCK);
    OMS_QIMEN_OUT_ORDER_TYPE_MAP
        .put(OrderType.PURCHASE_RETURN_ORDER, QimenOutOrderType.CGTH);
    //OMS转奇门入库单类型
    OMS_QIMEN_IN_ORDER_TYPE_MAP.put(OrderType.VIP_RETURN_NOTICE_ORDER, QimenInOrderType.B2BRK);
    OMS_QIMEN_IN_ORDER_TYPE_MAP.put(OrderType.PURCHASE_NOTICE_ORDER, QimenInOrderType.CGRK);
    //奇门转OMS单据
    QIMEN_OMS_ORDER_TYPE_MAP.put("JYCK", OrderType.B2C_DISPATCH_ORDER);
    QIMEN_OMS_ORDER_TYPE_MAP.put("HHCK", OrderType.B2C_DISPATCH_ORDER);
    QIMEN_OMS_ORDER_TYPE_MAP.put("BFCK", OrderType.B2C_DISPATCH_ORDER);
    QIMEN_OMS_ORDER_TYPE_MAP.put("PTCK", OrderType.OUT_ORDER);
    QIMEN_OMS_ORDER_TYPE_MAP.put("DBCK", OrderType.OTHER);
    QIMEN_OMS_ORDER_TYPE_MAP.put("B2BRK", OrderType.VIP_RETURN_NOTICE_ORDER);
    QIMEN_OMS_ORDER_TYPE_MAP.put("B2BCK", OrderType.VIP_DISPATCH_ORDER);
    QIMEN_OMS_ORDER_TYPE_MAP.put("QTCK", OrderType.OTHER);
    QIMEN_OMS_ORDER_TYPE_MAP.put("SCRK", OrderType.OTHER);
    QIMEN_OMS_ORDER_TYPE_MAP.put("LYRK", OrderType.LOAN_IN_ORDER);
    QIMEN_OMS_ORDER_TYPE_MAP.put("CCRK", OrderType.OTHER);
    QIMEN_OMS_ORDER_TYPE_MAP.put("CGRK", OrderType.PURCHASE_ORDER);
    QIMEN_OMS_ORDER_TYPE_MAP.put("DBRK", OrderType.OTHER);
    QIMEN_OMS_ORDER_TYPE_MAP.put("QTRK", OrderType.OTHER);
    QIMEN_OMS_ORDER_TYPE_MAP.put("XTRK", OrderType.B2C_RETURN_NOTICE_ORDER);
    QIMEN_OMS_ORDER_TYPE_MAP.put("HHRK", OrderType.B2C_RETURN_NOTICE_ORDER);
    QIMEN_OMS_ORDER_TYPE_MAP.put("CNJG", OrderType.OTHER);
    //奇门转OMS出库单类型
    QIMEN_OMS_OUT_ORDER_TYPE_MAP.put("JITCK", OrderType.VIP_DISPATCH_ORDER);
    QIMEN_OMS_OUT_ORDER_TYPE_MAP.put("DBCK", OrderType.OUT_ORDER);
    QIMEN_OMS_OUT_ORDER_TYPE_MAP.put("CGTH", OrderType.PURCHASE_RETURN_ORDER);
    //奇门转OMS入库单类型
    QIMEN_OMS_IN_ORDER_TYPE_MAP.put("B2BRK", OrderType.VIP_RETURN_NOTICE_ORDER);
    QIMEN_OMS_IN_ORDER_TYPE_MAP.put("DBRK", OrderType.IN_ORDER);
    QIMEN_OMS_IN_ORDER_TYPE_MAP.put("CGRK", OrderType.PURCHASE_NOTICE_ORDER);
    //商城类型
    QIMEN_OMS_MALL_TYPE_MAP.put(MallType.TAOBAO, "TB");
    QIMEN_OMS_MALL_TYPE_MAP.put(MallType.TMALL, "TM");
    QIMEN_OMS_MALL_TYPE_MAP.put(MallType.JD, "JD");
    QIMEN_OMS_MALL_TYPE_MAP.put(MallType.DANGDANG, "DD");
    QIMEN_OMS_MALL_TYPE_MAP.put(MallType.AMAZON_CN, "AMAZON");
    QIMEN_OMS_MALL_TYPE_MAP.put(MallType.AMAZON_COM, "AMAZON");
    QIMEN_OMS_MALL_TYPE_MAP.put(MallType.SUNING, "SN");
    QIMEN_OMS_MALL_TYPE_MAP.put(MallType.SUNING_SELF, "SN");
    QIMEN_OMS_MALL_TYPE_MAP.put(MallType.SUNING_SPECIAL, "SN");
    QIMEN_OMS_MALL_TYPE_MAP.put(MallType.GOME, "GM");
    QIMEN_OMS_MALL_TYPE_MAP.put(MallType.VIP, "WPH");
    QIMEN_OMS_MALL_TYPE_MAP.put(MallType.JU_MEI, "JM");
    QIMEN_OMS_MALL_TYPE_MAP.put(MallType.MO_GU_JIE, "MGJ");
    QIMEN_OMS_MALL_TYPE_MAP.put(MallType.ALIBABA, "1688");
    QIMEN_OMS_MALL_TYPE_MAP.put(MallType.MIA, "MIA");
    //单据状态
    ORDER_PROCESS_MAP.put("ACCEPT", "仓库接单");
    ORDER_PROCESS_MAP.put("PARTFULFILLED", "部分收货完成;");
    ORDER_PROCESS_MAP.put("FULFILLED", "收货完成;");
    ORDER_PROCESS_MAP.put("PRINT", "打印;");
    ORDER_PROCESS_MAP.put("PICK", "捡货;");
    ORDER_PROCESS_MAP.put("CHECK", "复核;");
    ORDER_PROCESS_MAP.put("PACKAGE", "打包;");
    ORDER_PROCESS_MAP.put("WEIGH", "称重;");
    ORDER_PROCESS_MAP.put("READY", "待提货;");
    ORDER_PROCESS_MAP.put("DELIVERED", "已发货;");
    ORDER_PROCESS_MAP.put("REFUSE", "买家拒签;");
    ORDER_PROCESS_MAP.put("EXCEPTION", "异常;");
    ORDER_PROCESS_MAP.put("CLOSED", "关闭;");
    ORDER_PROCESS_MAP.put("CANCELED", "取消");
    ORDER_PROCESS_MAP.put("REJECT", "仓库拒单;");
    ORDER_PROCESS_MAP.put("SIGN", "签收;");
    ORDER_PROCESS_MAP.put("TMSCANCELED", "快递拦截;");
    ORDER_PROCESS_MAP.put("OTHER", "其他;");
    ORDER_PROCESS_MAP.put("PARTDELIVERED", "部分发货完成;");
    ORDER_PROCESS_MAP.put("TMSCANCELFAILED", "快递拦截失败;");
  }

  /**
   * 转换出库单类型.
   */
  public static QimenDeliveryOrderType toDeliveryOrderType(OrderType orderType) {
    return OMS_QIMEN_DELIVERY_ORDER_TYPE_MAP.getOrDefault(orderType, QimenDeliveryOrderType.QTCK);
  }

  /**
   * 转换出库单类型.
   */
  public static String toDeliveryOrderTypeString(OrderType orderType) {
    return toDeliveryOrderType(orderType).toString();
  }

  /**
   * 转换出库单类型.
   */
  public static QimenOutOrderType toOutOrderType(OrderType orderType) {
    return OMS_QIMEN_OUT_ORDER_TYPE_MAP.getOrDefault(orderType, QimenOutOrderType.DBCK);
  }

  /**
   * 反向转换单据类型.
   */
  public static OrderType reverseOutOrderType(String orderType, String orderCode) {
    OrderType type = QIMEN_OMS_OUT_ORDER_TYPE_MAP.getOrDefault(orderType, OrderType.OUT_ORDER);
    final Matcher matcher = ORDER_PREFIX_REGEX.matcher(orderCode);
    if (matcher.find()) {
      switch (matcher.group()) {
        case "LO":
          type = OrderType.LOAN_OUT_ORDER;
          break;
        default:
          break;
      }
    }
    return type;
  }

  /**
   * 转换出库单类型.
   */
  public static String toOutOrderTypeString(OrderType orderType) {
    return toOutOrderType(orderType).toString();
  }

  /**
   * 转换入库单类型.
   */
  public static QimenInOrderType toInOrderType(OrderType orderType) {
    return OMS_QIMEN_IN_ORDER_TYPE_MAP.getOrDefault(orderType, QimenInOrderType.DBRK);
  }

  /**
   * 转换入库单类型.
   */
  public static QimenReturnOrderType toReturnOrderType(OrderType orderType) {
    return OMS_QIMEN_RETURN_ORDER_TYPE_MAP.getOrDefault(orderType, QimenReturnOrderType.HHRK);
  }

  public static String toReturnOrderTypeString(OrderType orderType) {
    return toReturnOrderType(orderType).toString();
  }

  /**
   * 转换入库单类型.
   */
  public static OrderType reverseInOrderType(String orderType, String orderCode) {
    OrderType type = QIMEN_OMS_IN_ORDER_TYPE_MAP.getOrDefault(orderType, OrderType.IN_ORDER);
    final Matcher matcher = ORDER_PREFIX_REGEX.matcher(orderCode);
    if (matcher.find()) {
      switch (matcher.group()) {
        case "LI":
          return OrderType.LOAN_IN_ORDER;
        default:
          return type;
      }
    }
    return type;
  }

  /**
   * 转换入库单类型.
   */
  public static String toInOrderTypeString(OrderType orderType) {
    return toInOrderType(orderType).toString();
  }

  /**
   * 转换入库单类型.
   */
  public static OrderType reverseOrderType(String orderType) {
    return QIMEN_OMS_ORDER_TYPE_MAP.getOrDefault(orderType, OrderType.OUT_ORDER);
  }

  /**
   * 转换商城类型.
   */
  public static String toMallTypeString(MallType mallType) {
    return QIMEN_OMS_MALL_TYPE_MAP.getOrDefault(mallType, "OTHER");
  }

  /**
   * 获取单据处理中文状态.
   *
   * @param code 代码
   */
  public static String getOrderProcessStatus(String code) {
    return ORDER_PROCESS_MAP.getOrDefault(code, "其他");
  }

  /**
   * 布尔转为Y/N.
   */
  public static String toBooleanString(Boolean value) {
    return Assert.isTrue(value) ? "Y" : "N";
  }

  /**
   * Y/N转为布尔.
   */
  public static boolean toBoolean(String value) {
    return "Y".equalsIgnoreCase(value);
  }
}
