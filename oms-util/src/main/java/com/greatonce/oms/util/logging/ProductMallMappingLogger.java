package com.greatonce.oms.util.logging;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.domain.product.ProductMallMapping;
import com.greatonce.oms.util.BizContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * @author ginta
 * @version 2017-12-06 11:47
 */
public class ProductMallMappingLogger extends OmsLogger {

  private static final String OPER = "oper";
  private static final String STORE_ID = "storeId";
  private static final String STORE_NAME = "storeName";
  private static final String MALL_PRODUCT_ID = "mallProductId";
  private static final String MALL_SKU_ID = "mallSkuId";
  private static final String MALL_PRODUCT_OUT_CODE = "mallProductOutCode";
  private static final String MALL_SKU_OUT_CODE = "mallSkuOutCode";
  private final Logger logger;

  ProductMallMappingLogger() {
    this.logger = LoggerFactory.getLogger("oms.service.logging.product.mall.mapping");
  }

  public void log(ProductMallMapping mapping) {
    putMDCItem(OPER, BizContext.getNickname());
    putMDCItem(STORE_ID, mapping.getStoreId());
    putMDCItem(STORE_NAME, mapping.getStoreName());
    putMDCItem(MALL_PRODUCT_ID, mapping.getMallProductId());
    putMDCItem(MALL_SKU_ID, mapping.getMallSkuId());
    putMDCItem(MALL_PRODUCT_OUT_CODE, mapping.getMallProductOutCode());
    putMDCItem(MALL_SKU_OUT_CODE, mapping.getMallSkuOutCode());
    String message = "自动上传：{}，自动上架：{}，自动下架：{}";
    doInfo(message, toChinese(mapping.isAutoUpload()), toChinese(mapping.isAutoListing()),
        toChinese(mapping.isAutoDelisting()));
  }

  private String toChinese(Boolean value) {
    return Assert.isTrue(value) ? "是" : "否";
  }

  @Override
  protected Logger getLogger() {
    return this.logger;
  }

  @Override
  protected void cleanMDC() {
    super.cleanMDC();
    MDC.remove(STORE_ID);
    MDC.remove(MALL_PRODUCT_ID);
    MDC.remove(MALL_SKU_ID);
    MDC.remove(MALL_PRODUCT_OUT_CODE);
    MDC.remove(MALL_SKU_OUT_CODE);
  }
}
