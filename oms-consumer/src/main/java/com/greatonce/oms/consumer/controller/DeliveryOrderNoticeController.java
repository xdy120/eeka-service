package com.greatonce.oms.consumer.controller;

import com.greatonce.oms.biz.MqProducer;
import com.greatonce.oms.biz.trade.DispatchOrderService;
import com.greatonce.oms.domain.trade.DispatchOrder;
import com.greatonce.oms.message.trade.DispatchOrderCreateMessage;
import com.greatonce.oms.message.trade.DispatchOrderDeliveryMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangcf on 2018/3/27.
 */
@RestController
@RequestMapping("/trade/dispatch/order")
public class DeliveryOrderNoticeController {

  @Autowired
  MqProducer mqProducer;
  @Autowired
  DispatchOrderService dispatchOrderService;

  @PostMapping(value = "/create/{code}")
  public void create(@PathVariable("code") String code) {
    DispatchOrder dispatchOrder = dispatchOrderService.getByCode(code);
    DispatchOrderCreateMessage message = new DispatchOrderCreateMessage(
        dispatchOrder.getDispatchOrderId());
    mqProducer.send(message);
  }

  @PostMapping(value = "/delivery/{code}")
  public void delivery(@PathVariable("code") String code) {
    DispatchOrder dispatchOrder = dispatchOrderService.getByCode(code);
    DispatchOrderDeliveryMessage message = new DispatchOrderDeliveryMessage(
        dispatchOrder.getDispatchOrderId());
    mqProducer.send(message);
  }
}
