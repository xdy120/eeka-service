package com.greatonce.oms.util.logging;


import com.greatonce.oms.domain.enums.OmsModule;

/**
 * 日志工厂
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/9
 */
public class OmsLoggerFactory {


  public static BizLogger getLogger(final OmsModule module) {
    return new BizLogger(module);
  }

  public static ProductMallMappingLogger getProductMallMappingLogger() {
    return new ProductMallMappingLogger();
  }

  public static StockUploadLogger getStockUploadLogger() {
    return new StockUploadLogger();
  }

  public static StockSyncLogger getStockSyncLogger() {
    return new StockSyncLogger();
  }

  public static StockTrackLogger getStockTrackLogger() {
    return new StockTrackLogger();
  }

  public static WmsLogger getWmsLogger() {
    return new WmsLogger();
  }

  public static MallLogger getMallLogger() {
    return new MallLogger();
  }

  public static DispatchLogger getDispatchLogger() {
    return new DispatchLogger();
  }

  public static TranslateOrderLogger getTranslateOrderLogger() {
    return new TranslateOrderLogger();
  }

  public static TranslateRefundLogger getTranslateRefundLogger() {
    return new TranslateRefundLogger();
  }

  public static TranslateExchangeLogger getTranslateExchangeLogger() {
    return new TranslateExchangeLogger();
  }
}
