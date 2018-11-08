package com.greatonce.oms.dao.impl.report;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.bo.report.VipDeliverBO;
import com.greatonce.oms.bo.report.VipReturnBO;
import com.greatonce.oms.bo.report.VipSalesBO;
import com.greatonce.oms.bo.report.VipSalesStatisticsBO;
import com.greatonce.oms.dao.impl.AbstractReportDao;
import com.greatonce.oms.dao.report.VipSalesReportDao;
import com.greatonce.oms.query.report.VipDeliverQuery;
import com.greatonce.oms.query.report.VipReturnRptQuery;
import com.greatonce.oms.query.report.VipSalesMonthStatisticsQuery;
import com.greatonce.oms.query.report.VipSalesQuery;
import com.greatonce.oms.query.report.VipSalesStatisticsQuery;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Stock <br/>
 * 库存
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Repository
public class VipSalesReportDaoImpl extends AbstractReportDao implements VipSalesReportDao {


    /**
     * 获取XML名称
     */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.report.VipSalesOrderDetailMapper";
    }

    @Override
    public PageList<VipDeliverBO> exportVipDeliverList(VipDeliverQuery vipDeliverQuery, int page, int pageSize) {
        return listPage("exportVipDeliverList", vipDeliverQuery, page, pageSize);
    }

    @Override
    public PageList<VipReturnBO> exportVipReturnList(VipReturnRptQuery vipReturnQuery, int page, int pageSize) {
        return listPage("exportVipReturnList", vipReturnQuery, page, pageSize);
    }

    @Override
    public PageList<VipSalesBO> exportVipSalesList(VipSalesQuery vipSalesQuery, int page, int pageSize) {
        return listPage("exportVipSalesList", vipSalesQuery, page, pageSize);
    }

    @Override
    public PageList<VipSalesStatisticsBO> vipSalesStatisticsList(VipSalesStatisticsQuery query,
        int page, int pageSize) {
        return listPage("vipSalesStatisticsList", query, page, pageSize);
    }

    @Override
    public PageList<Map> listPageOrderMonthtatistics(VipSalesMonthStatisticsQuery query, int page,
        int pageSize) {
        return listPage("vipSalesStatisticsForMonth", query, page, pageSize);
    }

    @Override
    public PageList<Map> listPageOrderYeartatistics(VipSalesMonthStatisticsQuery query, int page,
        int pageSize) {
        return listPage("vipSalesStatisticsForYear", query, page, pageSize);
    }

    @Override
    public PageList<VipReturnBO> vipReturnDetailQueryListPage(VipReturnRptQuery  query,
        int page, int pageSize) {
        return listPage("vipReturnDetailQueryListPage", query, page, pageSize);
    }

    @Override
    public PageList<VipDeliverBO> vipSalesDeliveryDetailQueryListPage(VipDeliverQuery query,
        int page, int pageSize) {
        return listPage("vipSalesDeliveryDetailQueryListPage", query, page, pageSize);
    }

    @Override
    public PageList<VipSalesStatisticsBO> B2BSellStatisticsQuery(VipSalesStatisticsQuery query,
        int page, int pageSize) {
        return listPage("B2BSellStatisticsQueryListPage", query, page, pageSize);
    }


}