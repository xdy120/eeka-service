package com.greatonce.oms.bridge.mall;

import com.greatonce.oms.bridge.mall.request.ProductQuantityUploadRequest;
import com.greatonce.oms.bridge.mall.request.ProductQueryRequest;
import com.greatonce.oms.bridge.mall.request.SkuQuantityUploadRequest;
import com.greatonce.oms.bridge.mall.response.ProductQuantityUploadResponse;
import com.greatonce.oms.bridge.mall.response.ProductQueryResponse;
import com.greatonce.oms.bridge.mall.response.SkuQuantityUploadResponse;

/**
 * 商品接口.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018-06-27
 */
public interface ProductBridge extends MallBridge {

  /**
   * 修改SKU库存.
   *
   * @param request SKU更新信息
   */
  SkuQuantityUploadResponse uploadQuantity(SkuQuantityUploadRequest request);

  /**
   * 修改商品库存.
   *
   * @param request 商品更新信息
   */
  ProductQuantityUploadResponse uploadQuantity(ProductQuantityUploadRequest request);

  /**
   * 查询商品.
   */
  ProductQueryResponse queryProduct(ProductQueryRequest request);

  /**
   * 通过平台商品ID查询.
   */
  ProductQueryResponse queryProductById(ProductQueryRequest request);

  /**
   * 通过平台商品编码查询.
   */
  ProductQueryResponse queryProductByCode(ProductQueryRequest request);

  /**
   * 商品查询接口是否支持多个状态.
   */
  boolean isSupportMultiStatus();

  /**
   * 平台商品链接.
   */
  String getMallProductUrl(String mallProductId);
}
