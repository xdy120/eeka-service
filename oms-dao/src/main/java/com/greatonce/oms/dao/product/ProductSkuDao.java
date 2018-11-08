package com.greatonce.oms.dao.product;

import com.greatonce.core.database.PageList;
import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.domain.product.ProductSku;
import com.greatonce.oms.query.product.ProductSkuQuery;
import java.util.Collection;
import java.util.List;

/**
 * ProductSku <br/> 商品规格
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface ProductSkuDao extends QueryDao<ProductSku, ProductSkuQuery> {

  /**
   * 根据商品ID修改信息 统一设置价格、重量、体积
   *
   * @param info 信息
   * @return 影响行数
   */
  int modifyByProductId(ProductSku info);

  PageList<ProductSku> listFullInfo(ProductSkuQuery filter, int page, int pageSize);

  List<ProductSku> listFullInfo(ProductSkuQuery filter);

  boolean exists(String skuCode);

  /**
   * 根据skuCode   skuCode or  barcode   enable=true
   *
   * @return 返回一行数据
   */
  ProductSku getBySkuCodeOrBarcode(String skuCode);

  /**
   * 根据skuCode和productCode修改
   */
  int updateByCode(Collection<? extends ProductSku> list);


  /**
   * 根据商品Id返回规格Id列表.
   */
  List<Long> listSkuIdsByProductId(Long productId);

}