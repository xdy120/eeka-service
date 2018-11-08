package com.greatonce.oms.web.controller.trade;

import com.greatonce.oms.biz.trade.SalesOrderMarketingService;
import com.greatonce.oms.domain.trade.SalesOrderMarketing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author buer
 * @version 2017-12-08 9:46
 */
@RestController
@RequestMapping(value = "/trade/sales/{salesOrderId}/marketing")
@CrossOrigin
public class SalesOrderMarketingController {

  @Autowired
  SalesOrderMarketingService salesOrderMarketingService;

  @GetMapping
  public List<SalesOrderMarketing> list(@PathVariable("salesOrderId") Long salesOrderId) {
    return salesOrderMarketingService.listExample(new SalesOrderMarketing() {{
      setSalesOrderId(salesOrderId);
    }});
  }

}
