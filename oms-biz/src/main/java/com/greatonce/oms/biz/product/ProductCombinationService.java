package com.greatonce.oms.biz.product;

import com.greatonce.oms.biz.BatchBizService;
import com.greatonce.oms.domain.product.ProductCombination;
import com.greatonce.oms.query.product.ProductCombinationQuery;
import java.util.List;
import java.util.Map;

/**
 * ProductCombination <br/>
 * 组合商品明细
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface ProductCombinationService extends
    BatchBizService<ProductCombination, ProductCombinationQuery> {

  List<ProductCombination> listByCombination(Long combinationId);

  Map<Long, List<ProductCombination>> listMainDetail();

  boolean exists(String skuCode);

  void removeByCombinationId(Long skuId);

  void setMainSku(ProductCombination productCombination);
}