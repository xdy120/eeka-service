package com.greatonce.oms.web.controller.report;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.biz.report.ProductReportService;
import com.greatonce.oms.bo.report.ProductReturnBO;
import com.greatonce.oms.bo.report.ProductSalesBO;
import com.greatonce.oms.query.report.ProductReturnQuery;
import com.greatonce.oms.query.report.ProductSalesQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/report")
@CrossOrigin
public class ProductReportController {

  @Autowired
  private ProductReportService productReportService;

  @GetMapping(path = "productReturnQuery")
  public PageList<ProductReturnBO> exportProductReturnQuery(ProductReturnQuery productReturnQuery,
      @RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
    return productReportService.exportProductReturnList(productReturnQuery, page, pageSize);
  }

  @GetMapping(path = "/{fileName}/exportReturn")
  public void exportReturn(@PathVariable("fileName") String fileName,
      ProductReturnQuery productReturnQuery) {
    productReportService.exportReturn(fileName, productReturnQuery);
  }

  @GetMapping(path = "productSalesQuery")
  public PageList<ProductSalesBO> exportProductSalesQuery(ProductSalesQuery productSalesQuery,
      @RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
    return productReportService.exportProductSalesList(productSalesQuery, page, pageSize);
  }

  @GetMapping(path = "/{fileName}/exportSales")
  public void exportSales(@PathVariable("fileName") String fileName,
      ProductSalesQuery productSalesQuery) {
    productReportService.exportSales(fileName, productSalesQuery);
  }
}
