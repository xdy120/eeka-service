package com.greatonce.oms.web.controller.trade;

import com.greatonce.oms.biz.trade.SalesOrderDiscountService;
import com.greatonce.oms.domain.trade.SalesOrderDiscount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author buer
 * @version 2017-12-08 9:46
 */
@RestController
@RequestMapping(value = "/trade/sales/{salesOrderId}/discount")
@CrossOrigin
public class SalesOrderDiscountController {

  @Autowired
  SalesOrderDiscountService salesOrderDiscountService;

  @GetMapping
  public List<SalesOrderDiscount> list(@PathVariable("salesOrderId") Long salesOrderId) {
    return salesOrderDiscountService.listExample(new SalesOrderDiscount() {{
      setSalesOrderId(salesOrderId);
    }});
  }
}
