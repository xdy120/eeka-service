package com.greatonce.oms.web.controller.trade;

import com.greatonce.oms.biz.trade.SalesOrderInvoiceService;
import com.greatonce.oms.domain.trade.SalesOrderInvoice;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author buer
 * @version 2017-12-08 9:46
 */
@RestController
@RequestMapping(value = "/trade/sales/{salesOrderId}/invoice")
@CrossOrigin
public class SalesOrderInvoiceController {

  @Autowired
  private SalesOrderInvoiceService salesOrderInvoiceService;

  @GetMapping
  public List<SalesOrderInvoice> list(@PathVariable("salesOrderId") Long salesOrderId) {
    return salesOrderInvoiceService.listExample(new SalesOrderInvoice() {{
      setSalesOrderId(salesOrderId);
    }});
  }

  @PostMapping
  public void insert(@PathVariable("salesOrderId") Long salesOrderId,
      @RequestBody SalesOrderInvoice invoice) {
    invoice.setSalesOrderId(salesOrderId);
    salesOrderInvoiceService.create(invoice);
  }

  @PutMapping(path = "/{salesOrderInvoiceId}")
  public void update(@PathVariable("salesOrderId") Long salesOrderId,
      @PathVariable("salesOrderInvoiceId") Long salesOrderInvoiceId,
      @RequestBody SalesOrderInvoice invoice) {
    invoice.setSalesOrderId(salesOrderId);
    invoice.setSalesOrderInvoiceId(salesOrderInvoiceId);
    salesOrderInvoiceService.modify(invoice);
  }

  @DeleteMapping(path = "/{salesOrderInvoiceId}")
  public void delete(@PathVariable("salesOrderId") Long salesOrderId,
      @PathVariable("salesOrderInvoiceId") Long salesOrderInvoiceId) {
    salesOrderInvoiceService.remove(salesOrderInvoiceService.getByKey(salesOrderInvoiceId));
  }

  @GetMapping(path = "/{salesOrderInvoiceId}")
  public SalesOrderInvoice get(@PathVariable("salesOrderInvoiceId") Long salesOrderInvoiceId) {
    return salesOrderInvoiceService.getByKey(salesOrderInvoiceId);
  }
}
