package com.greatonce.oms.biz.report;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.bo.report.VipDeliverBO;
import com.greatonce.oms.bo.report.VipOrderMonthPageListBO;
import com.greatonce.oms.bo.report.VipReturnBO;
import com.greatonce.oms.bo.report.VipSalesBO;
import com.greatonce.oms.bo.report.VipSalesStatisticsBO;
import com.greatonce.oms.query.report.VipDeliverQuery;
import com.greatonce.oms.query.report.VipReturnRptQuery;
import com.greatonce.oms.query.report.VipSalesMonthStatisticsQuery;
import com.greatonce.oms.query.report.VipSalesQuery;
import com.greatonce.oms.query.report.VipSalesStatisticsQuery;


public interface VipSalesReportService {

  /**
   * 唯品发货单报表
   */
  PageList<VipDeliverBO> exportVipDeliverList(VipDeliverQuery vipDeliverQuery, int page,
      int pageSize);


  /**
   * 唯品退货报表
   */
  PageList<VipReturnBO> exportVipReturnList(VipReturnRptQuery vipReturnQuery, int page,
      int pageSize);

  PageList<VipSalesBO> exportVipSalesList(VipSalesQuery vipSalesQuery, int page, int pageSize);

  PageList<VipSalesStatisticsBO> vipSalesStatisticsList(VipSalesStatisticsQuery query, int page, int pageSize);

  VipOrderMonthPageListBO listPageOrderMonthStatistics(VipSalesMonthStatisticsQuery query, int page, int pageSize);

  /**
   * 导出唯品退货统计报表
   */
  void exportVipReturn(String fileName, VipReturnRptQuery vipReturnRptQuery);

  /**
   * 导出唯品发货统计报表
   */
  void exportVipDelivery(String fileName, VipDeliverQuery vipDeliverQuery);

  /**
   * 导出唯品销售统计报表
   */
  void exportVipSales(String fileName, VipSalesQuery vipSalesQuery);

  void exportVipSalesStatistics(String fileName, VipSalesStatisticsQuery query);

  void exportOrderDateStatistics(VipSalesMonthStatisticsQuery query, String fileName);

  PageList<VipReturnBO> vipReturnDetailQueryListPage(VipReturnRptQuery vipReturnRptQuery, int page, int pageSize);

  PageList<VipDeliverBO> vipSalesDeliveryDetailQueryListPage(VipDeliverQuery vipDeliverQuery, int page, int pageSize);

  void exportVipSalesDeliveryDetail(VipDeliverQuery query, String fileName);

  void exportVipReturnDetail(VipReturnRptQuery query, String fileName);

  PageList<VipSalesStatisticsBO> B2BSellStatisticsQuery(VipSalesStatisticsQuery query, int page, int pageSize);

  void exportB2BSellStatistics(String fileName, VipSalesStatisticsQuery query);
}