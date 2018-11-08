package com.greatonce.oms.biz.impl.stock;

import com.greatonce.oms.bridge.mall.request.SkuQuantityUploadRequest;
import com.greatonce.oms.bridge.mall.response.SkuQuantityUploadResponse;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.product.ProductMallMapping;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import com.greatonce.oms.util.logging.StockUploadLogger;

/**
 * 库存日志工具类.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/16
 */
public class StockUploadLogUtil {

  private static final StockUploadLogger LOGGER = OmsLoggerFactory.getStockUploadLogger();

  public static void log(SkuQuantityUploadResponse response, String batchNo, String operator,
      String reason) {
    SkuQuantityUploadRequest request = response.getRequest();
    if (response.isSuccess()) {
      LOGGER.log(batchNo, request.getStore(),
          request.getMapping().getProductCode(),
          request.getMapping().getSkuCode(),
          "成功",
          request.getQuantity(),
          operator,
          reason);
    } else {
      LOGGER.log(batchNo, request.getStore(),
          request.getMapping().getProductCode(),
          request.getMapping().getSkuCode(),
          "失败",
          request.getQuantity(),
          operator,
          "{},{}", reason, response.getResult());
    }

  }

  public static void log(Store store, ProductMallMapping mapping, String batchNo, String operator,
      String message, Object... args) {
    LOGGER.log(batchNo, store,
        mapping.getProductCode(),
        mapping.getSkuCode(),
        "失败",
        0,
        operator,
        message, args);
  }

  public static void log(SkuQuantityUploadRequest request, String batchNo, String operator,
      String message, Object... args) {
    LOGGER.log(batchNo, request.getStore(),
        request.getMapping().getProductCode(),
        request.getMapping().getSkuCode(),
        "失败",
        request.getQuantity(),
        operator,
        message, args);
  }

  public static void log(Store store, String productCode, String skuCode, String batchNo,
      String operator, String message, Object... args) {
    LOGGER.log(batchNo, store,
        productCode,
        skuCode,
        "失败",
        0,
        operator,
        message, args);
  }
}

