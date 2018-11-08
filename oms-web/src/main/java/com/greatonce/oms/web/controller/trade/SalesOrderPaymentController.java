package com.greatonce.oms.web.controller.trade;

import com.greatonce.oms.biz.trade.SalesOrderPaymentService;
import com.greatonce.oms.domain.trade.SalesOrderPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author buer
 * @version 2017-12-08 9:46
 */
@RestController
@RequestMapping(value = "/trade/sales/{salesOrderId}/payment")
@CrossOrigin
public class SalesOrderPaymentController {

  @Autowired
  SalesOrderPaymentService salesOrderPaymentService;

  @GetMapping
  public List<SalesOrderPayment> list(@PathVariable("salesOrderId") Long salesOrderId) {
    return salesOrderPaymentService.listExample(new SalesOrderPayment() {{
      setSalesOrderId(salesOrderId);
    }});
  }

  @PostMapping
  public void insert(@PathVariable("salesOrderId") Long salesOrderId,
      @RequestBody SalesOrderPayment payment) {
    payment.setSalesOrderId(salesOrderId);
    salesOrderPaymentService.create(payment);
  }

  @PutMapping(path = "/{salesOrderPaymentId}")
  public void update(@PathVariable("salesOrderId") Long salesOrderId,
      @PathVariable("salesOrderPaymentId") Long salesOrderPaymentId,
      @RequestBody SalesOrderPayment payment) {
    payment.setSalesOrderId(salesOrderId);
    payment.setSalesOrderPaymentId(salesOrderPaymentId);
    salesOrderPaymentService.modify(payment);
  }

  @DeleteMapping(path = "/{salesOrderPaymentId}")
  public void delete(@PathVariable("salesOrderId") Long salesOrderId,
      @PathVariable("salesOrderPaymentId") Long salesOrderPaymentId) {
    salesOrderPaymentService.remove(salesOrderPaymentService.getByKey(salesOrderPaymentId));
  }

  @GetMapping(path = "/{salesOrderPaymentId}")
  public SalesOrderPayment get(@PathVariable("salesOrderPaymentId") Long salesOrderPaymentId) {
    return salesOrderPaymentService.getByKey(salesOrderPaymentId);
  }
}
