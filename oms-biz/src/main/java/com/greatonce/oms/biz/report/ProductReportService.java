package com.greatonce.oms.biz.report;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.bo.report.ProductReturnBO;
import com.greatonce.oms.bo.report.ProductSalesBO;
import com.greatonce.oms.query.report.ProductReturnQuery;
import com.greatonce.oms.query.report.ProductSalesQuery;


public interface ProductReportService {

    /**
     * 商品退货报表
     * @param productReturnQuery
     * @param page
     * @param pageSize
     * @return
     */
    PageList<ProductReturnBO> exportProductReturnList(ProductReturnQuery productReturnQuery, int page, int pageSize);

    /**
     * 商品销售报表
     * @param productSalesQuery
     * @param page
     * @param pageSize
     * @return
     */
    PageList<ProductSalesBO> exportProductSalesList(ProductSalesQuery productSalesQuery, int page, int pageSize);

    void exportReturn(String fileName,ProductReturnQuery productReturnQuery);

    void exportSales(String fileName, ProductSalesQuery productSalesQuery);
}