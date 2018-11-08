package com.greatonce.oms.util.logging;

import com.greatonce.oms.domain.base.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/28
 */
public class MallLogger extends OmsLogger {

  private static final String STORE_ID = "storeId";
  private static final String STORE_NAME = "storeName";
  private final Logger logger;

  public MallLogger() {
    this.logger = LoggerFactory.getLogger("oms.service.logging.mall");
  }

  @Override
  protected Logger getLogger() {
    return this.logger;
  }

  public void info(Store store, String message, Object... args) {
    putMDC(store);
    doInfo(message, args);
  }

  public void debug(Store store, String message, Object... args) {
    putMDC(store);
    doDebug(message, args);
  }

  public void error(Store store, String message, Object... args) {
    putMDC(store);
    doError(message, args);
  }

  private void putMDC(Store store) {
    putMDCItem(STORE_ID, store.getStoreId());
    putMDCItem(STORE_NAME, store.getStoreName());
  }

  @Override
  protected void cleanMDC() {
    super.cleanMDC();
    MDC.remove(STORE_ID);
    MDC.remove(STORE_NAME);
  }
}
