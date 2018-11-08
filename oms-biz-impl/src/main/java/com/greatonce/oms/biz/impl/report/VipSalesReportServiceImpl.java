package com.greatonce.oms.biz.impl.report;

import com.greatonce.core.database.PageList;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.CollectionUtil;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.biz.impl.MessageExporter;
import com.greatonce.oms.biz.report.VipSalesReportService;
import com.greatonce.oms.bo.report.VipDeliverBO;
import com.greatonce.oms.bo.report.VipOrderMonthPageListBO;
import com.greatonce.oms.bo.report.VipReturnBO;
import com.greatonce.oms.bo.report.VipSalesBO;
import com.greatonce.oms.bo.report.VipSalesStatisticsBO;
import com.greatonce.oms.dao.report.VipSalesReportDao;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.query.report.VipDeliverQuery;
import com.greatonce.oms.query.report.VipReturnRptQuery;
import com.greatonce.oms.query.report.VipSalesMonthStatisticsQuery;
import com.greatonce.oms.query.report.VipSalesQuery;
import com.greatonce.oms.query.report.VipSalesStatisticsQuery;
import com.greatonce.oms.util.FormatUtil;
import com.greatonce.oms.util.excel.ExcelHeaderCollection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Supplier <br/> 供应商
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class VipSalesReportServiceImpl implements VipSalesReportService {

  @Autowired
  private VipSalesReportDao dao;
  @Autowired
  private MessageExporter messageExporter;

  @Override
  public PageList<VipDeliverBO> exportVipDeliverList(VipDeliverQuery vipDeliverQuery, int page,
      int pageSize) {
    return dao.exportVipDeliverList(vipDeliverQuery, page, pageSize);
  }


  @Override
  public PageList<VipReturnBO> exportVipReturnList(VipReturnRptQuery vipReturnQuery, int page,
      int pageSize) {
    return dao.exportVipReturnList(vipReturnQuery, page, pageSize);
  }

  @Override
  public PageList<VipSalesBO> exportVipSalesList(VipSalesQuery vipSalesQuery, int page,
      int pageSize) {
    return dao.exportVipSalesList(vipSalesQuery, page, pageSize);
  }

  @Override
  public PageList<VipSalesStatisticsBO> vipSalesStatisticsList(VipSalesStatisticsQuery query,
      int page, int pageSize) {
    return dao.vipSalesStatisticsList(query, page, pageSize);
  }


  @Override
  public void exportVipReturn(String fileName, VipReturnRptQuery vipReturnRptQuery) {
    ExcelHeaderCollection<VipReturnBO> headers = new ExcelHeaderCollection<>();
    headers.add("店铺名称", x -> x.getStoreName());
    headers.add("商品编码", x -> x.getProductCode());
    headers.add("商品名称", x -> x.getProductName());
    headers.add("规格编码", x -> x.getSkuCode());
    headers.add("规格名称", x -> x.getSkuName());
    headers.add("退货数量", x -> FormatUtil.formatInteger(x.getQuantity()));
    headers.add("退货时间", x -> DateTimeUtil.format(x.getLastInTime()));
    messageExporter.exportExcel(this::exportVipReturnList, headers, vipReturnRptQuery, fileName);
  }

  @Override
  public void exportVipDelivery(String fileName, VipDeliverQuery vipDeliverQuery) {
    ExcelHeaderCollection<VipDeliverBO> headers = new ExcelHeaderCollection<>();
    headers.add("店铺名称", x -> x.getStoreName());
    headers.add("商品编码", x -> x.getProductCode());
    headers.add("商品名称", x -> x.getProductName());
    headers.add("规格编码", x -> x.getSkuCode());
    headers.add("规格名称", x -> x.getSkuName());
    headers.add("发货数量", x -> FormatUtil.formatInteger(x.getQuantity()));
    messageExporter.exportExcel(this::exportVipDeliverList, headers, vipDeliverQuery, fileName);
  }

  @Override
  public void exportVipSales(String fileName, VipSalesQuery vipSalesQuery) {
    ExcelHeaderCollection<VipSalesBO> headers = new ExcelHeaderCollection<>();
    headers.add("店铺名称", x -> x.getStoreName());
    headers.add("商品编码", x -> x.getProductCode());
    headers.add("商品名称", x -> x.getProductName());
    headers.add("规格编码", x -> x.getSkuCode());
    headers.add("规格名称", x -> x.getSkuName());
    headers.add("发货数量", x -> FormatUtil.formatInteger(x.getQuantity()));
    headers.add("销售金额", x -> FormatUtil.formatDouble(x.getSellingAmount()));
    headers.add("发货时间", x -> DateTimeUtil.format(x.getLastOutTime()));
    messageExporter.exportExcel(this::exportVipSalesList, headers, vipSalesQuery, fileName);
  }

  @Override
  public void exportVipSalesStatistics(String fileName, VipSalesStatisticsQuery query) {
    ExcelHeaderCollection<VipSalesStatisticsBO> headers = new ExcelHeaderCollection<>();
    headers.add("店铺名称", x -> x.getStoreName());
    headers.add("销售件数", x -> FormatUtil.formatInteger(x.getSalesQuantity()));
    headers.add("销售金额", x -> FormatUtil.formatDouble(x.getSalesAmount()));
    headers.add("退货件数", x -> FormatUtil.formatInteger(x.getReturnQuantity()));
    headers.add("退货金额", x -> FormatUtil.formatDouble(x.getReturnAmount()));
    headers.add("实际销售件数", x -> FormatUtil.formatInteger(x.getActualQuantity()));
    headers.add("实际销售金额", x -> FormatUtil.formatDouble(x.getActualAmount()));
    headers.add("销量退货率(%)", x -> FormatUtil.formatDouble(x.getReturnQuantityRatio()));
    headers.add("金额退货率(%)", x -> FormatUtil.formatDouble(x.getReturnAmountRatio()));
    messageExporter.exportExcel(this::vipSalesStatisticsList, headers, query, fileName);
  }

  @Override
  public VipOrderMonthPageListBO listPageOrderMonthStatistics(VipSalesMonthStatisticsQuery query,
      int page, int pageSize) {
    //处理查询的时间问题
    PageList<Map> pageList;
    if ("month".equals(query.getStatisticsType())) {
      //查询出来的分页数据
      pageList = dao.listPageOrderMonthtatistics(query, page, pageSize);
    } else if ("year".equals(query.getStatisticsType())) {
      pageList = dao.listPageOrderYeartatistics(query, page, pageSize);
    } else {
      pageList = null;
    }
    if (Assert.isNull(pageList)) {
      throw new OmsException("统计类型有误");
    }

    //返回的
    VipOrderMonthPageListBO returnPage = new VipOrderMonthPageListBO(pageList.getPageSize(),
        pageList.getPage(), pageList.getTotal());

    List<Map> datas = new ArrayList<>();

    if (!Assert.isEmpty(pageList.getData())) {
      List<Map> data = pageList.getData();

      List<Object> columnList = pageList.getData().stream().map(x -> x.get("salesDate")).sorted()
          .distinct().collect(Collectors.toList());
      returnPage.setColumns(columnList);

      Map<Object, List<Map>> pageData = CollectionUtil
          .listToMapList(data, x -> x.get("storeName"));
      Set<Map.Entry<Object, List<Map>>> entries = pageData.entrySet();
      for (Map.Entry<Object, List<Map>> entry : entries) {
        Object storeName = entry.getKey();
        List<Map> value = entry.getValue();
        Map<Object, Object> item = new HashMap<>();
        item.put("storeName", storeName);

        //处理后面的月份
        for (Map map : value) {
          item.put(map.get("salesDate"), map.get("amount"));
        }
        datas.add(item);
      }
      returnPage.setData(datas);
      returnPage.setTotal(datas.size());
      return returnPage;
    }
    return null;
  }

  @Override
  public void exportOrderDateStatistics(VipSalesMonthStatisticsQuery query, String fileName) {
    VipOrderMonthPageListBO pageListBO = listPageOrderMonthStatistics(query, 1, 500);
    List<Map> list = pageListBO.getData();
    List columns = pageListBO.getColumns();
    String type;
    if ("month".equals(query.getStatisticsType())) {
      type = "月";
    } else if ("year".equals(query.getStatisticsType())) {
      type = "年";
    } else {
      type = "";
    }
    ExcelHeaderCollection<Map> headers = new ExcelHeaderCollection<>();
    headers.add("店铺名称", x -> x.get("storeName").toString());
    columns.forEach(x -> headers.add(x + type,
        y -> Assert.isNull(y.get(x.toString())) ? "" : y.get(x.toString()).toString()));
    messageExporter.exportExcel(headers, list, fileName);
  }

  @Override
  public PageList<VipReturnBO> vipReturnDetailQueryListPage(VipReturnRptQuery vipReturnRptQuery,
      int page, int pageSize) {
    return dao.vipReturnDetailQueryListPage(vipReturnRptQuery, page, pageSize);
  }

  @Override
  public PageList<VipDeliverBO> vipSalesDeliveryDetailQueryListPage(VipDeliverQuery vipDeliverQuery,
      int page, int pageSize) {
    return dao.vipSalesDeliveryDetailQueryListPage(vipDeliverQuery, page, pageSize);
  }

  @Override
  public void exportVipSalesDeliveryDetail(VipDeliverQuery query, String fileName) {
    ExcelHeaderCollection<VipDeliverBO> headers = new ExcelHeaderCollection<>();
    headers.add("店铺名称", x -> x.getStoreName());
    headers.add("发货单号", x -> x.getVipDispatchCode());
    headers.add("规格编码", x -> x.getSkuCode());
    headers.add("金额", x -> FormatUtil.formatDouble(x.getSettlementAmount()));
    headers.add("通知数量", x -> FormatUtil.formatInteger(x.getNoticeQuantity()));
    headers.add("出库数量", x -> FormatUtil.formatInteger(x.getOutQuantity()));
    headers.add("出库时间", x -> DateTimeUtil.format(x.getLastOutTime()));
    messageExporter.exportExcel(this::vipSalesDeliveryDetailQueryListPage, headers, query, fileName);
  }

  @Override
  public void exportVipReturnDetail(VipReturnRptQuery query, String fileName) {
    ExcelHeaderCollection<VipReturnBO> headers = new ExcelHeaderCollection<>();
    headers.add("店铺名称", x -> x.getStoreName());
    headers.add("退货单号", x -> x.getVipReturnNoticeCode());
    headers.add("规格编码", x -> x.getSkuCode());
    headers.add("金额", x -> FormatUtil.formatDouble(x.getRefundableAmount()));
    headers.add("通知数量", x -> FormatUtil.formatInteger(x.getNoticeQuantity()));
    headers.add("入库数量", x -> FormatUtil.formatInteger(x.getInQuantity()));
    headers.add("入库时间", x -> DateTimeUtil.format(x.getLastInTime()));
    messageExporter.exportExcel(this::vipReturnDetailQueryListPage, headers, query, fileName);
  }

  @Override
  public PageList<VipSalesStatisticsBO> B2BSellStatisticsQuery(VipSalesStatisticsQuery query,
      int page, int pageSize) {
    return dao.B2BSellStatisticsQuery(query, page, pageSize);
  }

  @Override
  public void exportB2BSellStatistics(String fileName, VipSalesStatisticsQuery query) {
    ExcelHeaderCollection<VipSalesStatisticsBO> headers = new ExcelHeaderCollection<>();
    headers.add("店铺名称", x -> x.getStoreName());
    headers.add("销售件数", x -> FormatUtil.formatInteger(x.getSalesQuantity()));
    headers.add("销售金额", x -> FormatUtil.formatDouble(x.getSalesAmount()));
    headers.add("退货件数", x -> FormatUtil.formatInteger(x.getReturnQuantity()));
    headers.add("退货金额", x -> FormatUtil.formatDouble(x.getReturnAmount()));
    headers.add("实际销售件数", x -> FormatUtil.formatInteger(x.getActualQuantity()));
    headers.add("实际销售金额", x -> FormatUtil.formatDouble(x.getActualAmount()));
    headers.add("销量退货率(%)", x -> FormatUtil.formatDouble(x.getReturnQuantityRatio()));
    headers.add("金额退货率(%)", x -> FormatUtil.formatDouble(x.getReturnAmountRatio()));
    messageExporter.exportExcel(this::B2BSellStatisticsQuery, headers, query, fileName);
  }
}