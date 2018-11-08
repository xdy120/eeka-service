package com.greatonce.oms.web.controller.report;

import com.greatonce.core.database.PageList;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.biz.report.SalesReportService;
import com.greatonce.oms.bo.report.OrderMonthPageListBO;
import com.greatonce.oms.bo.report.ReturnDetailBO;
import com.greatonce.oms.bo.report.SalesBO;
import com.greatonce.oms.bo.report.SalesOrderDeliverBO;
import com.greatonce.oms.bo.report.SalesOrderDetailSellStatistics;
import com.greatonce.oms.bo.report.SalesOrderOosBO;
import com.greatonce.oms.bo.report.SalesStatisticsBO;
import com.greatonce.oms.query.report.ReturnDetailQuery;
import com.greatonce.oms.query.report.SalesDetailSellStatisticsQuery;
import com.greatonce.oms.query.report.SalesOrderDeliverQuery;
import com.greatonce.oms.query.report.SalesOrderMonthStatisticsQuery;
import com.greatonce.oms.query.report.SalesOrderOosQuery;
import com.greatonce.oms.query.report.SalesQuery;
import com.greatonce.oms.query.report.SalesStatisticsQuery;
import com.greatonce.oms.web.controller.ControllerUtil;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangc on 2017/11/30.
 */
@RestController
@RequestMapping(value = "/report")
@CrossOrigin
public class SalesReportController {

  @Autowired
  private SalesReportService salesReportService;
  @Autowired
  private ControllerUtil controllerUtil;

  /**
   * 销售列表
   */
  @GetMapping(path = "salesQuery")
  public PageList<SalesBO> exportSalesQuery(SalesQuery salesFilter, @RequestParam("page") int page,
      @RequestParam("pageSize") int pageSize) {
    controllerUtil.addUserStoreIds(salesFilter, SalesQuery::getStoreIds, salesFilter::setStoreIds);
    return salesReportService.listPage(salesFilter, page, pageSize);
  }

  /**
   * 销售明细
   */
  @GetMapping(path = "salesDetailQuery")
  public PageList<SalesBO> exportSalesDetailQuery(SalesQuery salesFilter,
      @RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
    controllerUtil.addUserStoreIds(salesFilter, SalesQuery::getStoreIds, salesFilter::setStoreIds);
    return salesReportService.listPageDetail(salesFilter, page, pageSize);
  }

  @GetMapping("/exportSalesOrder/{fileName}")
  public void exportSalesOrder(@PathVariable("fileName") String fileName, SalesQuery salesQuery) {
    salesReportService.exportSales(fileName, salesQuery);
  }


  @GetMapping("/exportSalesOrderDetail/{fileName}")
  public void exportSalesOrderDetail(@PathVariable("fileName") String fileName,
      SalesQuery salesQuery) {
    salesReportService.exportSalesOrderDetail(fileName, salesQuery);
  }

  /**
   * 销售统计
   */
  @GetMapping(path = "salesStatisticsQuery")
  public PageList<SalesStatisticsBO> salesStatisticsQuery(SalesStatisticsQuery salesStatisticsQuery,
      @RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
    controllerUtil.addUserStoreIds(salesStatisticsQuery, SalesStatisticsQuery::getStoreIds,
        salesStatisticsQuery::setStoreIds);
    return salesReportService.listPageSalesStatistics(salesStatisticsQuery, page, pageSize);
  }

  @GetMapping("/exportSalesStatistics/{fileName}")
  public void exportSalesStatistics(@PathVariable("fileName") String fileName,
      SalesStatisticsQuery salesStatisticsQuery) {
    salesReportService.exportSalesStatistics(fileName, salesStatisticsQuery);
  }

  @GetMapping(path = "salesDeliverQuery")
  public PageList<SalesOrderDeliverBO> exportSalesDeliverQuery(
      SalesOrderDeliverQuery salesOrderDeliverQuery, @RequestParam("page") int page,
      @RequestParam("pageSize") int pageSize) {
    controllerUtil.addUserStoreIds(salesOrderDeliverQuery, SalesOrderDeliverQuery::getStoreIds,
        salesOrderDeliverQuery::setStoreIds);
    return salesReportService.exportSalesDeliverQuery(salesOrderDeliverQuery, page, pageSize);
  }


  @GetMapping("/exportDeliverStatistics/{fileName}")
  public void exportDeliverStatistics(@PathVariable("fileName") String fileName,
      SalesOrderDeliverQuery salesOrderDeliverQuery) {
    salesReportService.exportDeliverStatistics(fileName, salesOrderDeliverQuery);
  }

  @GetMapping(path = "salesOrderOosQuery")
  public PageList<SalesOrderOosBO> salesOrderOosQuery(SalesOrderOosQuery salesOrderOosQuery,
      @RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
    controllerUtil.addUserStoreIds(salesOrderOosQuery, SalesOrderOosQuery::getStoreIds,
        salesOrderOosQuery::setStoreIds);
    return salesReportService.salesOrderOosQuery(salesOrderOosQuery, page, pageSize);
  }

  @GetMapping("/exportSalesOrderOosStatistics/{fileName}")
  public void exportSalesOrderOosStatistics(@PathVariable("fileName") String fileName,
      SalesOrderOosQuery salesOrderOosQuery) {
    salesReportService.exportSalesOrderOosStatistics(fileName, salesOrderOosQuery);
  }

  @GetMapping("/orderDetailStatisticsQuery")
  public PageList<SalesOrderDetailSellStatistics> orderDetailStatisticsQuery(
      SalesDetailSellStatisticsQuery query, @RequestParam("page") int page,
      @RequestParam("pageSize") int pageSize) {
    controllerUtil.addUserStoreIds(query, SalesDetailSellStatisticsQuery::getStoreIds,
        query::setStoreIds);
    return salesReportService.listPageOrderDetailStatistics(query, page, pageSize);
  }

  @GetMapping("/salesOrderDateStatisticsQuery")
  public OrderMonthPageListBO salesOrderDateStatisticsQuery(SalesOrderMonthStatisticsQuery query,
      @RequestParam("page") int page,
      @RequestParam("pageSize") int pageSize) {
    controllerUtil.addUserStoreIds(query, SalesOrderMonthStatisticsQuery::getStoreIds,
        query::setStoreIds);
    OrderMonthPageListBO listPageList = salesReportService
        .listPageOrderMonthStatistics(query, page, pageSize);
    return listPageList;
  }


  @GetMapping("/exportOrderDateStatistics/{fileName}")
  public void exportOrderDateStatistics(@PathVariable("fileName") String fileName,
      SalesOrderMonthStatisticsQuery query) {

    controllerUtil.addUserStoreIds(query, SalesOrderMonthStatisticsQuery::getStoreIds,
        query::setStoreIds);
    salesReportService.exportOrderDateStatistics(query, fileName);
  }


  @GetMapping("/exportOrderDetailStatistics/{fileName}")
  public void exportOrderDetailStatistics(@PathVariable("fileName") String fileName,
      SalesDetailSellStatisticsQuery query) {
    controllerUtil.addUserStoreIds(query, SalesDetailSellStatisticsQuery::getStoreIds,
        query::setStoreIds);
    salesReportService.exportOrderDetailStatistics(query, fileName);
  }


  /**
   * 发货明细
   */
  @GetMapping(path = "salesDeliveryDetailQuery")
  public PageList<SalesBO> salesDeliveryDetailQuery(SalesQuery salesFilter, @RequestParam("page") int page,
      @RequestParam("pageSize") int pageSize) {
    controllerUtil.addUserStoreIds(salesFilter, SalesQuery::getStoreIds, salesFilter::setStoreIds);
    if(!Assert.isNull(salesFilter.getDeliveryTimeEnd())){
      LocalDateTime endTime = DateTimeUtil.parserLocalDateTime(DateTimeUtil.format(salesFilter.getDeliveryTimeEnd())).plusDays(1);
      salesFilter.setDeliveryTimeEnd(endTime);
    }
    return salesReportService.salesDeliveryDetailQueryListPage(salesFilter, page, pageSize);
  }

  /**
   * 发货明细报表
   */
  @GetMapping("/exportSalesDeliveryDetail/{fileName}")
  public void exportSalesDeliveryDetail(@PathVariable("fileName") String fileName, SalesQuery salesQuery) {
    controllerUtil.addUserStoreIds(salesQuery, SalesQuery::getStoreIds, salesQuery::setStoreIds);
    if(!Assert.isNull(salesQuery.getDeliveryTimeEnd())){
      LocalDateTime endTime = DateTimeUtil.parserLocalDateTime(DateTimeUtil.format(salesQuery.getDeliveryTimeEnd())).plusDays(1);
      salesQuery.setDeliveryTimeEnd(endTime);
    }
    salesReportService.exportSalesDeliveryDetail(fileName, salesQuery);
  }

  /**
   * 退货明细
   */
  @GetMapping(path = "returnDetailQuery")
  public PageList<ReturnDetailBO> returnDetailQuery(ReturnDetailQuery filter,
      @RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
    controllerUtil.addUserStoreIds(filter, ReturnDetailQuery::getStoreIds, filter::setStoreIds);
    if(!Assert.isNull(filter.getLastInTimeEnd())){
      LocalDateTime endTime = DateTimeUtil.parserLocalDateTime(DateTimeUtil.format(filter.getLastInTimeEnd())).plusDays(1);
      filter.setLastInTimeEnd(endTime);
    }
    return salesReportService.returnDetailQueryListPage(filter, page, pageSize);
  }

  /**
   * 退货明细报表
   */
  @GetMapping("/exportReturnDetail/{fileName}")
  public void exportReturnDetail(@PathVariable("fileName") String fileName,
      ReturnDetailQuery returnDetailQuery) {
    controllerUtil.addUserStoreIds(returnDetailQuery, ReturnDetailQuery::getStoreIds, returnDetailQuery::setStoreIds);
    if(!Assert.isNull(returnDetailQuery.getLastInTimeEnd())){
      LocalDateTime endTime = DateTimeUtil.parserLocalDateTime(DateTimeUtil.format(returnDetailQuery.getLastInTimeEnd())).plusDays(1);
      returnDetailQuery.setLastInTimeEnd(endTime);
    }
    salesReportService.exportReturnDetail(fileName, returnDetailQuery);
  }

  /**
   * b2c销售汇总
   */
  @GetMapping("/B2CSellStatisticsQuery")
  public PageList<SalesOrderDetailSellStatistics> B2CSellStatisticsQuery(
      SalesDetailSellStatisticsQuery query, @RequestParam("page") int page,
      @RequestParam("pageSize") int pageSize) {
    controllerUtil.addUserStoreIds(query, SalesDetailSellStatisticsQuery::getStoreIds,
        query::setStoreIds);
    if(!Assert.isNull(query.getCreateTimeEnd())){
      LocalDateTime endTime = DateTimeUtil.parserLocalDateTime(DateTimeUtil.format(query.getCreateTimeEnd())).plusDays(1);
      query.setCreateTimeEnd(endTime);
    }
    return salesReportService.listPageB2CSellStatisticsQuery(query, page, pageSize);
  }

  /**
   * b2c销售汇总导出
   */
  @GetMapping("/exportB2CSellStatistics/{fileName}")
  public void exportB2CSellStatistics(@PathVariable("fileName") String fileName,
      SalesDetailSellStatisticsQuery query) {
    controllerUtil.addUserStoreIds(query, SalesDetailSellStatisticsQuery::getStoreIds,
        query::setStoreIds);
    if(!Assert.isNull(query.getCreateTimeEnd())){
      LocalDateTime endTime = DateTimeUtil.parserLocalDateTime(DateTimeUtil.format(query.getCreateTimeEnd())).plusDays(1);
      query.setCreateTimeEnd(endTime);
    }
    salesReportService.exportB2CSellStatistics(query, fileName);
  }



  /**
   * B2C销售与退货跟踪
   */
  @GetMapping("/B2CSellAndReturnStatisticsQuery")
  public PageList<SalesOrderDetailSellStatistics> B2CSellAndReturnStatisticsQuery(
      SalesDetailSellStatisticsQuery query, @RequestParam("page") int page,
      @RequestParam("pageSize") int pageSize) {
    controllerUtil.addUserStoreIds(query, SalesDetailSellStatisticsQuery::getStoreIds,
        query::setStoreIds);
    if(!Assert.isNull(query.getCreateTimeEnd())){
      LocalDateTime endTime = DateTimeUtil.parserLocalDateTime(DateTimeUtil.format(query.getCreateTimeEnd())).plusDays(1);
      query.setCreateTimeEnd(endTime);
    }
    return salesReportService.listPageB2CSellAndReturnStatisticsQuery(query, page, pageSize);
  }

  /**
   * B2C销售与退货跟踪导出
   */
  @GetMapping("/exportB2CSellAndReturnStatistics/{fileName}")
  public void exportB2CSellAndReturnStatistics(@PathVariable("fileName") String fileName,
      SalesDetailSellStatisticsQuery query) {
    controllerUtil.addUserStoreIds(query, SalesDetailSellStatisticsQuery::getStoreIds,
        query::setStoreIds);
    if(!Assert.isNull(query.getCreateTimeEnd())){
      LocalDateTime endTime = DateTimeUtil.parserLocalDateTime(DateTimeUtil.format(query.getCreateTimeEnd())).plusDays(1);
      query.setCreateTimeEnd(endTime);
    }
    salesReportService.exportB2CSellAndReturnStatistics(query, fileName);
  }

}
