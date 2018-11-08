package com.greatonce.oms.dao.report;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.bo.report.ReturnDetailBO;
import com.greatonce.oms.bo.report.SalesBO;
import com.greatonce.oms.bo.report.SalesOrderDeliverBO;
import com.greatonce.oms.bo.report.SalesOrderDetailSellStatistics;
import com.greatonce.oms.bo.report.SalesOrderOosBO;
import com.greatonce.oms.bo.report.SalesOrderReturnBO;
import com.greatonce.oms.bo.report.SalesStatisticsBO;
import com.greatonce.oms.query.report.ReturnDetailQuery;
import com.greatonce.oms.query.report.SalesDetailSellStatisticsQuery;
import com.greatonce.oms.query.report.SalesOrderDeliverQuery;
import com.greatonce.oms.query.report.SalesOrderMonthStatisticsQuery;
import com.greatonce.oms.query.report.SalesOrderOosQuery;
import com.greatonce.oms.query.report.SalesOrderReturnQuery;
import com.greatonce.oms.query.report.SalesQuery;
import com.greatonce.oms.query.report.SalesStatisticsQuery;
import java.util.Map;

/**
 * Stock <br/>
 * 库存
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface SalesReportDao {

  PageList<SalesBO> exportSalesList(SalesQuery salesFilter, int page, int pageSize);

  PageList<SalesBO> exportSalesDetailList(SalesQuery salesFilter, int page, int pageSize);

  PageList<SalesStatisticsBO> listPageSalesStatistics(SalesStatisticsQuery salesStatisticsQuery,
      int page, int pageSize);

  PageList<SalesOrderDeliverBO> listPageSalesDeliverStatistics(SalesOrderDeliverQuery salesOrderDeliverQuery, int page,
      int pageSize);

  PageList<SalesOrderOosBO> listPageSalesOosStatistics(SalesOrderOosQuery salesOrderOosQuery, int page, int pageSize);

  PageList<SalesOrderDetailSellStatistics> listPageOrderDetailSellStatistics(SalesDetailSellStatisticsQuery query, int page, int pageSize);

  PageList<Map> listPageOrderMonthtatistics(SalesOrderMonthStatisticsQuery query, int page, int pageSize);

  PageList<Map> listPageOrderYeartatistics(SalesOrderMonthStatisticsQuery query, int page, int pageSize);

  PageList<SalesBO> exportSalesDeliveryForDay(SalesQuery filter, int page, int pageSize);

  PageList<SalesBO> exportSalesDropShippingForDay(SalesQuery filter, int page, int pageSize);

  PageList<SalesOrderReturnBO> exportSalesReturnForDay(SalesOrderReturnQuery filter, int page, int pageSize);

  PageList<SalesOrderReturnBO> exportSalesRefundForDay(SalesOrderReturnQuery filter, int page, int pageSize);

  PageList<SalesBO> salesDeliveryDetailQueryListPage(SalesQuery salesFilter, int page, int pageSize);

  PageList<ReturnDetailBO> returnDetailQueryListPage(ReturnDetailQuery filter, int page, int pageSize);

  PageList<SalesOrderDetailSellStatistics> listPageB2CSellStatisticsQuery(SalesDetailSellStatisticsQuery query, int page, int pageSize);

  PageList<SalesOrderDetailSellStatistics> listPageB2CSellAndReturnStatisticsQuery(SalesDetailSellStatisticsQuery query, int page,
      int pageSize);
}