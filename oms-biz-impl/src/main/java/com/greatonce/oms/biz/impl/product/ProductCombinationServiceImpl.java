package com.greatonce.oms.biz.impl.product;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.biz.product.ProductCombinationService;
import com.greatonce.oms.dao.product.ProductCombinationDao;
import com.greatonce.oms.domain.product.ProductCombination;
import com.greatonce.oms.query.product.ProductCombinationQuery;
import com.greatonce.oms.util.CacheableNotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
@Service
public class ProductCombinationServiceImpl extends
    AbstractService<ProductCombination, ProductCombinationQuery> implements
    ProductCombinationService {

  @Autowired
  private ProductCombinationDao dao;

  @Override
  protected QueryDao<ProductCombination, ProductCombinationQuery> getDAO() {
    return this.dao;
  }

  @Override
  protected void initDefaultValue(ProductCombination entity) {
    super.initDefaultValue(entity);
    if (entity.isMainSku() == null) {
      entity.setMainSku(false);
    }
  }

  @Override
  public List<ProductCombination> listByCombination(Long combinationId) {
    ProductCombination eg = new ProductCombination();
    eg.setCombinationId(combinationId);
    return listExample(eg);
  }

  @Override
  public boolean exists(String skuCode) {
    return dao.exists(skuCode);
  }

  @Override
  public void removeByCombinationId(Long skuId) {
    ProductCombination eg = new ProductCombination();
    eg.setCombinationId(skuId);
    deleteByExample(eg);
  }

  @Override
  @CacheEvict(value = "PRODUCT_COMBO", key = "'SKU_COMBO_MAIN_DETAIL'")
  public void setMainSku(ProductCombination productCombination) {
    //将套装的明细都设为不是主商品
    ProductCombination record = new ProductCombination();
    ProductCombination example = new ProductCombination();
    record.setMainSku(false);
    example.setCombinationId(productCombination.getCombinationId());
    //设置这个为主商品
    ProductCombination eg = new ProductCombination();
    eg.setCombinationDetailId(productCombination.getCombinationDetailId());
    eg.setMainSku(true);
    getTransactionTemplate().execute(()->{
      updateByExample(record,example);
      modify(eg);
    });
  }

  @Override
  @CacheableNotNull(value = "PRODUCT_COMBO", key = "'SKU_COMBO_MAIN_DETAIL'")
  public Map<Long, List<ProductCombination>> listMainDetail() {
    List<ProductCombination> list = dao.listMainDetail();
    Map<Long, List<ProductCombination>> map = new HashMap<>();
    list.forEach(detail -> {
      if (!map.containsKey(detail.getSkuId())) {
        map.put(detail.getSkuId(), new ArrayList<>());
      }
      map.get(detail.getSkuId()).add(detail);
    });
    return map;
  }

  @Override
  @CacheEvict(value = "PRODUCT_COMBO", key = "'SKU_COMBO_MAIN_DETAIL'")
  public int batchCreate(Collection<? extends ProductCombination> collection) {
    collection.forEach(this::initDefaultValue);
    return getTransactionTemplate().executeWithResult(() -> insertBatch(collection));
  }

  @Override
  @CacheEvict(value = "PRODUCT_COMBO", key = "'SKU_COMBO_MAIN_DETAIL'")
  public int batchModify(Collection<? extends ProductCombination> collection) {
    return updateBatch(collection);
  }
}