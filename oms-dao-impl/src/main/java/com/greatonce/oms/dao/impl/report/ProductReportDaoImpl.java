package com.greatonce.oms.dao.impl.report;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.bo.report.ProductReturnBO;
import com.greatonce.oms.bo.report.ProductSalesBO;
import com.greatonce.oms.dao.impl.AbstractReportDao;
import com.greatonce.oms.dao.report.ProductReportDao;
import com.greatonce.oms.query.report.ProductReturnQuery;
import com.greatonce.oms.query.report.ProductSalesQuery;
import org.springframework.stereotype.Repository;

/**
 * Stock <br/>
 * 库存
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Repository
public class ProductReportDaoImpl extends AbstractReportDao implements ProductReportDao {


  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.report.ProductMapper";
  }

  @Override
  public PageList<ProductReturnBO> exportProductReturnList(ProductReturnQuery productReturnQuery,
      int page, int pageSize) {
    return getSqlSessionDecorator()
        .selectList(getStatement("exportProductReturnList"), productReturnQuery, page, pageSize);
  }

  @Override
  public PageList<ProductSalesBO> exportProductSalesList(ProductSalesQuery productSalesQuery,
      int page, int pageSize) {
    return getSqlSessionDecorator()
        .selectList(getStatement("exportProductSalesList"), productSalesQuery, page, pageSize);
  }

  @Override
  public PageList<ProductSalesBO> exportDeliveriedProductSalesList(
      ProductSalesQuery productSalesQuery, int page, int pageSize) {
    return getSqlSessionDecorator()
        .selectList(getStatement("exportDeliveriedProductSalesList"), productSalesQuery, page,
            pageSize);
  }
}