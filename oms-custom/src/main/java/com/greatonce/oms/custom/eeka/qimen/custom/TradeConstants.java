package com.greatonce.oms.custom.eeka.qimen.custom;

/**
 * @author buer
 * @version 2018-01-08 16:14
 */
public final class TradeConstants {

  private TradeConstants() {
  }

  public static final String MOBILE_PATTERN = "[1][3,4,5,7,8]\\d{9}";
  public static final String TELEPHONE_PATTERN = "\\d{2,4}-?\\d{7,8}";
  public static final String SALES_ORDER_CODE_PATTERN = "SO\\d{14}";
  public static final String DISPATCH_ORDER_CODE_PATTERN = "DO\\d{14}";
  public static final String RETURN_ORDER_CODE_PATTERN = "RTO\\d{14}";

}
