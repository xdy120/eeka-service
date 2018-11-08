package com.greatonce.oms.bo.stock;

import java.util.List;

/**
 * 异步上传实体.
 *
 * @author 82743
 */
public class AsyncStockUploadBO extends StockUploadBO {

  private List<StockUploadSkuBO> skus;

  public List<StockUploadSkuBO> getSkus() {
    return skus;
  }

  public void setSkus(List<StockUploadSkuBO> skus) {
    this.skus = skus;
  }
}
