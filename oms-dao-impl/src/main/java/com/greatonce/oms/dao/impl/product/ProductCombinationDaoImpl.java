package com.greatonce.oms.dao.impl.product;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.product.ProductCombinationDao;
import com.greatonce.oms.domain.product.ProductCombination;
import com.greatonce.oms.query.product.ProductCombinationQuery;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * ProductCombination <br/>
 * 组合商品明细
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Repository
public class ProductCombinationDaoImpl extends
    AbstractOmsDao<ProductCombination, ProductCombinationQuery> implements ProductCombinationDao {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.product.ProductCombinationMapper";
  }

  @Override
  public boolean exists(String skuCode) {
    return getSqlSessionDecorator().selectOne(getStatement("exists"), skuCode) != null;
  }

  @Override
  public List<ProductCombination> listMainDetail() {
    return getSqlSessionDecorator().selectList("listMainDetail");
  }
}