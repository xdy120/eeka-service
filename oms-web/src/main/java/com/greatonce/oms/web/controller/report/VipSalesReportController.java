package com.greatonce.oms.web.controller.report;

import com.greatonce.core.database.PageList;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.report.VipSalesReportService;
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
public class VipSalesReportController {

  @Autowired
  private VipSalesReportService vipSalesReportService;

  @Autowired
  private StoreService storeService;
  @Autowired
  private ControllerUtil controllerUtil;

  @GetMapping(path = "vipDeliverQuery")
  public PageList<VipDeliverBO> exportVipDeliverQuery(VipDeliverQuery vipDeliverQuery,
      @RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
    controllerUtil
        .addUserStoreIds(vipDeliverQuery, VipDeliverQuery::getStoreIds,
            vipDeliverQuery::setStoreIds);
    return vipSalesReportService.exportVipDeliverList(vipDeliverQuery, page, pageSize);
  }

  @GetMapping(path = "vipReturnQuery")
  public PageList<VipReturnBO> exportVipReturnQuery(VipReturnRptQuery vipReturnRptQuery,
      @RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
    controllerUtil
        .addUserStoreIds(vipReturnRptQuery, VipReturnRptQuery::getStoreIds,
            vipReturnRptQuery::setStoreIds);
    return vipSalesReportService.exportVipReturnList(vipReturnRptQuery, page, pageSize);
  }

  @GetMapping(path = "vipSalesQuery")
  public PageList<VipSalesBO> exportVipSalesQuery(VipSalesQuery vipSalesFilter,
      @RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
    controllerUtil.addUserStoreIds(vipSalesFilter, VipSalesQuery::getStoreIds,
        vipSalesFilter::setStoreIds);
    return vipSalesReportService.exportVipSalesList(vipSalesFilter, page, pageSize);
  }

  @GetMapping("/vipSalesStatisticsReportQuery")
  public PageList<VipSalesStatisticsBO> vipSalesStatisticsReportQuery(VipSalesStatisticsQuery query, @RequestParam("page") int page,
      @RequestParam("pageSize") int pageSize){
    return vipSalesReportService.vipSalesStatisticsList(query, page, pageSize);
  }

  @GetMapping("/vipSalesOrderDateStatisticsQuery")
  public VipOrderMonthPageListBO salesOrderDateStatisticsQuery(VipSalesMonthStatisticsQuery query,
      @RequestParam("page") int page,
      @RequestParam("pageSize") int pageSize) {
    controllerUtil.addUserStoreIds(query, VipSalesMonthStatisticsQuery::getStoreIds,
        query::setStoreIds);
    VipOrderMonthPageListBO listPageList = vipSalesReportService
        .listPageOrderMonthStatistics(query, page, pageSize);
    return listPageList;
  }


  @GetMapping("/exportVipDelivery/{fileName}")
  public void vipDeliverReportQuery(@PathVariable("fileName") String fileName,
      VipDeliverQuery vipDeliverQuery) {
    vipSalesReportService.exportVipDelivery(fileName, vipDeliverQuery);
  }


  @GetMapping("/exportVipReturn/{fileName}")
  public void vipReturnReportQuery(@PathVariable("fileName") String fileName,
      VipReturnRptQuery vipReturnRptQuery) {
    vipSalesReportService.exportVipReturn(fileName, vipReturnRptQuery);
  }

  @GetMapping("/exportVipSales/{fileName}")
  public void vipSalesReportQuery(@PathVariable("fileName") String fileName,
      VipSalesQuery vipSalesQuery) {
    vipSalesReportService.exportVipSales(fileName, vipSalesQuery);
  }

  @GetMapping("/exportVipSalesStatistics/{fileName}")
  public void vipSalesStatisticsReportQuery(@PathVariable("fileName") String fileName, VipSalesStatisticsQuery query){
    vipSalesReportService.exportVipSalesStatistics(fileName,query);
  }

  @GetMapping("/exportVipOrderDateStatistics/{fileName}")
  public void exportOrderDateStatistics(@PathVariable("fileName") String fileName,
      VipSalesMonthStatisticsQuery query) {
    controllerUtil.addUserStoreIds(query, VipSalesMonthStatisticsQuery::getStoreIds,
        query::setStoreIds);
    vipSalesReportService.exportOrderDateStatistics(query, fileName);
  }

  @GetMapping(path = "vipSalesDeliveryDetailQuery")
  public PageList<VipDeliverBO> vipSalesDeliveryDetailQuery(VipDeliverQuery vipDeliverQuery,
      @RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
    controllerUtil
        .addUserStoreIds(vipDeliverQuery, VipDeliverQuery::getStoreIds,
            vipDeliverQuery::setStoreIds);
    if(!Assert.isNull(vipDeliverQuery.getLastOutTimeEnd())){
      LocalDateTime endTime = DateTimeUtil.parserLocalDateTime(DateTimeUtil.format(vipDeliverQuery.getLastOutTimeEnd())).plusDays(1);
      vipDeliverQuery.setLastOutTimeEnd(endTime);
    }
    return vipSalesReportService.vipSalesDeliveryDetailQueryListPage(vipDeliverQuery, page, pageSize);
  }

  @GetMapping("/exportVipSalesDeliveryDetail/{fileName}")
  public void exportVipSalesDeliveryDetail(@PathVariable("fileName") String fileName,
      VipDeliverQuery query) {
    controllerUtil.addUserStoreIds(query, VipDeliverQuery::getStoreIds,
        query::setStoreIds);
    if(!Assert.isNull(query.getLastOutTimeEnd())){
      LocalDateTime endTime = DateTimeUtil.parserLocalDateTime(DateTimeUtil.format(query.getLastOutTimeEnd())).plusDays(1);
      query.setLastOutTimeEnd(endTime);
    }
    vipSalesReportService.exportVipSalesDeliveryDetail(query, fileName);
  }


  @GetMapping(path = "vipReturnDetailQuery")
  public PageList<VipReturnBO> vipReturnDetailQuery(VipReturnRptQuery vipReturnRptQuery,
      @RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
    controllerUtil
        .addUserStoreIds(vipReturnRptQuery, VipReturnRptQuery::getStoreIds,
            vipReturnRptQuery::setStoreIds);
    if(!Assert.isNull(vipReturnRptQuery.getLastInTimeEnd())){
      LocalDateTime endTime = DateTimeUtil.parserLocalDateTime(DateTimeUtil.format(vipReturnRptQuery.getLastInTimeEnd())).plusDays(1);
      vipReturnRptQuery.setLastInTimeEnd(endTime);
    }
    return vipSalesReportService.vipReturnDetailQueryListPage(vipReturnRptQuery, page, pageSize);
  }

  @GetMapping("/exportVipReturnDetail/{fileName}")
  public void exportVipReturnDetail(@PathVariable("fileName") String fileName,
      VipReturnRptQuery query) {
    controllerUtil.addUserStoreIds(query, VipReturnRptQuery::getStoreIds,
        query::setStoreIds);
    if(!Assert.isNull(query.getLastInTimeEnd())){
      LocalDateTime endTime = DateTimeUtil.parserLocalDateTime(DateTimeUtil.format(query.getLastInTimeEnd())).plusDays(1);
      query.setLastInTimeEnd(endTime);
    }
    vipSalesReportService.exportVipReturnDetail(query, fileName);
  }


  /**
   * b2b销售统计
   * @param query
   * @param page
   * @param pageSize
   * @return
   */
  @GetMapping("/B2BSellStatisticsQuery")
  public PageList<VipSalesStatisticsBO> B2BSellStatisticsQuery(VipSalesStatisticsQuery query, @RequestParam("page") int page,
      @RequestParam("pageSize") int pageSize){
    controllerUtil.addUserStoreIds(query, VipSalesStatisticsQuery::getStoreIds,
        query::setStoreIds);
    if(!Assert.isNull(query.getTimeEnd())){
      LocalDateTime endTime = DateTimeUtil.parserLocalDateTime(DateTimeUtil.format(query.getTimeEnd())).plusDays(1);
      query.setTimeEnd(endTime);
    }
    return vipSalesReportService.B2BSellStatisticsQuery(query, page, pageSize);
  }

  /**
   * b2b销售统计导出
   */
  @GetMapping("/exportB2BSellStatistics/{fileName}")
  public void exportB2BSellStatistics(@PathVariable("fileName") String fileName, VipSalesStatisticsQuery query){
    controllerUtil.addUserStoreIds(query, VipSalesStatisticsQuery::getStoreIds,
        query::setStoreIds);
    if(!Assert.isNull(query.getTimeEnd())){
      LocalDateTime endTime = DateTimeUtil.parserLocalDateTime(DateTimeUtil.format(query.getTimeEnd())).plusDays(1);
      query.setTimeEnd(endTime);
    }
    vipSalesReportService.exportB2BSellStatistics(fileName,query);
  }
}
