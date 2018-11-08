package com.greatonce.oms.dao.impl.report;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.bo.report.ReturnDetailBO;
import com.greatonce.oms.bo.report.SalesBO;
import com.greatonce.oms.bo.report.SalesOrderDeliverBO;
import com.greatonce.oms.bo.report.SalesOrderDetailSellStatistics;
import com.greatonce.oms.bo.report.SalesOrderOosBO;
import com.greatonce.oms.bo.report.SalesOrderReturnBO;
import com.greatonce.oms.bo.report.SalesStatisticsBO;
import com.greatonce.oms.dao.impl.AbstractReportDao;
import com.greatonce.oms.dao.report.SalesReportDao;
import com.greatonce.oms.query.report.ReturnDetailQuery;
import com.greatonce.oms.query.report.SalesDetailSellStatisticsQuery;
import com.greatonce.oms.query.report.SalesOrderDeliverQuery;
import com.greatonce.oms.query.report.SalesOrderMonthStatisticsQuery;
import com.greatonce.oms.query.report.SalesOrderOosQuery;
import com.greatonce.oms.query.report.SalesOrderReturnQuery;
import com.greatonce.oms.query.report.SalesQuery;
import com.greatonce.oms.query.report.SalesStatisticsQuery;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 * Stock <br/> 库存
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Repository
public class SalesReportDaoImpl extends AbstractReportDao implements SalesReportDao {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.report.SalesOrderMapper";
  }

  @Override
  public PageList<SalesBO> exportSalesList(SalesQuery salesFilter, int page, int pageSize) {
    return listPage("exportSalesList", salesFilter, page, pageSize);
  }

  @Override
  public PageList<SalesBO> exportSalesDetailList(SalesQuery salesFilter, int page, int pageSize) {
    return listPage("exportSalesDetailList", salesFilter, page, pageSize);
  }

  @Override
  public PageList<SalesStatisticsBO> listPageSalesStatistics(
      SalesStatisticsQuery salesStatisticsQuery, int page, int pageSize) {
    return listPage("exportSalesStatisticsList", salesStatisticsQuery, page, pageSize);
  }

  @Override
  public PageList<SalesOrderDeliverBO> listPageSalesDeliverStatistics(
      SalesOrderDeliverQuery salesOrderDeliverQuery, int page, int pageSize) {
    return listPage("salesOrderDeliverStatisticsList", salesOrderDeliverQuery, page, pageSize);
  }

  @Override
  public PageList<SalesOrderOosBO> listPageSalesOosStatistics(SalesOrderOosQuery salesOrderOosQuery,
      int page, int pageSize) {
    return listPage("salesOrderOosStatisticsList", salesOrderOosQuery, page, pageSize);
  }

  @Override
  public PageList<SalesOrderDetailSellStatistics> listPageOrderDetailSellStatistics(
      SalesDetailSellStatisticsQuery query, int page, int pageSize) {
    return listPage("SellStatisticsForSalesOrderDetail", query, page, pageSize);
  }

  @Override
  public PageList<Map> listPageOrderMonthtatistics(SalesOrderMonthStatisticsQuery query, int page,
      int pageSize) {
    return listPage("SellStatisticsForMonth", query, page, pageSize);
  }

  @Override
  public PageList<Map> listPageOrderYeartatistics(SalesOrderMonthStatisticsQuery query, int page,
      int pageSize) {
    return listPage("SellStatisticsForYear", query, page, pageSize);
  }

  @Override
  public PageList<SalesBO> exportSalesDeliveryForDay(SalesQuery filter, int page, int pageSize) {
    return listPage("exportSalesDeliveryForDay", filter, page, pageSize);
  }

  @Override
  public PageList<SalesBO> exportSalesDropShippingForDay(SalesQuery filter, int page,
      int pageSize) {
    return listPage("exportSalesDropShippingForDay", filter, page, pageSize);
  }

  @Override
  public PageList<SalesOrderReturnBO> exportSalesReturnForDay(SalesOrderReturnQuery filter, int page, int pageSize) {
    return listPage("exportSalesReturnForDay", filter, page, pageSize);
  }

  @Override
  public PageList<SalesOrderReturnBO> exportSalesRefundForDay(SalesOrderReturnQuery filter,
      int page, int pageSize) {
    return listPage("exportSalesRefundForDay", filter, page, pageSize);
  }

  @Override
  public PageList<SalesBO> salesDeliveryDetailQueryListPage(SalesQuery filter, int page,
      int pageSize) {
    return listPage("salesDeliveryDetailQueryListPage", filter, page, pageSize);
  }

  @Override
  public PageList<ReturnDetailBO> returnDetailQueryListPage(ReturnDetailQuery filter, int page,
      int pageSize) {
    return listPage("returnDetailQueryListPage", filter, page, pageSize);
  }

  @Override
  public PageList<SalesOrderDetailSellStatistics> listPageB2CSellStatisticsQuery(
      SalesDetailSellStatisticsQuery filter, int page, int pageSize) {
    return listPage("B2CSellStatisticsQuery", filter, page, pageSize);
  }

  @Override
  public PageList<SalesOrderDetailSellStatistics> listPageB2CSellAndReturnStatisticsQuery(
      SalesDetailSellStatisticsQuery filter, int page, int pageSize) {
    return listPage("B2CSellAndReturnStatisticsQuery", filter, page, pageSize);
  }
}