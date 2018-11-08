package com.greatonce.oms.util.logging;


import com.greatonce.oms.domain.base.VirtualWarehouse;
import com.greatonce.oms.domain.enums.OrderType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/5/9
 */
public class StockTrackLogger extends OmsLogger {

  private static final String ORDER_CODE = "orderCode";
  private static final String ORDER_TYPE = "orderType";
  private static final String VIRTUAL_WAREHOUSE_ID = "virtualWarehouseId";
  private static final String VIRTUAL_WAREHOUSE_NAME = "virtualWarehouseName";
  private static final String SKU_CODE = "skuCode";
  private static final String QUANTITY = "quantity";
  private final Logger logger;

  public StockTrackLogger() {
    logger = LoggerFactory.getLogger("oms.service.logging.stock.track");
  }

  public void log(String orderCode, OrderType orderType, VirtualWarehouse virtualWarehouse,
      String skuCode, Integer quantity) {
    log(orderCode, orderType, virtualWarehouse.getVirtualWarehouseId(),
        virtualWarehouse.getVirtualWarehouseName(), skuCode, quantity);
  }

  public void log(Long virtualWarehouseId, String virtualWarehouseName, String skuCode,
      Integer quantity) {
    log(null, OrderType.OTHER, virtualWarehouseId, virtualWarehouseName, skuCode, quantity, "初始库存");
  }

  public void log(String orderCode, OrderType orderType, Long virtualWarehouseId,
      String virtualWarehouseName, String skuCode, Integer quantity) {
    log(orderCode, orderType, virtualWarehouseId, virtualWarehouseName, skuCode, quantity, null);
  }

  public void log(String orderCode, OrderType orderType, Long virtualWarehouseId,
      String virtualWarehouseName, String skuCode, Integer quantity, String message,
      Object... args) {
    putMDCItem(ORDER_CODE, orderCode);
    putMDCItem(ORDER_TYPE, orderType == null ? OrderType.OTHER.caption() : orderType.caption());
    putMDCItem(VIRTUAL_WAREHOUSE_ID, String.valueOf(virtualWarehouseId));
    putMDCItem(VIRTUAL_WAREHOUSE_NAME, virtualWarehouseName);
    putMDCItem(SKU_CODE, skuCode);
    putMDCItem(QUANTITY, String.valueOf(quantity));
    doInfo(message, args);
  }

  @Override
  protected void cleanMDC() {
    super.cleanMDC();
    MDC.remove(ORDER_CODE);
    MDC.remove(ORDER_TYPE);
    MDC.remove(VIRTUAL_WAREHOUSE_ID);
    MDC.remove(VIRTUAL_WAREHOUSE_NAME);
    MDC.remove(SKU_CODE);
    MDC.remove(QUANTITY);
  }

  @Override
  protected Logger getLogger() {
    return logger;
  }
}