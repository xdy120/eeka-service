package com.greatonce.oms.util.logging;

import com.greatonce.oms.domain.base.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * 库存同步日志
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/5/9
 */
public class DispatchLogger extends OmsLogger {

  private static final String BATCH_ID = "batchId";
  private static final String STORE_ID = "storeId";
  private static final String STORE_NAME = "storeName";
  private static final String TRADE_ID = "tradeId";
  private static final String SALES_ORDER_CODE = "salesOrderCode";
  private final Logger logger;

  public DispatchLogger() {
    this.logger = LoggerFactory.getLogger("oms.service.logging.dispatch");
  }

  public void info(Long batchId, Store store, String salesOrderCode, String tradeId, String message,
      Object... args) {
    info(batchId, store.getStoreId(), store.getStoreName(), salesOrderCode, tradeId, message, args);
  }

  public void info(Long batchId, Long storeId, String storeName, String salesOrderCode,
      String tradeId, String message, Object... args) {
    putMDC(batchId, storeId, storeName, salesOrderCode, tradeId);
    doInfo(message, args);
  }

  public void debug(Long batchId, Store store, String salesOrderCode, String tradeId,
      String message, Object... args) {
    debug(batchId, store.getStoreId(), store.getStoreName(), salesOrderCode, tradeId, message,
        args);
  }

  public void debug(Long batchId, Long storeId, String storeName, String salesOrderCode,
      String tradeId, String message, Object... args) {
    putMDC(batchId, storeId, storeName, salesOrderCode, tradeId);
    doDebug(message, args);
  }

  public void error(Long batchId, Store store, String salesOrderCode, String tradeId,
      String message, Object... args) {
    error(batchId, store.getStoreId(), store.getStoreName(), salesOrderCode, tradeId, message,
        args);
  }

  public void error(Long batchId, Long storeId, String storeName, String salesOrderCode,
      String tradeId, String message, Object... args) {
    putMDC(batchId, storeId, storeName, salesOrderCode, tradeId);
    doError(message, args);
  }

  private void putMDC(Long batchId, Long storeId, String storeName, String salesOrderCode,
      String tradeId) {
    putMDCItem(BATCH_ID, batchId);
    putMDCItem(SALES_ORDER_CODE, salesOrderCode);
    putMDCItem(STORE_ID, storeId);
    putMDCItem(STORE_NAME, storeName);
    putMDCItem(TRADE_ID, tradeId);
  }

  @Override
  protected void cleanMDC() {
    super.cleanMDC();
    MDC.remove(BATCH_ID);
    MDC.remove(SALES_ORDER_CODE);
    MDC.remove(STORE_ID);
    MDC.remove(STORE_NAME);
    MDC.remove(TRADE_ID);
  }

  @Override
  protected Logger getLogger() {
    return logger;
  }
}
