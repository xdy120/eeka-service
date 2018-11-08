package com.greatonce.oms.biz.product;

import com.greatonce.oms.biz.BatchBizService;
import com.greatonce.oms.biz.EnableBizService;
import com.greatonce.oms.domain.product.Product;
import com.greatonce.oms.domain.product.ProductSku;
import com.greatonce.oms.query.product.ProductQuery;
import java.util.List;
import java.util.Map;

/**
 * 商品.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface ProductService extends BatchBizService<Product, ProductQuery>,
    EnableBizService<Product> {

  /**
   * 修改SKU信息.
   *
   * @param productId 商品ID
   * @param sku sku信息
   */
  void modifySkuInfo(Long productId, ProductSku sku);

  /**
   * 检查商品规格是否存在.
   */
  boolean exists(String productCode);

  void importProduct(List<Map<String, String>> list);

  /**
   * 根据productCode搜索商品信息.
   */
  Product getProductByCode(String productCode);

  /**
   * 根据productCode和skuCode 调整价格.
   */
  void importProductPrice(List<Map<String, String>> list);

  /**
   * 导出SKU.
   */
  void exportSku(String fileName, ProductQuery productQuery);

  /**
   * 通知WMS.
   */
  void noticeWms(Product product);

}