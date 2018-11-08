package com.greatonce.oms.web.controller.report;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.report.SalesReportService;
import com.greatonce.oms.query.report.SalesOrderReturnQuery;
import com.greatonce.oms.query.report.SalesQuery;
import com.greatonce.oms.util.BizContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/report/auto")
@CrossOrigin
public class AutoExportController {

  @Autowired
  private SalesReportService salesReportService;

  @GetMapping("/exportSalesDeliveryForDay")
  public void exportSalesDeliveryForDay(@RequestParam String fileName, @RequestParam Long userId,
      SalesQuery query) {
    Assert.notNull(query.getDeliveryTimeBegin(), "开始时间为空");
    Assert.notNull(query.getDeliveryTimeEnd(), "结束时间为空");
    BizContext.setUserId(userId);
    salesReportService.exportSalesDeliveryForDay(fileName, query);
  }

  @GetMapping("/exportSalesDropShippingForDay")
  public void exportSalesDropShippingForDay(@RequestParam String fileName, @RequestParam Long userId,
      SalesQuery query) {
    Assert.notNull(query.getCreatedTimeBegin(), "开始时间为空");
    Assert.notNull(query.getCreatedTimeEnd(), "结束时间为空");
    BizContext.setUserId(userId);
    salesReportService.exportSalesDropShippingForDay(fileName, query);
  }

  @GetMapping("/exportSalesReturnForDay")
  public void exportSalesReturnForDay(@RequestParam String fileName, @RequestParam Long userId,
      SalesOrderReturnQuery query) {
    Assert.notNull(query.getUnpackedTimeBegin(), "开始时间为空");
    Assert.notNull(query.getUnpackedTimeEnd(), "结束时间为空");
    BizContext.setUserId(userId);
    salesReportService.exportSalesReturnForDay(fileName, query);
  }

  @GetMapping("/exportSalesRefundForDay")
  public void exportSalesRefundForDay(@RequestParam String fileName, @RequestParam Long userId,
      SalesOrderReturnQuery query) {
    Assert.notNull(query.getAppliedTimeBegin(), "开始时间为空");
    Assert.notNull(query.getAppliedTimeEnd(), "结束时间为空");
    BizContext.setUserId(userId);
    salesReportService.exportSalesRefundForDay(fileName, query);
  }
}
