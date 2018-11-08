package com.greatonce.oms.dao.impl.product;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.product.ProductDao;
import com.greatonce.oms.domain.product.Product;
import com.greatonce.oms.query.product.ProductQuery;
import org.springframework.stereotype.Repository;

/**
 * Product <br/>
 * 商品
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Repository
public class ProductDaoImpl extends AbstractOmsDao<Product, ProductQuery> implements ProductDao {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.product.ProductMapper";
  }

  @Override
  public boolean exists(String productCode) {
    return getSqlSessionDecorator().selectOne(getStatement("exists"), productCode) != null;
  }

  @Override
  public Product getByCode(String productCode) {
    return getSqlSessionDecorator().selectOne(getStatement("getByCode"), productCode);
  }

  @Override
  public PageList<Product> listPage(ProductQuery productQuery, int page, int pageSize) {
    return getSqlSessionDecorator().selectList(getStatement("listPageExtend"), productQuery, page, pageSize);
  }

}