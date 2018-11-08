package com.greatonce.oms.biz.product.mall.mapping;

import com.greatonce.oms.bo.product.ProductMallMappingDownloadBO;

/**
 * 铺货关系下载.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/8
 */
public interface ProductMappingDownload {

  /**
   * 异步下载.
   */
  void asyncDownload(ProductMallMappingDownloadBO downloadBO);

  /**
   * 同步下载.
   */
  void download(ProductMallMappingDownloadBO downloadBO);
}
