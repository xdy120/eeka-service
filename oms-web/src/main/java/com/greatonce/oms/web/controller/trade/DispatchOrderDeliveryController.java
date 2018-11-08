package com.greatonce.oms.web.controller.trade;

import com.greatonce.oms.biz.trade.DispatchOrderDeliveryService;
import com.greatonce.oms.biz.trade.DispatchOrderService;
import com.greatonce.oms.domain.trade.DispatchOrderDelivery;
import com.greatonce.oms.query.trade.DispatchOrderDeliveryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author buer
 * @version 2017-12-08 9:46
 */
@RestController
@RequestMapping(value = "/trade/dispatch/{dispatchOrderId}/delivery")
@CrossOrigin
public class DispatchOrderDeliveryController {

  @Autowired
  DispatchOrderService dispatchOrderService;
  @Autowired
  DispatchOrderDeliveryService dispatchOrderDeliveryService;

  @GetMapping
  public List<DispatchOrderDelivery> list(@PathVariable("dispatchOrderId") Long dispatchOrderId) {
    DispatchOrderDeliveryQuery filter = new DispatchOrderDeliveryQuery();
    filter.setDispatchOrderId(dispatchOrderId);
    return dispatchOrderDeliveryService.list(filter);
  }
}
