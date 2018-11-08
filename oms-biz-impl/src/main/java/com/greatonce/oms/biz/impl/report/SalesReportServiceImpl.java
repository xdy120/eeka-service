package com.greatonce.oms.biz.impl.report;

import com.greatonce.core.database.PageList;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.CollectionUtil;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.biz.impl.MessageExporter;
import com.greatonce.oms.biz.report.SalesReportService;
import com.greatonce.oms.bo.report.OrderMonthPageListBO;
import com.greatonce.oms.bo.report.ReturnDetailBO;
import com.greatonce.oms.bo.report.SalesBO;
import com.greatonce.oms.bo.report.SalesOrderDeliverBO;
import com.greatonce.oms.bo.report.SalesOrderDetailSellStatistics;
import com.greatonce.oms.bo.report.SalesOrderOosBO;
import com.greatonce.oms.bo.report.SalesOrderReturnBO;
import com.greatonce.oms.bo.report.SalesStatisticsBO;
import com.greatonce.oms.dao.report.SalesReportDao;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.query.report.ReturnDetailQuery;
import com.greatonce.oms.query.report.SalesDetailSellStatisticsQuery;
import com.greatonce.oms.query.report.SalesOrderDeliverQuery;
import com.greatonce.oms.query.report.SalesOrderMonthStatisticsQuery;
import com.greatonce.oms.query.report.SalesOrderOosQuery;
import com.greatonce.oms.query.report.SalesOrderReturnQuery;
import com.greatonce.oms.query.report.SalesQuery;
import com.greatonce.oms.query.report.SalesStatisticsQuery;
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
public class SalesReportServiceImpl implements SalesReportService {

  @Autowired
  private SalesReportDao salesReportDao;
  @Autowired
  private MessageExporter messageExporter;


  @Override
  public PageList<SalesBO> listPage(SalesQuery salesFilter, int page, int pageSize) {
    return salesReportDao.exportSalesList(salesFilter, page, pageSize);
  }

  @Override
  public PageList<SalesBO> listPageDetail(SalesQuery salesFilter, int page, int pageSize) {
    return salesReportDao.exportSalesDetailList(salesFilter, page, pageSize);
  }

  @Override
  public PageList<SalesStatisticsBO> listPageSalesStatistics(
      SalesStatisticsQuery salesStatisticsQuery, int page, int pageSize) {
    return salesReportDao.listPageSalesStatistics(salesStatisticsQuery, page, pageSize);
  }

  @Override
  public PageList<SalesOrderDeliverBO> exportSalesDeliverQuery(
      SalesOrderDeliverQuery salesOrderDeliverQuery, int page, int pageSize) {
    return salesReportDao.listPageSalesDeliverStatistics(salesOrderDeliverQuery, page, pageSize);
  }

  @Override
  public PageList<SalesOrderOosBO> salesOrderOosQuery(SalesOrderOosQuery salesOrderOosQuery,
      int page, int pageSize) {
    return salesReportDao.listPageSalesOosStatistics(salesOrderOosQuery, page, pageSize);
  }

  @Override
  public PageList<SalesOrderDetailSellStatistics> listPageOrderDetailStatistics(SalesDetailSellStatisticsQuery query,int page, int pageSize) {
    return salesReportDao.listPageOrderDetailSellStatistics(query,page,pageSize);
  }

  @Override
  public OrderMonthPageListBO listPageOrderMonthStatistics(SalesOrderMonthStatisticsQuery query, int page,
      int pageSize) {
    //处理查询的时间问题
    PageList<Map> pageList;
    if ("month".equals(query.getStatisticsType())){
      //查询出来的分页数据
      pageList = salesReportDao.listPageOrderMonthtatistics(query,page,pageSize);
    }else if ("year".equals(query.getStatisticsType())){
      pageList = salesReportDao.listPageOrderYeartatistics(query, page, pageSize);
    }else {
      pageList = null;
    }
    if (Assert.isNull(pageList)){
      throw new OmsException("统计类型有误");
    }

    //返回的
    OrderMonthPageListBO returnPage = new OrderMonthPageListBO(pageList.getPageSize(),
        pageList.getPage(), pageList.getTotal());

    List<Map> datas = new ArrayList<>();

    if (!Assert.isEmpty(pageList.getData())){
      List<Map> data = pageList.getData();

      //找出有多少列
      List<Object> columnList = pageList.getData().stream().map(x -> x.get("salesDate")).sorted()
          .distinct().collect(Collectors.toList());
      returnPage.setColumns(columnList);

//      有多少条记录
      Map<Object, List<Map>> pageData = CollectionUtil
          .listToMapList(data, x -> x.get("storeName"));
      Set<Map.Entry<Object, List<Map>>> entries = pageData.entrySet();
      for (Map.Entry<Object, List<Map>> entry : entries) {
        Object storeName = entry.getKey();
        List<Map> value = entry.getValue();
        Map<Object, Object> item = new HashMap<>();
        item.put("storeName",storeName);

        //处理后面的月份
        for (Map map : value) {
          item.put(map.get("salesDate"),map.get("amount"));
        }
        datas.add(item);
      }
      returnPage.setData(datas);
      returnPage.setTotal(datas.size());
      return returnPage;
    }
    return null;
  }

  /**
   * B2C订单列表导出
   */
  @Override
  public void exportSales(String fileName, SalesQuery salesQuery) {
    ExcelHeaderCollection<SalesBO> headers = new ExcelHeaderCollection<>();
    headers.add("订单号", x -> x.getSalesOrderCode());
    headers.add("外部交易号", x -> x.getTradeId());
    headers.add("付款时间", x -> DateTimeUtil.format(x.getPaidTime()));
    headers.add("配货状态", x -> x.getDispatchStatus().caption());
    headers.add("发货状态", x -> x.getDispatchStatus().caption());
    headers.add("预售状态", x -> x.getPresellType().caption());
    headers.add("店铺", x -> x.getStoreName());
    headers.add("数量", x -> FormatUtil.formatInteger(x.getQuantity()));
    headers.add("顾客实付金额", x -> FormatUtil.formatDouble(x.getActualAmount()));
    headers.add("均摊金额", x -> FormatUtil.formatDouble(x.getSettlementAmount()));
    headers.add("省份", x -> x.getProvinceName());
    headers.add("市", x -> x.getCityName());
    headers.add("县", x -> x.getDistrictName());
//    headers.add("地址", x -> x.getAddress());
//    headers.add("收货人", x -> x.getContact());
//    headers.add("联系方式", x -> x.getMobile());
    headers.add("快递名称", x -> x.getLastExpressName());
    headers.add("快递单号", x -> x.getLastExpressNo());
    headers.add("昵称", x -> x.getMemberName());
    headers.add("下单时间", x -> DateTimeUtil.format(x.getMallCreatedTime()));
    headers.add("补发原因", x -> x.getReissueReason());
    messageExporter.exportExcel(this::listPage, headers, salesQuery, fileName);
  }

  /**
   * B2C订单明细导出
   */
  @Override
  public void exportSalesOrderDetail(String fileName, SalesQuery salesQuery) {
    ExcelHeaderCollection<SalesBO> headers = new ExcelHeaderCollection<>();
    headers.add("订单号", x -> x.getSalesOrderCode());
    headers.add("外部交易号", x -> x.getTradeId());
    headers.add("商品编码", x -> x.getProductCode());
    headers.add("商品名称", x -> x.getProductName());
    headers.add("规格编码", x -> x.getSkuCode());
    headers.add("规格名称", x -> x.getSkuName());
    headers.add("颜色", x -> x.getColor());
    headers.add("尺码", x -> x.getSize());
    headers.add("付款时间", x -> DateTimeUtil.format(x.getPaidTime()));
    headers.add("配货状态", x -> x.getDispatchStatus().caption());
    headers.add("发货状态", x -> x.getDispatchStatus().caption());
    headers.add("预售状态", x -> x.getPresellType().caption());
    headers.add("退款状态",x-> Assert.isNull(x.getSalesOrderDetailRefundStatus()) ?
        "" : x.getSalesOrderDetailRefundStatus().caption());
    headers.add("店铺", x -> x.getStoreName());
    headers.add("调整价", x -> FormatUtil.formatDouble(x.getSellingPrice()));
    headers.add("数量", x -> FormatUtil.formatInteger(x.getQuantity()));
    headers.add("顾客实付金额", x -> FormatUtil.formatDouble(x.getActualAmount()));
    headers.add("均摊金额", x -> FormatUtil.formatDouble(x.getSettlementAmount()));
    headers.add("省份", x -> x.getProvinceName());
    headers.add("市", x -> x.getCityName());
    headers.add("县", x -> x.getDistrictName());
    headers.add("收货人", x -> x.getContact());
    headers.add("联系方式", x -> x.getMobile());
    headers.add("地址", x -> x.getAddress());
    headers.add("昵称", x -> x.getMemberName());
    headers.add("快递名称", x -> x.getExpressName());
    headers.add("快递单号", x -> x.getExpressNo());
    headers.add("下单时间", x -> DateTimeUtil.format(x.getMallCreatedTime()));
    messageExporter.exportExcel(this::listPageDetail, headers, salesQuery, fileName);
  }

  /**
   * B2C销售单统计导出
   */
  @Override
  public void exportSalesStatistics(String fileName, SalesStatisticsQuery salesStatisticsQuery) {
    ExcelHeaderCollection<SalesStatisticsBO> headers = new ExcelHeaderCollection<>();
    headers.add("店铺名称", x -> x.getStoreName());
    headers.add("销售总数量", x -> FormatUtil.formatInteger(x.getQuantity()));
    headers.add("结算总金额", x -> FormatUtil.formatDouble(x.getSettlementAmountTotal()));
    headers.add("优惠总金额", x -> FormatUtil.formatDouble(x.getDiscounAmountTotal()));
    headers.add("退货数量", x -> FormatUtil.formatInteger(x.getReturnQuantity()));
    headers.add("退货金额", x -> FormatUtil.formatDouble(x.getReturnAmountTotal()));
    headers.add("退货率", x -> FormatUtil.formatDouble(x.getRefundRate()));
    messageExporter
        .exportExcel(this::listPageSalesStatistics, headers, salesStatisticsQuery, fileName);
  }

  /**
   * B2C缺货单统计导出
   */
  @Override
  public void exportSalesOrderOosStatistics(String fileName,
      SalesOrderOosQuery salesOrderOosQuery) {
    ExcelHeaderCollection<SalesOrderOosBO> headers = new ExcelHeaderCollection<>();
    headers.add("店铺名称", x -> x.getStoreName());
    headers.add("缺货订单数", x -> FormatUtil.formatInteger(x.getOrderQuantity()));
    headers.add("规格编码", x -> x.getSkuCode());
    headers.add("缺货商品件数", x -> FormatUtil.formatInteger(x.getProductQuantity()));
    headers.add("商品名称",x->x.getProductName());
    headers.add("规格名称",x->x.getSkuName());
    messageExporter.exportExcel(this::salesOrderOosQuery, headers, salesOrderOosQuery, fileName);
  }

  /**
   * B2C发货单统计导出
   */
  @Override
  public void exportDeliverStatistics(String fileName,
      SalesOrderDeliverQuery salesOrderDeliverQuery) {
    ExcelHeaderCollection<SalesOrderDeliverBO> headers = new ExcelHeaderCollection<>();
    headers.add("店铺名称", x -> x.getStoreName());
    headers.add("订单总数量", x -> FormatUtil.formatInteger(x.getOrderQuantityTotal()));
    headers.add("已发货订单", x -> FormatUtil.formatInteger(x.getOrderDeliveryTotal()));
    headers.add("未发货订单", x -> FormatUtil.formatInteger(x.getOrderNotDeliveryTotal()));
    headers.add("商品总数量", x -> FormatUtil.formatInteger(x.getProductQuantity()));
    headers.add("已发货商品", x -> FormatUtil.formatInteger(x.getDeliveryQuantity()));
    headers.add("未发货商品", x -> FormatUtil.formatInteger(x.getNotDeliveryQuantity()));
    headers.add("订单总金额", x -> FormatUtil.formatDouble(x.getOrderAmountTotal()));
    headers.add("已发货金额", x -> FormatUtil.formatDouble(x.getDeliveryAmountTotal()));
    headers.add("未发货金额", x -> FormatUtil.formatDouble(x.getNotDeliveryAmountTotal()));

    messageExporter
        .exportExcel(this::exportSalesDeliverQuery, headers, salesOrderDeliverQuery, fileName);
  }

  @Override
  public void exportOrderDetailStatistics(SalesDetailSellStatisticsQuery query,String fileName) {
    List<SalesOrderDetailSellStatistics> list = new ArrayList<>();
    PageList<SalesOrderDetailSellStatistics> b2cList = salesReportDao.listPageOrderDetailSellStatistics(query, 1, 500);
    if (!Assert.isEmpty(b2cList.getData())){
      list.addAll(b2cList.getData());
    }

    ExcelHeaderCollection<SalesOrderDetailSellStatistics> headers = new ExcelHeaderCollection<>();
    headers.add("店铺名称", x->x.getStoreName());
    headers.add("销售件数", x-> FormatUtil.formatInteger(x.getSalesQuantity()));
    headers.add("销售金额", x-> FormatUtil.formatDouble(x.getSalesAmount()));
    headers.add("退货件数", x-> FormatUtil.formatInteger(x.getReturnQuantity()));
    headers.add("退货金额", x-> FormatUtil.formatDouble(x.getReturnAmount()));
    headers.add("实际销售件数", x-> FormatUtil.formatInteger(x.getActualQuantity()));
    headers.add("实际销售金额", x->FormatUtil.formatDouble(x.getActualAmount()));
    headers.add("销量退货率(%)",x->FormatUtil.formatDouble(x.getReturnQuantityRatio()));
    headers.add("金额退货率(%)",x->FormatUtil.formatDouble(x.getReturnAmountRatio()));
    messageExporter.exportExcel(headers,list,fileName);
  }

  @Override
  public void exportOrderDateStatistics(SalesOrderMonthStatisticsQuery query, String fileName) {
    OrderMonthPageListBO pageListBO = listPageOrderMonthStatistics(query, 1, 500);
    List<Map> list = pageListBO.getData();
    List columns = pageListBO.getColumns();
    String type;
    if ("month".equals(query.getStatisticsType())){
      type = "月";
    }else if ("year".equals(query.getStatisticsType())){
      type = "年";
    }else {
      type = "";
    }
    ExcelHeaderCollection<Map> headers = new ExcelHeaderCollection<>();
    headers.add("店铺名称", x->x.get("storeName").toString());
    columns.forEach(x-> headers.add(x+type,y-> Assert.isNull(y.get(x.toString())) ? "" : y.get(x.toString()).toString()));
    messageExporter.exportExcel(headers,list,fileName);
  }

  @Override
  public PageList<SalesBO> listSalesDeliveryForDay(SalesQuery query, int page, int pageSize) {
    return salesReportDao.exportSalesDeliveryForDay(query, page, pageSize);
  }

  @Override
  public void exportSalesDeliveryForDay(String fileName, SalesQuery query) {
    ExcelHeaderCollection<SalesBO> headers = new ExcelHeaderCollection<>();
    headers.add("店铺名称", x -> x.getStoreName());
    headers.add("订单号", x -> x.getSalesOrderCode());
    headers.add("外部交易号", x -> x.getTradeId());
    headers.add("发货时间", x -> DateTimeUtil.format(x.getDeliveryTime()));
    headers.add("快递名称", x -> x.getExpressName());
    headers.add("快递单号", x -> x.getExpressNo());
    headers.add("昵称", x -> x.getMemberName());
    headers.add("发货仓库", x -> x.getWarehouseName());
    headers.add("商品编码", x -> x.getProductCode());
    headers.add("商品名称", x -> x.getProductName());
    headers.add("数量", x -> FormatUtil.formatInteger(x.getQuantity()));
    headers.add("销售价", x -> FormatUtil.formatDouble(x.getSellingPrice()));
    headers.add("销售金额", x -> FormatUtil.formatDouble(x.getSellingAmount()));
    headers.add("结算金额", x -> FormatUtil.formatDouble(x.getSettlementAmount()));
    headers.add("成本价", x -> FormatUtil.formatDouble(x.getCostPrice()));
    headers.add("总商品成本", x -> {
      if (Assert.isNull(x.getCostPrice()) || Assert.isNull(x.getQuantity())) {
        return "0.0000";
      }
      return FormatUtil.formatDouble(x.getCostPrice() * x.getQuantity());
    });
    headers.add("是否代发", x -> FormatUtil.formatBoolean(x.isDropShipping()));
    headers.add("订单类型", x -> x.getSalesOrderType().caption());
    messageExporter.exportExcel(this::listSalesDeliveryForDay, headers, query, fileName);
  }

  @Override
  public PageList<SalesOrderReturnBO> listSalesReturnForDay(SalesOrderReturnQuery query, int page,
      int pageSize) {
    return salesReportDao.exportSalesReturnForDay(query, page, pageSize);
  }

  @Override
  public void exportSalesReturnForDay(String fileName, SalesOrderReturnQuery query) {
    ExcelHeaderCollection<SalesOrderReturnBO> headers = new ExcelHeaderCollection<>();
    headers.add("店铺名称", x -> x.getStoreName());
    headers.add("退货单号", x -> x.getReturnOrderCode());
    headers.add("订单号", x -> x.getSalesOrderCode());
    headers.add("外部交易号", x -> x.getTradeId());
    headers.add("快递名称", x -> x.getExpressName());
    headers.add("快递单号", x -> x.getExpressNo());
    headers.add("昵称", x -> x.getMemberName());
    headers.add("退换类型", x -> x.getReturnType());
    headers.add("商品编码", x -> x.getProductCode());
    headers.add("商品名称", x -> x.getProductName());
    headers.add("成本价", x -> FormatUtil.formatDouble(x.getCostPrice()));
    headers.add("总商品成本", x -> {
      if (Assert.isNull(x.getCostPrice()) || Assert.isNull(x.getQuantity())) {
        return "0.0000";
      }
      return FormatUtil.formatDouble(x.getCostPrice() * x.getQuantity());
    });
    headers.add("数量", x -> FormatUtil.formatInteger(x.getQuantity()));
    headers.add("应退金额", x -> FormatUtil.formatDouble(x.getRefundableAmount()));
    headers.add("制单人", x -> x.getUnpacker());
    headers.add("制单日期", x -> DateTimeUtil.format(x.getUnpackedTime()));
    headers.add("售后申请状态", x -> {
      if (Assert.isTrue(x.getExchange())) {
        return Assert.isNull(x.getMallExchangeStatus()) ? "" : x.getMallExchangeStatus().caption();
      } else {
        return Assert.isNull(x.getMallRefundStatus()) ? "" : x.getMallRefundStatus().caption();
      }
    });
    messageExporter.exportExcel(this::listSalesReturnForDay, headers, query, fileName);
  }

  @Override
  public PageList<SalesOrderReturnBO> listSalesRefundForDay(SalesOrderReturnQuery query, int page,
      int pageSize) {
    return salesReportDao.exportSalesRefundForDay(query, page, pageSize);
  }

  @Override
  public void exportSalesRefundForDay(String fileName, SalesOrderReturnQuery query) {
    ExcelHeaderCollection<SalesOrderReturnBO> headers = new ExcelHeaderCollection<>();
    headers.add("店铺名称", x -> x.getStoreName());
    headers.add("订单号", x -> x.getSalesOrderCode());
    headers.add("外部交易号", x -> x.getTradeId());
    headers.add("昵称", x -> x.getMemberName());
    headers.add("退货类型", x -> Assert.isNull(x.getRefundApplyOrderType()) ?
        "" : x.getRefundApplyOrderType().caption());
    headers.add("商品编码", x -> x.getProductCode());
    headers.add("商品名称", x -> x.getProductName());
    headers.add("成本价", x -> FormatUtil.formatDouble(x.getCostPrice()));
    headers.add("总商品成本", x -> {
      if (Assert.isNull(x.getCostPrice()) || Assert.isNull(x.getQuantity())) {
        return "0.0000";
      }
      return FormatUtil.formatDouble(x.getCostPrice() * x.getQuantity());
    });
    headers.add("申请数量", x -> FormatUtil.formatInteger(x.getQuantity()));
    headers.add("申请金额", x -> FormatUtil.formatDouble(x.getApplyAmount()));
    headers.add("申请时间", x -> DateTimeUtil.format(x.getAppliedTime()));
    headers.add("售后申请状态", x -> x.getMallRefundStatus().caption());
    messageExporter.exportExcel(this::listSalesRefundForDay, headers, query, fileName);
  }

  @Override
  public PageList<SalesBO> listSalesDropShippingForDay(SalesQuery query, int page, int pageSize) {
    return salesReportDao.exportSalesDropShippingForDay(query, page, pageSize);
  }

  @Override
  public void exportSalesDropShippingForDay(String fileName, SalesQuery query) {
    ExcelHeaderCollection<SalesBO> headers = new ExcelHeaderCollection<>();
    headers.add("店铺名称", x -> x.getStoreName());
    headers.add("订单号", x -> x.getSalesOrderCode());
    headers.add("外部交易号", x -> x.getTradeId());
    headers.add("创建时间", x -> DateTimeUtil.format(x.getCreatedTime()));
    headers.add("昵称", x -> x.getMemberName());
    headers.add("商品编码", x -> x.getProductCode());
    headers.add("商品名称", x -> x.getProductName());
    headers.add("数量", x -> FormatUtil.formatInteger(x.getQuantity()));
    headers.add("销售价", x -> FormatUtil.formatDouble(x.getSellingPrice()));
    headers.add("销售金额", x -> FormatUtil.formatDouble(x.getSellingAmount()));
    headers.add("结算金额", x -> FormatUtil.formatDouble(x.getSettlementAmount()));
    headers.add("成本价", x -> FormatUtil.formatDouble(x.getCostPrice()));
    headers.add("总商品成本", x -> {
      if (Assert.isNull(x.getCostPrice()) || Assert.isNull(x.getQuantity())) {
        return "0.0000";
      }
      return FormatUtil.formatDouble(x.getCostPrice() * x.getQuantity());
    });
    headers.add("是否代发", x -> FormatUtil.formatBoolean(x.isDropShipping()));
    headers.add("订单类型", x -> x.getSalesOrderType().caption());
    messageExporter.exportExcel(this::listSalesDropShippingForDay, headers, query, fileName);
  }

  @Override
  public PageList<SalesBO> salesDeliveryDetailQueryListPage(SalesQuery salesFilter, int page,
      int pageSize) {
    return salesReportDao.salesDeliveryDetailQueryListPage(salesFilter,page,pageSize);
  }

  @Override
  public PageList<ReturnDetailBO> returnDetailQueryListPage(ReturnDetailQuery filter, int page,
      int pageSize) {
    return salesReportDao.returnDetailQueryListPage(filter,page,pageSize);
  }

  /**
   * B2C销售明细导出
   */
  @Override
  public void exportSalesDeliveryDetail(String fileName, SalesQuery salesQuery) {
    ExcelHeaderCollection<SalesBO> headers = new ExcelHeaderCollection<>();
    headers.add("店铺名称", x -> x.getStoreName());
    headers.add("发货单号", x -> x.getDispatchOrderCode());
    headers.add("规格编码", x -> x.getSkuCode());
    headers.add("金额", x -> FormatUtil.formatDouble(x.getSettlementAmount()));
    headers.add("通知数量", x -> FormatUtil.formatInteger(x.getNoticeQuantity()));
    headers.add("出库数量", x -> FormatUtil.formatInteger(x.getOutQuantity()));
    headers.add("快递公司", x -> x.getExpressName());
    headers.add("快递单号", x -> x.getExpressNo());
    headers.add("发货时间", x -> DateTimeUtil.format(x.getDeliveryTime()));
    messageExporter.exportExcel(this::salesDeliveryDetailQueryListPage, headers, salesQuery, fileName);
  }

  /**
   * B2C销售明细导出
   */
  @Override
  public void exportReturnDetail(String fileName, ReturnDetailQuery returnDetailQuery) {
    ExcelHeaderCollection<ReturnDetailBO> headers = new ExcelHeaderCollection<>();
    headers.add("店铺名称", x -> x.getStoreName());
    headers.add("退货单号", x -> x.getReturnOrderCode());
    headers.add("规格编码", x -> x.getSkuCode());
    headers.add("金额", x -> FormatUtil.formatDouble(x.getRefundableAmount()));
    headers.add("通知数量", x -> FormatUtil.formatInteger(x.getNoticeQuantity()));
    headers.add("入库数量", x -> FormatUtil.formatInteger(x.getInQuantity()));
    headers.add("快递公司", x -> x.getExpressName());
    headers.add("快递单号", x -> x.getExpressNo());
    headers.add("入库时间", x -> DateTimeUtil.format(x.getLastInTime()));
    messageExporter.exportExcel(this::returnDetailQueryListPage, headers, returnDetailQuery, fileName);
  }

  @Override
  public PageList<SalesOrderDetailSellStatistics> listPageB2CSellStatisticsQuery(
      SalesDetailSellStatisticsQuery query, int page, int pageSize) {
    return salesReportDao.listPageB2CSellStatisticsQuery(query,page,pageSize);
  }

  @Override
  public void exportB2CSellStatistics(SalesDetailSellStatisticsQuery query, String fileName) {
    ExcelHeaderCollection<SalesOrderDetailSellStatistics> headers = new ExcelHeaderCollection<>();
    headers.add("店铺名称", x->x.getStoreName());
    headers.add("销售件数", x-> FormatUtil.formatInteger(x.getSalesQuantity()));
    headers.add("销售金额", x-> FormatUtil.formatDouble(x.getSalesAmount()));
    headers.add("退货件数", x-> FormatUtil.formatInteger(x.getReturnQuantity()));
    headers.add("退货金额", x-> FormatUtil.formatDouble(x.getReturnAmount()));
    headers.add("实际销售件数", x-> FormatUtil.formatInteger(x.getActualQuantity()));
    headers.add("实际销售金额", x->FormatUtil.formatDouble(x.getActualAmount()));
    headers.add("销量退货率(%)",x->FormatUtil.formatDouble(x.getReturnQuantityRatio()));
    headers.add("金额退货率(%)",x->FormatUtil.formatDouble(x.getReturnAmountRatio()));
    messageExporter.exportExcel(this::listPageB2CSellStatisticsQuery,headers,query,fileName);
  }

  @Override
  public PageList<SalesOrderDetailSellStatistics> listPageB2CSellAndReturnStatisticsQuery(
      SalesDetailSellStatisticsQuery query, int page, int pageSize) {
    return salesReportDao.listPageB2CSellAndReturnStatisticsQuery(query,page,pageSize);
  }

  @Override
  public void exportB2CSellAndReturnStatistics(SalesDetailSellStatisticsQuery query,
      String fileName) {
    ExcelHeaderCollection<SalesOrderDetailSellStatistics> headers = new ExcelHeaderCollection<>();
    headers.add("店铺名称", x->x.getStoreName());
    headers.add("发货件数", x-> FormatUtil.formatInteger(x.getSalesQuantity()));
    headers.add("发货金额", x-> FormatUtil.formatDouble(x.getSalesAmount()));
    headers.add("退货件数", x-> FormatUtil.formatInteger(x.getReturnQuantity()));
    headers.add("退货金额", x-> FormatUtil.formatDouble(x.getReturnAmount()));
    headers.add("实际销售件数", x-> FormatUtil.formatInteger(x.getActualQuantity()));
    headers.add("实际销售金额", x->FormatUtil.formatDouble(x.getActualAmount()));
    headers.add("销量退货率(%)",x->FormatUtil.formatDouble(x.getReturnQuantityRatio()));
    headers.add("金额退货率(%)",x->FormatUtil.formatDouble(x.getReturnAmountRatio()));
    messageExporter.exportExcel(this::listPageB2CSellAndReturnStatisticsQuery,headers,query,fileName);
  }


}