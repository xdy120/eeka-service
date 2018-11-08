package com.greatonce.oms.dao.report;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.bo.report.VipDeliverBO;
import com.greatonce.oms.bo.report.VipReturnBO;
import com.greatonce.oms.bo.report.VipSalesBO;
import com.greatonce.oms.bo.report.VipSalesStatisticsBO;
import com.greatonce.oms.query.report.VipDeliverQuery;
import com.greatonce.oms.query.report.VipReturnRptQuery;
import com.greatonce.oms.query.report.VipSalesMonthStatisticsQuery;
import com.greatonce.oms.query.report.VipSalesQuery;
import com.greatonce.oms.query.report.VipSalesStatisticsQuery;

import java.util.Map;

/**
 * Stock <br/>
 * 库存
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface VipSalesReportDao {

    PageList<VipDeliverBO> exportVipDeliverList(VipDeliverQuery vipDeliverQuery, int page, int pageSize);

    PageList<VipReturnBO> exportVipReturnList(VipReturnRptQuery vipReturnQuery, int page, int pageSize);

    PageList<VipSalesBO> exportVipSalesList(VipSalesQuery vipSalesQuery, int page, int pageSize);

    PageList<VipSalesStatisticsBO> vipSalesStatisticsList(VipSalesStatisticsQuery query, int page, int pageSize);

    PageList<Map> listPageOrderMonthtatistics(VipSalesMonthStatisticsQuery query, int page, int pageSize);

    PageList<Map> listPageOrderYeartatistics(VipSalesMonthStatisticsQuery query, int page, int pageSize);

    PageList<VipReturnBO> vipReturnDetailQueryListPage(VipReturnRptQuery vipReturnRptQuery, int page, int pageSize);

    PageList<VipDeliverBO> vipSalesDeliveryDetailQueryListPage(VipDeliverQuery vipDeliverQuery, int page,
        int pageSize);

    PageList<VipSalesStatisticsBO> B2BSellStatisticsQuery(VipSalesStatisticsQuery query, int page, int pageSize);
}