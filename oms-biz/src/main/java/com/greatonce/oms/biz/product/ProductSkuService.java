package com.greatonce.oms.biz.product;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.biz.BatchBizService;
import com.greatonce.oms.biz.EnableBizService;
import com.greatonce.oms.domain.product.ProductSku;
import com.greatonce.oms.query.product.ProductSkuQuery;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 商品规格.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface ProductSkuService extends BatchBizService<ProductSku, ProductSkuQuery>,
    EnableBizService<ProductSku> {

  /**
   * 获取有效的SKU.
   *
   * @param skuCode 规格编码
   */
  ProductSku getEffectiveByCode(String skuCode);

  /**
   * 获取有效的SKU.
   *
   * @param skuId 规格ID
   */
  ProductSku getEffectiveById(Long skuId);

  /**
   * 新建组合套装.
   *
   * @param skus 套装
   */
  void insertCombinationBatch(Collection<ProductSku> skus);

  /**
   * 新建组合套装.
   *
   * @param sku 套装
   */
  void insertCombination(ProductSku sku);

  /**
   * 修改组合套装.
   *
   * @param sku 套装
   */
  void updateCombination(ProductSku sku);

  /**
   * 检查商品规格是否存在.
   */
  boolean exists(String skuCode);

  /**
   * 获取商品下的SKU.
   *
   * @param productId 商品ID
   * @return SKU集合
   */
  List<ProductSku> listByProductId(Long productId);

  /**
   * 获取商品下的SKU.
   *
   * @param productCode 商品编码
   * @return SKU集合
   */
  List<ProductSku> listByProductCode(String productCode);

  /**
   * 获取商品下启用的SKU.
   *
   * @param productId 商品ID
   * @return SKU集合
   */
  List<ProductSku> listEffectiveByProductId(Long productId);

  /**
   * 获取商品下启用的SKU.
   *
   * @param productCode 商品编码
   * @return SKU集合
   */
  List<ProductSku> listEffectiveByProductCode(String productCode);

  /**
   * 获取完整信息，带Product.
   *
   * @param query 查询对象
   * @return SKU集合
   */
  List<ProductSku> listFullInfo(ProductSkuQuery query);

  /**
   * 获取完整信息，带Product.
   *
   * @param query 查询对象
   * @param page 页数
   * @param pageSize 页大小
   * @return SKU集合
   */
  PageList<ProductSku> listFullInfo(ProductSkuQuery query, int page, int pageSize);

  /**
   * 导入组合套装.
   *
   * @param list 数据集合,List为行，Map为列
   */
  void importCombination(List<Map<String, String>> list);

  /**
   * 导出组合套装.
   *
   * @param fileName 文件名.
   * @param productSkuQuery SKU查询对象
   */
  void exportCombination(String fileName, ProductSkuQuery productSkuQuery);

  int batchModifyByCode(Collection<? extends ProductSku> skus);

  /**
   * 推送WMS.
   *
   * @param sku sku
   */
  void noticeWms(ProductSku sku);

  /**
   * 根据productId查询skuId列表
   */
  List<Long> listSkuIdsByProductId(Long productId);

  /**
   * 获取单个完整信息，带sku
   * @param skuId
   */
  ProductSku getFullInfoById(Long skuId);

}