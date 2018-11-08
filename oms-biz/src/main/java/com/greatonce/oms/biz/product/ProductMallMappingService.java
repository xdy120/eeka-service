package com.greatonce.oms.biz.product;

import com.greatonce.oms.biz.BatchBizService;
import com.greatonce.oms.domain.enums.MallType;
import com.greatonce.oms.domain.enums.MarketingType;
import com.greatonce.oms.domain.product.ProductMallMapping;
import com.greatonce.oms.query.product.ProductMallMappingQuery;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * ProductMallMapping <br/>
 * 铺货关系.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface ProductMallMappingService extends
    BatchBizService<ProductMallMapping, ProductMallMappingQuery> {

  List<ProductMallMapping> listMapping(Long skuId, Long storeId);

  List<ProductMallMapping> listMapping(Collection<Long> skuIds, Long storeId);

  List<ProductMallMapping> listMapping(Collection<Long> skuIds, Collection<Long> storeIds);

  /**
   * 获取商城.
   */
  ProductMallMapping getByMallSkuOutCode(String mallSkuOutCode, Long storeId);

  Map<String, Long> listStoreMapping(Long storeId);

  /**
   * 获取店铺已关联并开启了自动上传的铺货关系.
   */
  List<ProductMallMapping> listAutoUploadMapping(Long storeId);

  /**
   * 开始活动.
   */
  void beginMarketing(Long storeId, Long marketingId, MarketingType marketingType,
      boolean autoUpload, Long skuId, String mallProductId);

  /**
   * 结束活动.
   */
  void endMarketing(Long storeId, boolean autoUpload, Long skuId, String mallProductId);

  /**
   * 导出excel.
   */
  void export(String fileName, ProductMallMappingQuery filter);

  /**
   * 设置代发.
   */
  void setDropShipping(ProductMallMapping mapping);

  /**
   * 取消代发.
   */
  void cancelDropShipping(ProductMallMapping mapping);

  /**
   * 获取商品的平台链接.
   */
  String getMallProductUrl(ProductMallMapping mapping);
}