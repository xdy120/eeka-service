package com.greatonce.oms.dao.impl.product;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.product.ProductMallMappingDao;
import com.greatonce.oms.domain.product.ProductMallMapping;
import com.greatonce.oms.query.product.ProductMallMappingQuery;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 * ProductMallMapping <br/>
 * 铺货关系
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Repository
public class ProductMallMappingDaoImpl extends
    AbstractOmsDao<ProductMallMapping, ProductMallMappingQuery> implements ProductMallMappingDao {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.product.ProductMallMappingMapper";
  }

  @Override
  public List<ProductMallMapping> listFull(Map<String, Object> params) {
    return getSqlSessionDecorator().selectList(getStatement("listFull"), params);
  }

  @Override
  public List<ProductMallMapping> listStoreMapping(Long storeId) {
    return getSqlSessionDecorator().selectList(getStatement("listStoreMapping"), storeId);
  }

  @Override
  public void beginMarketing(ProductMallMapping mapping) {
    getSqlSessionDecorator().update(getStatement("beginMarketing"), mapping);
  }

  @Override
  public void endMarketing(ProductMallMapping mapping) {
    getSqlSessionDecorator().update(getStatement("endMarketing"), mapping);
  }

  @Override
  public List<ProductMallMapping> listAutoUploadMapping(Long storeId) {
    return getSqlSessionDecorator().selectList(getStatement("listAutoUploadMapping"), storeId);
  }
}