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
public class TranslateRefundLogger extends OmsLogger {

  private static final String BATCH_ID = "batchId";
  private static final String STORE_ID = "storeId";
  private static final String STORE_NAME = "storeName";
  private static final String TRADE_ID = "tradeId";
  private static final String REFUND_ID = "refundId";
  private final Logger logger;

  public TranslateRefundLogger() {
    this.logger = LoggerFactory.getLogger("oms.service.logging.translate.refund");
  }

  public void log(Long batchId, Store store, String tradeId, String refundId, String message,
      Object... args) {
    log(batchId, store.getStoreId(), store.getStoreName(), tradeId, refundId, message, args);
  }

  public void log(Long batchId, Long storeId, String storeName, String tradeId, String refundId,
      String message, Object... args) {
    putMDC(batchId, storeId, storeName, tradeId, refundId);
    doInfo(message, args);
  }

  public void debug(Long batchId, Store store, String tradeId, String refundId, String message,
      Object... args) {
    debug(batchId, store.getStoreId(), store.getStoreName(), tradeId, refundId, message, args);
  }

  public void debug(Long batchId, Long storeId, String storeName, String tradeId, String refundId,
      String message, Object... args) {
    putMDC(batchId, storeId, storeName, tradeId, refundId);
    doDebug(message, args);
  }

  public void error(Long batchId, Store store, String tradeId, String refundId, String message,
      Object... args) {
    error(batchId, store.getStoreId(), store.getStoreName(), tradeId, refundId, message, args);
  }

  public void error(Long batchId, Long storeId, String storeName, String tradeId, String refundId,
      String message, Object... args) {
    putMDC(batchId, storeId, storeName, tradeId, refundId);
    doError(message, args);
  }

  private void putMDC(Long batchId, Long storeId, String storeName, String tradeId,
      String refundId) {
    putMDCItem(BATCH_ID, batchId);
    putMDCItem(STORE_ID, storeId);
    putMDCItem(STORE_NAME, storeName);
    putMDCItem(TRADE_ID, tradeId);
    putMDCItem(REFUND_ID, refundId);
  }

  @Override
  protected void cleanMDC() {
    super.cleanMDC();
    MDC.remove(BATCH_ID);
    MDC.remove(STORE_ID);
    MDC.remove(STORE_NAME);
    MDC.remove(TRADE_ID);
    MDC.remove(REFUND_ID);
  }

  @Override
  protected Logger getLogger() {
    return logger;
  }
}
