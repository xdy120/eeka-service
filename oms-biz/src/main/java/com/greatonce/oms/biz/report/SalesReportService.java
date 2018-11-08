package com.greatonce.oms.biz.report;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.bo.report.OrderMonthPageListBO;
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


public interface SalesReportService {

  PageList<SalesBO> listPage(SalesQuery salesFilter, int page, int pageSize);

  PageList<SalesBO> listPageDetail(SalesQuery salesFilter, int page, int pageSize);

  PageList<SalesStatisticsBO> listPageSalesStatistics(SalesStatisticsQuery salesStatisticsQuery,
      int page, int pageSize);

  PageList<SalesOrderDeliverBO> exportSalesDeliverQuery(
      SalesOrderDeliverQuery salesOrderDeliverQuery, int page,
      int pageSize);

  PageList<SalesOrderOosBO> salesOrderOosQuery(SalesOrderOosQuery salesOrderOosQuery, int page,
      int pageSize);

  PageList<SalesOrderDetailSellStatistics> listPageOrderDetailStatistics(SalesDetailSellStatisticsQuery query, int page, int pageSize);

  OrderMonthPageListBO listPageOrderMonthStatistics(SalesOrderMonthStatisticsQuery query, int page, int pageSize);

  void exportSales(String fileName, SalesQuery salesQuery);

  void exportSalesOrderDetail(String fileName, SalesQuery salesQuery);

  void exportSalesStatistics(String fileName, SalesStatisticsQuery salesStatisticsQuery);

  void exportSalesOrderOosStatistics(String fileName, SalesOrderOosQuery salesOrderOosQuery);

  void exportDeliverStatistics(String fileName,SalesOrderDeliverQuery salesOrderDeliverQuery);

  void exportOrderDetailStatistics(SalesDetailSellStatisticsQuery query,String fileName);

  void exportOrderDateStatistics(SalesOrderMonthStatisticsQuery query, String fileName);

  PageList<SalesBO> listSalesDeliveryForDay(SalesQuery query, int page, int pageSize);

  PageList<SalesOrderReturnBO> listSalesReturnForDay(SalesOrderReturnQuery query, int page, int pageSize);

  /**
   * 统计当天已发货的订单
   */
  void exportSalesDeliveryForDay(String fileName, SalesQuery query);

  /**
   * 统计当天退货数据，按拆包时间
   */
  void exportSalesReturnForDay(String fileName, SalesOrderReturnQuery query);

  PageList<SalesOrderReturnBO> listSalesRefundForDay(SalesOrderReturnQuery query, int page, int pageSize);

  /**
   * 统计当天退货数据(仅退款)，按申请时间
   */
  void exportSalesRefundForDay(String fileName, SalesOrderReturnQuery query);

  PageList<SalesBO> listSalesDropShippingForDay(SalesQuery query, int page, int pageSize);

  /**
   * 统计当天代发的订单
   */
  void exportSalesDropShippingForDay(String fileName, SalesQuery query);

  /**
   * 发货明细
   */
  PageList<SalesBO> salesDeliveryDetailQueryListPage(SalesQuery salesFilter, int page, int pageSize);
  /**
   * 退货明细
   */
  PageList<ReturnDetailBO> returnDetailQueryListPage(ReturnDetailQuery filter, int page, int pageSize);

  /**
   * 发货明细报表
   */
  void exportSalesDeliveryDetail(String fileName, SalesQuery salesQuery);
  /**
   * 退货明细报表
   */
  void exportReturnDetail(String fileName, ReturnDetailQuery returnDetailQuery);

  PageList<SalesOrderDetailSellStatistics> listPageB2CSellStatisticsQuery(SalesDetailSellStatisticsQuery query, int page, int pageSize);

  void exportB2CSellStatistics(SalesDetailSellStatisticsQuery query, String fileName);

  PageList<SalesOrderDetailSellStatistics> listPageB2CSellAndReturnStatisticsQuery(SalesDetailSellStatisticsQuery query, int page,
      int pageSize);

  void exportB2CSellAndReturnStatistics(SalesDetailSellStatisticsQuery query, String fileName);
}