package com.greatonce.oms.dao.report;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.bo.report.ProductReturnBO;
import com.greatonce.oms.bo.report.ProductSalesBO;
import com.greatonce.oms.query.report.ProductReturnQuery;
import com.greatonce.oms.query.report.ProductSalesQuery;

/**
 * Stock <br/>
 * 库存
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface ProductReportDao {

  PageList<ProductReturnBO> exportProductReturnList(ProductReturnQuery productReturnQuery, int page,
      int pageSize);

  PageList<ProductSalesBO> exportProductSalesList(ProductSalesQuery productSalesQuery, int page,
      int pageSize);

  PageList<ProductSalesBO> exportDeliveriedProductSalesList(ProductSalesQuery productSalesQuery,
      int page, int pageSize);
}