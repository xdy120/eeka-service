package com.greatonce.oms.dao.impl.product;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.product.ProductSkuDao;
import com.greatonce.oms.domain.product.ProductSku;
import com.greatonce.oms.query.product.ProductSkuQuery;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * ProductSku <br/> 商品规格
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Repository
public class ProductSkuDaoImpl extends AbstractOmsDao<ProductSku, ProductSkuQuery> implements
    ProductSkuDao {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.product.ProductSkuMapper";
  }

  @Override
  public int modifyByProductId(ProductSku info) {
    return getSqlSessionDecorator().update(getStatement("modifyByProductId"), info);
  }

  @Override
  public PageList<ProductSku> listFullInfo(ProductSkuQuery filter, int page, int pageSize) {
    return getSqlSessionDecorator()
        .selectList(getStatement("listPageFullInfo"), filter, page, pageSize);
  }

  @Override
  public List<ProductSku> listFullInfo(ProductSkuQuery filter) {
    return getSqlSessionDecorator().selectList(getStatement("listFullInfo"), filter);
  }

  @Override
  public boolean exists(String skuCode) {
    return getSqlSessionDecorator().selectOne(getStatement("exists"), skuCode) != null;
  }

  @Override
  public ProductSku getBySkuCodeOrBarcode(String skuCode) {
    return getSqlSessionDecorator().selectOne(getStatement("getBySkuCodeOrBarcode"), skuCode);
  }

  @Override
  public int updateByCode(Collection<? extends ProductSku> list) {
    return this.getSqlSessionDecorator().updateBatch(this.getStatement("updateByCode"), list);
  }

  @Override
  public List<Long> listSkuIdsByProductId(Long productId) {
    return getSqlSessionDecorator().selectList(getStatement("listSkuIdsByProductId"), productId);
  }

}