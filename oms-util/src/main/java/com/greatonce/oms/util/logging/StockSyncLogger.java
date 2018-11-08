package com.greatonce.oms.util.logging;

import com.greatonce.oms.domain.base.Warehouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * 库存同步日志.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/5/9
 */
public class StockSyncLogger extends OmsLogger {

  private static final String BATCH_ID = "batchId";
  private static final String WAREHOUSE_ID = "warehouseId";
  private static final String WAREHOUSE_CODE = "warehouseCode";
  private static final String WAREHOUSE_NAME = "warehouseName";
  private static final String SKU_CODE = "skuCode";
  private static final String QUANTITY = "quantity";
  private final Logger logger;

  public StockSyncLogger() {
    this.logger = LoggerFactory.getLogger("oms.service.logging.stock.sync");
  }

  public void log(Long batchId, Warehouse warehouse, String skuCode, Integer quantity) {
    log(batchId, warehouse.getWarehouseId(), warehouse.getWarehouseCode(),
        warehouse.getWarehouseName(), skuCode, quantity, null);
  }

  public void log(Long batchId, Warehouse warehouse, String skuCode, Integer quantity,
      String message, Object... args) {
    log(batchId, warehouse.getWarehouseId(), warehouse.getWarehouseCode(),
        warehouse.getWarehouseName(), skuCode, quantity, message, args);
  }

  public void log(Long batchId, Long warehouseId, String warehouseCode, String warehouseName,
      String skuCode, Integer quantity) {
    log(batchId, warehouseId, warehouseCode, warehouseName, skuCode, quantity, null);
  }

  public void log(Long batchId, Long warehouseId, String warehouseCode, String warehouseName,
      String skuCode, Integer quantity, String message, Object... args) {
    putMDCItem(BATCH_ID, String.valueOf(batchId));
    putMDCItem(WAREHOUSE_ID, String.valueOf(warehouseId));
    putMDCItem(WAREHOUSE_CODE, warehouseCode);
    putMDCItem(WAREHOUSE_NAME, warehouseName);
    putMDCItem(SKU_CODE, skuCode);
    putMDCItem(QUANTITY, String.valueOf(quantity));
    doInfo(message, args);
  }

  @Override
  protected void cleanMDC() {
    super.cleanMDC();
    MDC.remove(BATCH_ID);
    MDC.remove(WAREHOUSE_ID);
    MDC.remove(WAREHOUSE_CODE);
    MDC.remove(WAREHOUSE_NAME);
    MDC.remove(SKU_CODE);
    MDC.remove(QUANTITY);
  }

  @Override
  protected Logger getLogger() {
    return logger;
  }
}
