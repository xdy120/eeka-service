package com.greatonce.oms.util.logging;

import com.greatonce.oms.domain.base.Warehouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/28
 */
public class WmsLogger extends OmsLogger {

  private static final String WAREHOUSE_ID = "warehouseId";
  private static final String WAREHOUSE_CODE = "warehouseCode";
  private static final String WAREHOUSE_NAME = "warehouseName";

  @Override
  protected Logger getLogger() {
    return LoggerFactory.getLogger("oms.service.logging.wms");
  }

  public void info(Warehouse warehouse, String message, Object... args) {
    putMDC(warehouse);
    doInfo(message, args);
  }

  public void debug(Warehouse warehouse, String message, Object... args) {
    putMDC(warehouse);
    doDebug(message, args);
  }

  public void error(Warehouse warehouse, String message, Object... args) {
    putMDC(warehouse);
    doError(message, args);
  }

  private void putMDC(Warehouse warehouse) {
    putMDCItem(WAREHOUSE_ID, warehouse.getWarehouseId());
    putMDCItem(WAREHOUSE_CODE, warehouse.getWarehouseCode());
    putMDCItem(WAREHOUSE_NAME, warehouse.getWarehouseName());
  }

  @Override
  protected void cleanMDC() {
    super.cleanMDC();
    MDC.remove(WAREHOUSE_ID);
    MDC.remove(WAREHOUSE_CODE);
    MDC.remove(WAREHOUSE_NAME);
  }
}
