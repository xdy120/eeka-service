package com.greatonce.oms.util.logging;

import com.greatonce.oms.domain.base.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * @author buer
 * @version 2017-12-06 10:57
 */
public class StockUploadLogger extends OmsLogger {

  private static final String STORE_ID = "storeId";
  private static final String STORE_NAME = "storeName";
  private static final String BATCH_NO = "batchId";
  private static final String PRODUCT_CODE = "productCode";
  private static final String SKU_CODE = "skuCode";
  private static final String QUANTITY = "quantity";
  private static final String STATUS = "status";
  private static final String OPER = "oper";
  private final Logger logger;

  StockUploadLogger() {
    this.logger = LoggerFactory.getLogger("oms.service.logging.stock.mall.upload");
  }

  public void log(String batchNo, Store store, String productCode, String skuCode, String status,
      Integer quantity, String operator, String message, Object... args) {
    putMDCItem(STORE_ID, store.getStoreId());
    putMDCItem(STORE_NAME, store.getStoreName());
    putMDCItem(BATCH_NO, batchNo);
    putMDCItem(PRODUCT_CODE, productCode);
    putMDCItem(SKU_CODE, skuCode);
    putMDCItem(QUANTITY, quantity);
    putMDCItem(STATUS, status);
    putMDCItem(OPER, operator);
    doInfo(message, args);
  }

  @Override
  protected Logger getLogger() {
    return logger;
  }

  @Override
  protected void cleanMDC() {
    super.cleanMDC();
    MDC.remove(STORE_ID);
    MDC.remove(STORE_NAME);
    MDC.remove(BATCH_NO);
    MDC.remove(PRODUCT_CODE);
    MDC.remove(SKU_CODE);
    MDC.remove(QUANTITY);
    MDC.remove(STATUS);
    MDC.remove(OPER);
  }
}
